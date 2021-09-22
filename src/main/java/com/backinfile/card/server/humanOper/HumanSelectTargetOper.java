package com.backinfile.card.server.humanOper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCard;
import com.backinfile.card.gen.GameMessageHandler.SCSelectEmptySlot;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.SelectCardStepInfo;
import com.backinfile.card.server.local.LocalGameServer;
import com.backinfile.card.server.local.HumanOperCache.HumanOperType;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.Log;
import com.backinfile.support.Utils;

public class HumanSelectTargetOper implements IHumanOper {
	private LocalGameServer gameServer;
	private Human human;
	private TargetInfo targetInfo;
	private CardPile cardSelected = new CardPile();
	private SelectCardStepInfo stepInfo;

	public HumanSelectTargetOper(LocalGameServer gameServer, Human human) {
		this.human = human;
		this.targetInfo = human.targetInfo;
	}

	@Override
	public void onAttach() {
		// AI做出选择，转交给AI
		if (!human.token.equals(LocalData.instance().token)) {
			onAISelectTarget(human);
			return;
		}

		if (targetInfo.isSelectCardType()) {
			// 推送卡牌选择消息
			stepInfo = targetInfo.stepSelectCard(cardSelected);
			if (stepInfo.isSelectOver()) { // 不用选择了，直接完成
				targetInfo.setSelect(cardSelected);
				return;
			}
			gameServer.sendMessage(human, stepInfo.toMsg());
			return;
		}
		switch (targetInfo.targetInfo.getType()) {
		case None:
		case Confirm:
			break;
		case EmptySlot: {
			var targetHuman = targetInfo.targetInfo.getOpponent() ? human.getOpponent() : human;
			var emptySlots = targetHuman.getEmptySlots(targetInfo.targetInfo.getExceptPlan());
			if (emptySlots.isEmpty()) {
				Log.game.warn("找不到合理的空储备位，直接强制完成");
				targetInfo.setSelect(new ArrayList<Integer>());
				return;
			}
			var emptySlotIndexs = emptySlots.stream().map(s -> s.index).collect(Collectors.toList());
			SCSelectEmptySlot msg = new SCSelectEmptySlot();
			msg.setAimType(targetInfo.targetInfo.getSlotAimType());
			msg.setTip(targetInfo.targetInfo.getTip());
			msg.setSelectFromList(emptySlotIndexs);
			gameServer.sendMessage(human, msg);
			break;
		}
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

	@Override
	public boolean onMessage(String token, DSyncBase msg) {
		if (!human.token.equals(token)) {
			return false;
		}
		if (msg instanceof CSSelectCard) {
			var cardId = ((CSSelectCard) msg).getCardId();
			if (!targetInfo.isSelectCardType()) {
				Log.game.error("unmatch for SelectCardType");
				return false;
			}
			if (cardId == 0) {
				// 选择完成
				if (stepInfo.optional) {
					targetInfo.setSelect(cardSelected);
					return true;
				}
			} else if (stepInfo.cardPile.contains(cardId)) {
				Card card = human.board.getCard(cardId);
				if (card != null && !cardSelected.contains(card)) {
					cardSelected.add(card);
					// 推送卡牌选择消息
					stepInfo = targetInfo.stepSelectCard(cardSelected);
					if (stepInfo.cardPile.isEmpty()) { // 选择完成
						targetInfo.setSelect(cardSelected);
						return true;
					}
					gameServer.sendMessage(human, stepInfo.toMsg());
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isDone() {
		return targetInfo.isSelected();
	}

}
