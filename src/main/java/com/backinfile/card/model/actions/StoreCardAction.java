package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessage.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;
import com.backinfile.card.model.cards.SlotHelpCard.SlotStoreCard;

public class StoreCardAction extends WaitAction {
	private boolean fast;
	private Mode mode = Mode.EmptySlot;

	private static enum Mode {
		EmptySlot, // 补充空位
		Replace, // 替换
	}

	public StoreCardAction(Human human, Card card, boolean fast) {
		this.human = human;
		this.card = card;
		this.fast = fast;
	}

	public StoreCardAction(Human human, Card card) {
		this(human, card, false);
	}

	@Override
	public void init() {
		if (human.getEmptySlots(false).isEmpty()) {
			if (human.getStoreSlots(false, false).isEmpty()) {
				setDone();
				return;
			}
			mode = Mode.Replace;
		} else {
			mode = Mode.EmptySlot;
		}

		switch (mode) {
		case EmptySlot: {
			human.targetInfo = new TargetInfo(TargetType.EmptySlot, 1, actionString.tip);
			break;
		}
		case Replace: {
			human.targetInfo = new TargetInfo(TargetType.StoreSlotExceptPlan, 1, actionString.tip);
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void pulse() {
		if (isDone()) {
			return;
		}

		switch (mode) {
		case EmptySlot: {
			if (human.targetInfo.isSelected()) {
				board.removeCard(card);
				Card selectedOne = human.targetInfo.getSelectedOne();
				SlotStoreCard slotCard = (SlotStoreCard) selectedOne;
				CardSlot cardSlot = human.cardSlotMap.get(slotCard.getSlotIndex());
				cardSlot.put(ESlotType.Store, card);
				cardSlot.ready = fast;
				setDone();
				return;
			}
			break;
		}
		case Replace: {
			if (human.targetInfo.isSelected()) {
				board.removeCard(card);
				Card selectedOne = human.targetInfo.getSelectedOne();
				CardSlot cardSlot = human.getCardSlotByCard(selectedOne);
				cardSlot.put(ESlotType.Store, card);
				cardSlot.ready = fast;
				addLast(new DiscardCardAction(human, selectedOne));
				setDone();
				return;
			}
			break;
		}
		}

	}

	@Override
	public void dispose() {
		human.clearTargetInfo();
	}
}
