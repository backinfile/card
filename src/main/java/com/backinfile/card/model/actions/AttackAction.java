package com.backinfile.card.model.actions;

import java.util.List;

import com.backinfile.card.gen.GameMessage.ESlotType;
import com.backinfile.card.gen.GameMessage.ETargetSlotAimType;
import com.backinfile.card.gen.GameMessage.ETargetType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.SlotHelpCard.SlotOccupyCard;
import com.backinfile.card.gen.GameMessage.DCardPileInfo;
import com.backinfile.card.gen.GameMessage.DTargetCardInfo;
import com.backinfile.card.gen.GameMessage.DTargetInfo;
import com.backinfile.card.gen.GameMessage.ECardPileType;

public class AttackAction extends WaitAction {
	private Human targetHuman;

	public AttackAction(Human human, Card card, Human targetHuman) {
		this.human = human;
		this.card = card;
		this.targetHuman = targetHuman;
	}

	@Override
	public void init() {
		human.clearTargetInfo();
		targetHuman.clearTargetInfo();

		List<CardSlot> emptySlots = targetHuman.getEmptySlots(true);
		if (emptySlots.isEmpty()) {
			// 自己选择一处空位进行侵占
			var targetInfo = new DTargetInfo();
			targetInfo.setType(ETargetType.EmptySlotExceptionPlan);
			targetInfo.setTip(actionString.tip);
			targetInfo.setSlotAimType(ETargetSlotAimType.Occupy);
			targetInfo.setMaxNumber(1);
			targetInfo.setMinNumber(1);
			human.targetInfo = targetInfo;

		} else {
			// 对手选择一张被击破
			var targetInfo = new DTargetInfo();
			targetInfo.setType(ETargetType.CardPile);
			targetInfo.setTip(actionString.tips[0]);
			targetInfo.setMaxNumber(1);
			targetInfo.setMinNumber(1);
			DTargetCardInfo targetCardInfo = new DTargetCardInfo();
			targetCardInfo.setPileType(ECardPileType.SlotPile);
			targetCardInfo.setSlotType(ESlotType.Store);
			targetCardInfo.setExceptPlan(true);
			targetInfo.addTargetCardInfos(targetCardInfo);
			human.targetInfo = targetInfo;
		}
	}

	@Override
	public void pulse() {
		// 自己选择一处进行侵占
		if (human.isTargetSelected()) {
			board.removeCard(card);
			var occupyCard = (SlotOccupyCard) human.getTargetSelectOne();
			int slotIndex = occupyCard.getSlotIndex();
			CardSlot cardSlot = targetHuman.cardSlotMap.get(slotIndex);
			cardSlot.put(ESlotType.Seal, card);

			setDone();
			return;
		}

		// 对手选择一张被击破
		if (targetHuman.isTargetSelected()) {
			var breakCard = targetHuman.getTargetSelectOne();
			board.removeCard(card);
			board.removeCard(breakCard);
			board.getHuman(breakCard.oriHumanToken).discardPile.add(breakCard);
			human.discardPile.add(card);
			addLast(new DrawCardAction(human, 1));

			setDone();
			return;
		}
	}

	@Override
	public void dispose() {
		human.clearTargetInfo();
		targetHuman.clearTargetInfo();
	}
}
