package com.backinfile.card.server.local;

import java.util.LinkedList;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DTargetSelect;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.SelectCardStepInfo;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.IAlive;
import com.backinfile.support.func.Terminal;

public class LocalGameServer extends Terminal<MessageWarpper, MessageWarpper> implements IAlive {
	public Board board;
	private GameMessageHandler gameMessageHandler;

	// 当前正在执行操作的Human
	private LinkedList<HumanOperCache> waitingHumanOper = new LinkedList<>();

	public LocalGameServer() {
	}

	public void init() {
		board = new Board();
		gameMessageHandler = new GameMessageHandler();
		gameMessageHandler.addListener(new LocalGameServerMessageHandler(this, board));
	}

	public void startGame(DBoardInit boardInit) {
		board.init(boardInit);
		for (var human : board.humans) {
			sendMessage(human, board.getBoardSetup(human.token));
		}
		board.start();
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
		if (!waitingHumanOper.isEmpty()) {
			return;
		}

		// 等待玩家执行操作
		if (board.isWaitingHumanOper()) {
			// 执行Action中的选择
			for (var human : board.humans) {
				if (human.targetInfo.needSelectTarget()) {
					onTargetInfoAttach(human);
				}
			}
			// 执行一项Skill
			if (waitingHumanOper.isEmpty()) {
				// TODO
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

	// 玩家需要执行一个操作， 推送消息给玩家
	private void onTargetInfoAttach(Human human) {
		var humanOperCache = new HumanOperCache(human);
		waitingHumanOper.add(humanOperCache);
		TargetInfo targetInfo = human.targetInfo;
		if (targetInfo.isSelectCardType()) {
			// 推送卡牌选择消息
			var info = targetInfo.stepSelectCard(humanOperCache.selected);
			sendMessage(human, info.toMsg());
			return;
		}
		switch (targetInfo.targetInfo.getType()) {
		case None:
		case Confirm:
		case EmptySlot:
			break;
		case DiscardPile:
		case DrawPile:
		case HandPile:
		case Store:
		case StoreInSlot:
		default:
			break;
		}
	}

	public void onClientSelectCard(String token, long id) {
		for (var cache : waitingHumanOper) {
			if (cache.targetInfo.isSelected()) {
				continue;
			}
			if (cache.human.token.equals(token)) {
				if (cache.targetInfo.isSelectCardType()) {
					SelectCardStepInfo stepSelectCard = cache.targetInfo.stepSelectCard(cache.selected);
					if (id == 0) {
						if (stepSelectCard.optional) { // 选择完成
							DTargetSelect targetSelect = new DTargetSelect();
							targetSelect.addAllSelectedCard(cache.selected.getCardIdList());
							cache.targetInfo.targetSelect = targetSelect;
							break;
						}
					} else if (stepSelectCard.cardPile.contains(id)) {
						Card card = board.getCard(id);
						if (card != null && !cache.selected.contains(card)) {
							cache.selected.add(card);
							// 推送卡牌选择消息
							var info = cache.targetInfo.stepSelectCard(cache.selected);
							if (info.cardPile.isEmpty()) { // 选择完成
								DTargetSelect targetSelect = new DTargetSelect();
								targetSelect.addAllSelectedCard(cache.selected.getCardIdList());
								cache.targetInfo.targetSelect = targetSelect;
								break;
							}
							sendMessage(cache.human, info.toMsg());
						}
					}
				}
			}
		}
	}

	private void sendMessage(Human human, DSyncBase msg) {
		outputMsg(MessageWarpper.pack(human, msg));
	}
}
