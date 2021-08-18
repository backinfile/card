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
		human.clearSelectInfo();
		targetHuman.clearSelectInfo();

		List<CardSlot> emptySlots = targetHuman.getEmptySlots(true);
		if (emptySlots.isEmpty()) {

			// 自己选择一处进行侵占
			asBreak = false;
			var targetInfo = new TargetInfo(TargetType.EmptySlotExceptPlan);
			targetInfo.number = 1;
			targetInfo.optional = false;
			targetInfo.tip = actionString.tip;
			human.targetInfo = targetInfo;
		} else {
			// 对手选择一张被击破
			asBreak = true;
			var targetInfo = new TargetInfo(TargetType.StoreSlotExceptPlan);
			targetInfo.number = 1;
			targetInfo.optional = false;
			targetHuman.targetInfo = targetInfo;
		}
	}

	@Override
	public void pulse() {
		if (!human.selectedPile.isEmpty()) {
			board.removeCard(card);
			var occupyCard = (SlotOccupyCard) human.selectedPile.get(0);
			int slotIndex = occupyCard.getSlotIndex();
			CardSlot cardSlot = targetHuman.cardSlotMap.get(slotIndex);
			cardSlot.sealCard = card;

			isDone = true;
			return;
		}

		if (!targetHuman.selectedPile.isEmpty()) {
			var breakCard = targetHuman.selectedPile.get(0);
			board.removeCard(card);
			board.removeCard(breakCard);
			board.getHuman(breakCard.oriHumanId).discardPile.add(breakCard);
			human.discardPile.add(card);
			addLast(new DrawCardAction(human, 1));

			isDone = true;
			return;
		}
	}
}
