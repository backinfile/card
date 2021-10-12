package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SaveMarkAction extends WaitAction {
	private CardPile selectFrom = new CardPile();

	public SaveMarkAction(Human human, CardPile selectFrom) {
		this.human = human;
		this.selectFrom.addAll(selectFrom);
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(selectFrom, actionString.tip, 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				var card = humanOper.getSelectedPile().getAny();
				board.removeCard(card);
				human.markPile.add(card);
				board.modifyCard(card, human.handPile);
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
