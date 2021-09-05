package com.backinfile.card.model.actions;

import java.util.List;

import com.backinfile.card.gen.GameMessage.ESlotType;
import com.backinfile.card.gen.GameMessage.ETargetSlotAimType;
import com.backinfile.card.gen.GameMessage.ETargetType;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;

public class AttackAction extends WaitAction {
	private Human targetHuman;

	public AttackAction(Human human, Card card, Human targetHuman) {
		this.human = human;
		this.card = card;
		this.targetHuman = targetHuman;
	}

	@Override
	public void init() {
		human.targetInfo.clear();
		targetHuman.targetInfo.clear();

		List<CardSlot> emptySlots = targetHuman.getEmptySlots(true);
		if (emptySlots.isEmpty()) {
			// 自己选择对手一处空位进行侵占
			human.targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.EmptySlot, ETargetSlotAimType.Occupy, 1,
					actionString.tip, false, true, true, false));
		} else {
			// 对手选择一张被击破
			human.targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.StoreInSlot, ETargetSlotAimType.None, 1,
					actionString.tip, false, true));
		}
	}

	@Override
	public void pulse() {
		// 自己选择一处进行侵占
		if (human.targetInfo.isSelected()) {
			board.removeCard(card);
			int slotIndex = human.targetInfo.getSelectSlotIndex();
			CardSlot cardSlot = targetHuman.cardSlotMap.get(slotIndex);
			cardSlot.put(ESlotType.Seal, card);

			setDone();
			return;
		}

		// 对手选择一张被击破
		if (targetHuman.targetInfo.isSelected()) {
			var breakCard = targetHuman.targetInfo.getTargetSelectOne();
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
		super.dispose();
		human.targetInfo.clear();
		targetHuman.targetInfo.clear();
	}
}
