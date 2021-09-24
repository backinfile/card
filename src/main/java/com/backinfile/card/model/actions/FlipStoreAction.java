package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardPile;
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
			onSelect(humanOper.getSelectedPile());
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelect(CardPile cardPile) {
		if (cardPile.isEmpty()) {
			setDone();
		}
		var flipCard = cardPile.get(0);
		var slot = targetHuman.getCardSlotByCard(flipCard);
		slot.remove(flipCard);
		slot.getPile(ESlotType.Seal).add(flipCard);
		board.modifyCard(flipCard);
	}
}
