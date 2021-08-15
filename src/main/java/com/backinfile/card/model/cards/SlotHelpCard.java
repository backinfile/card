package com.backinfile.card.model.cards;

import com.backinfile.card.Res;
import com.backinfile.card.model.Card;

public abstract class SlotHelpCard extends Card {
	protected int slotIndex;

	public SlotHelpCard(int id, String name, int slotIndex) {
		super(id, name);
		this.slotIndex = slotIndex;
	}

	public int getSlotIndex() {
		return slotIndex;
	}

	public static class SlotOccupyCard extends SlotHelpCard {
		public SlotOccupyCard(int id, int slotIndex) {
			super(id, Res.CARD_AID_OCCUPY, slotIndex);
		}
	}

	public static class SlotStoreCard extends SlotHelpCard {
		public SlotStoreCard(int id, int slotIndex) {
			super(id, Res.CARD_AID_STORE, slotIndex);
		}
	}

	public static class SlotChargeCard extends SlotHelpCard {
		public SlotChargeCard(int id, int slotIndex) {
			super(id, Res.CARD_AID_CHARGE, slotIndex);
		}
	}
}
