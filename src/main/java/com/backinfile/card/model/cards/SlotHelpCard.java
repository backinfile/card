package com.backinfile.card.model.cards;

import com.backinfile.card.model.Card;

public abstract class SlotHelpCard extends Card {
	private int slotIndex;

	public SlotHelpCard() {
		mainType = CardType.NONE;
	}

	public int getSlotIndex() {
		return slotIndex;
	}

	public void setSlotIndex(int slotIndex) {
		this.slotIndex = slotIndex;
	}

	public static class SlotOccupyCard extends SlotHelpCard {
	}

	public static class SlotStoreCard extends SlotHelpCard {
	}
}
