package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class RemoveOpponentStoreOrMarkAction extends WaitAction {
	public RemoveOpponentStoreOrMarkAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		CardPile selectFrom = new CardPile();
		selectFrom.addAll(human.getOpponent().markPile);
		selectFrom.addAll(human.getOpponent().getAllStoreCards(false, false, true, true, true));

		if (selectFrom.isEmpty()) {
			setDone();
			return;
		}

		var humanOper = new SelectCardOper(selectFrom, actionString.tips[0], 0, 1);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getSelectedPile().isEmpty()) {
				setDone();
				return;
			}
			var card = humanOper.getSelectedPile().getAny();
			addFirst(new RefreshSlotAction());
			addFirst(new DiscardCardAction(human, card));
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
