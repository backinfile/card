package com.backinfile.card.model.actions;

import java.util.List;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;
import com.backinfile.card.model.cards.SlotHelpCard.SlotOccupyCard;

public class AttackAction extends WaitAction {
	private Human targetHuman;
	private boolean asBreak = false;

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

			// 自己选择一处进行侵占
			asBreak = false;
			human.targetInfo = new TargetInfo(TargetType.EmptySlotExceptPlan, 1, actionString.tip);
		} else {
			// 对手选择一张被击破
			asBreak = true;
			targetHuman.targetInfo = new TargetInfo(TargetType.StoreSlotExceptPlan, 1, actionString.tips[0]);
		}
	}

	@Override
	public void pulse() {
		if (human.targetInfo.isSelected()) {
			board.removeCard(card);
			var occupyCard = (SlotOccupyCard) human.targetInfo.getSelectedOne();
			int slotIndex = occupyCard.getSlotIndex();
			CardSlot cardSlot = targetHuman.cardSlotMap.get(slotIndex);
			cardSlot.sealCard = card;

			setDone();
			return;
		}

		if (targetHuman.targetInfo.isSelected()) {
			var breakCard = targetHuman.targetInfo.getSelectedOne();
			board.removeCard(card);
			board.removeCard(breakCard);
			board.getHuman(breakCard.oriHumanToken).discardPile.add(breakCard);
			human.discardPile.add(card);
			addLast(new DrawCardAction(human, 1));

			setDone();
			return;
		}
	}
}
