package com.backinfile.card.model.cards;

import com.backinfile.card.model.Card;

public abstract class SlotHelpCard extends Card {
	protected int slotIndex;

	public SlotHelpCard(int slotIndex) {
		this.slotIndex = slotIndex;
	}

	public int getSlotIndex() {
		return slotIndex;
	}

	public static class SlotOccupyCard extends SlotHelpCard {
		public SlotOccupyCard(int slotIndex) {
			super(slotIndex);
		}
	}

	public static class SlotStoreCard extends SlotHelpCard {
		public SlotStoreCard(int slotIndex) {
			super(slotIndex);
		}
	}

	public static class SlotChargeCard extends SlotHelpCard {
		public SlotChargeCard(int slotIndex) {
			super(slotIndex);
		}
	}
}
