package com.backinfile.card.model.cards;

import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.model.Card;

public abstract class SlotHelpCard extends Card {
	private int slotIndex;
	protected ETargetSlotAimType type;

	public SlotHelpCard() {
		mainType = CardType.TOOL;
	}

	public static class SlotOccupyCard extends SlotHelpCard {
		public SlotOccupyCard() {
			this(0);
		}

		public SlotOccupyCard(int slotIndex) {
			setSlotIndex(slotIndex);
			type = ETargetSlotAimType.Occupy;
		}
	}

	public static class SlotStoreCard extends SlotHelpCard {
		public SlotStoreCard() {
			this(0);
		}

		public SlotStoreCard(int slotIndex) {
			setSlotIndex(slotIndex);
			type = ETargetSlotAimType.Store;
		}
	}

	public int getSlotIndex() {
		return slotIndex;
	}

	public void setSlotIndex(int slotIndex) {
		this.slotIndex = slotIndex;
	}

	public ETargetSlotAimType getType() {
		return type;
	}
}
