package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.StoreCard;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SelectToStoreAction extends WaitAction {
	private boolean fast;

	public SelectToStoreAction(Human human, int number, boolean fast) {
		this.human = human;
		this.number = number;
		this.fast = fast;
	}

	@Override
	public void init() {
		if (number <= 0) {
			setDone();
			return;
		}
		var toSelect = human.handPile.filter(c -> c instanceof StoreCard);
		if (toSelect.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(toSelect, actionString.tips[fast ? 1 : 0], 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelect(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelect(Card toStore) {
		if (number > 1) {
			addFirst(new SelectToStoreAction(human, number - 1, fast));
		}
		addFirst(new ArrangePileAction(human));
		addFirst(new StoreCardAction(human, toStore, fast));
	}
}
