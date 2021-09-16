package com.backinfile.card.model.cards;

import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.model.Card;

public class SlotHelpCard extends Card {
	private int slotIndex;
	private ETargetSlotAimType type;

	public SlotHelpCard() {
		mainType = CardType.NONE;
	}

	public SlotHelpCard(int slotIndex, ETargetSlotAimType type) {
		this();
		this.slotIndex = slotIndex;
		this.type = type;
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
