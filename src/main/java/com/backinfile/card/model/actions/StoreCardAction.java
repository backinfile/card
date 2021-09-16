package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.gen.GameMessageHandler.ETargetType;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;

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
			human.targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.EmptySlot, 1, actionString.tip));
			break;
		}
		case Replace: {
			human.targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.StoreInSlot, 1, actionString.tips[0]));
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
				CardSlot cardSlot = human.cardSlotMap.get(human.targetInfo.getSelectSlotIndex());
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
				Card selectedOne = human.targetInfo.getTargetSelectOne();
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
}
