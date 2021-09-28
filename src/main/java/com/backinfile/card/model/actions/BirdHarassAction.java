package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class BirdHarassAction extends WaitAction {
	private Human targetHuman;

	public BirdHarassAction(Human human) {
		this.human = human;
		this.targetHuman = human.getOpponent();

	}

	@Override
	public void init() {
		var cards = targetHuman.drawnCardsInTurnStart.filter(targetHuman.handPile::contains);
		if (cards.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(cards, actionString.tip, 0, 1);
		humanOper.setDetachCallback(() -> {
			addFirst(new PutbackHandCardAction(targetHuman, humanOper.getSelectedPile()));
			addFirst(new DrawCardAction(targetHuman, humanOper.getSelectedPile().size()));
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
