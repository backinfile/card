package com.backinfile.card.server.local;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.SCSelectSkillToActive;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.SelectCardStepInfo;
import com.backinfile.card.model.actions.TurnEndAction;
import com.backinfile.card.model.skills.TurnEndSkill;
import com.backinfile.card.server.local.HumanOperCache.HumanOperType;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.IAlive;
import com.backinfile.support.Log;
import com.backinfile.support.Utils;
import com.backinfile.support.func.Terminal;

public class LocalGameServer extends Terminal<MessageWarpper, MessageWarpper> implements IAlive {
	public Board board;
	private GameMessageHandler gameMessageHandler;
	private String token;

	// 当前正在执行操作的Human
	private LinkedList<HumanOperCache> waitingHumanOperList = new LinkedList<>();

	public LocalGameServer() {
	}

	public void init() {
		token = LocalData.instance().token;

		board = new Board();
		gameMessageHandler = new GameMessageHandler();
		gameMessageHandler.addListener(new LocalGameServerMessageHandler(this, board));
	}

	public void startGame(DBoardInit boardInit) {
		board.init(boardInit);
		for (var human : board.humans) {
			sendMessage(human, board.getBoardSetup(human.token));
		}
		board.start(token);
	}

	@Override
	public void pulse() {

		// 处理客户端来的消息
		while (hasMsg()) {
			var msg = pollMsg();
			gameMessageHandler.onMessage(msg.content);
		}

		if (board == null) {
			return;
		}

		// 等待玩家执行操作
		if (!waitingHumanOperList.isEmpty()) {
			return;
		}

		// 等待玩家执行操作
		if (board.isWaitingHumanOper()) {
			if (board.isWaitingHumanSelectTarget()) {
				// 执行Action中的选择
				for (var human : board.humans) {
					if (human.targetInfo.needSelect()) {
						onTargetInfoAttach(human);
					}
				}
			} else {
				// 执行一项Skill
				onHumanSelectSkillAttach(board.curTurnHuman);
			}
			return;
		}

		// 正常游戏循环
		board.pulseLoop();
		for (var human : board.humans) {
			for (var msg : human.msgCacheQueue) {
				sendMessage(human, msg);
			}
			human.msgCacheQueue.clear();
		}
	}

	private void onHumanSelectSkillAttach(Human human) {
		if (!human.token.equals(token)) {
			onAISelectSkillAttach(human);
			return;
		}

		List<Skill> activableSkills = board.getActivableSkills(human.token);
		var skillInfos = activableSkills.stream().map(s -> s.toMsg()).collect(Collectors.toList());
		SCSelectSkillToActive msg = new SCSelectSkillToActive();
		msg.addAllSkillInfos(skillInfos);
		sendMessage(human, msg);
		waitingHumanOperList.add(new HumanOperCache(human, activableSkills));
	}

	// 玩家需要执行一个操作， 推送消息给玩家
	private void onTargetInfoAttach(Human human) {
		// AI做出选择，转交给AI
		if (!human.token.equals(token)) {
			onAISelectTarget(human);
			return;
		}

		var humanOperCache = new HumanOperCache(human);
		TargetInfo targetInfo = human.targetInfo;
		if (targetInfo.isSelectCardType()) {
			// 推送卡牌选择消息
			var info = targetInfo.stepSelectCard(humanOperCache.selected);
			if (info.isSelectOver()) { // 不用选择了，直接完成
				targetInfo.setSelect(humanOperCache.selected);
				return;
			}
			sendMessage(human, info.toMsg());
			waitingHumanOperList.add(humanOperCache);
			return;
		}
		waitingHumanOperList.add(humanOperCache);
		switch (targetInfo.targetInfo.getType()) {
		case None:
		case Confirm:
			break;
		case EmptySlot:
			break;
		default:
			break;
		}
		Log.game.error("unhandle onTargetInfoAttach");
	}

	private void onAISelectTarget(Human human) {
		if (human.targetInfo.isSelected()) {
			return;
		}
		if (human.targetInfo.isSelectCardType()) {
			CardPile selected = new CardPile();
			while (true) {
				var info = human.targetInfo.stepSelectCard(selected);
				if (info.isSelectOver()) {
					human.targetInfo.setSelect(selected);
					return;
				} else {
					var card = info.cardPile.get(Utils.nextInt(info.cardPile.size()));
					selected.add(card);
				}
			}
		}
		Log.game.error("unhandle onAISelectTarget");
	}

	/**
	 * 客户端选择一张卡牌
	 */
	public void onClientSelectCard(String token, long id) {
		for (var cache : waitingHumanOperList) {
			if (cache.type != HumanOperType.Target) {
				continue;
			}
			if (cache.targetInfo.isSelected()) {
				continue;
			}
			if (cache.human.token.equals(token)) {
				if (!cache.targetInfo.isSelectCardType()) {
					Log.game.warn("unmatch targetInfo type");
					break;
				}
				SelectCardStepInfo oldInfo = cache.targetInfo.stepSelectCard(cache.selected);
				if (id == 0) {
					// 选择完成
					if (oldInfo.optional) {
						cache.targetInfo.setSelect(cache.selected);
						waitingHumanOperList.remove(cache);
						break;
					}
				} else if (oldInfo.cardPile.contains(id)) {
					Card card = board.getCard(id);
					if (card != null && !cache.selected.contains(card)) {
						cache.selected.add(card);
						// 推送卡牌选择消息
						var newInfo = cache.targetInfo.stepSelectCard(cache.selected);
						if (newInfo.cardPile.isEmpty()) { // 选择完成
							cache.targetInfo.setSelect(cache.selected);
							waitingHumanOperList.remove(cache);
							break;
						}
						sendMessage(cache.human, newInfo.toMsg());
					}
				}
			}
		}
	}

	private void onAISelectSkillAttach(Human human) {
		TurnEndSkill skill = human.getSkill(TurnEndSkill.class);
		skill.setContext(board, human, null);
		skill.apply();
	}

	public void onClientUseSkill(String token, long skillId) {
		for (var cache : waitingHumanOperList) {
			if (cache.type != HumanOperType.Skill) {
				continue;
			}
			if (cache.human.token.equals(token)) {
				if (cache.skills.stream().anyMatch(s -> s.id == skillId)) {
					waitingHumanOperList.remove(cache);

					Skill skill = board.getSkillById(skillId);
					skill.apply();
					break;
				}
			}
		}
	}

	private void sendMessage(Human human, DSyncBase msg) {
		outputMsg(MessageWarpper.pack(human, msg));
	}
}
