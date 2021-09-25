package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class FlipStoreAction extends WaitAction {
	private Human targetHuman;

	public FlipStoreAction(Human human, Human targetHuman) {
		this.human = human;
		this.targetHuman = targetHuman;
	}

	@Override
	public void init() {
		var stores = targetHuman.getAllStoreInSlot(false, false, false, true);
		if (stores.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(stores, actionString.tip, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelect(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelect(Card flipCard) {
		var slot = targetHuman.getCardSlotByCard(flipCard);
		slot.remove(flipCard);
		addFirst(new DiscardCardAction(human, slot.getAllCards()));
		slot.getPile(ESlotType.Seal).add(flipCard);
		board.modifyCard(flipCard);
	}
}
