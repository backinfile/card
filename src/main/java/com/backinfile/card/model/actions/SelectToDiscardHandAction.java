package com.backinfile.card.model.actions;

import java.util.function.Predicate;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class SelectToDiscardHandAction extends WaitAction {
	private Predicate<Card> predicate;

	public SelectToDiscardHandAction(Human human, int number, Predicate<Card> predicate) {
		this.human = human;
		this.number = number;
		this.predicate = predicate;
	}

	public SelectToDiscardHandAction(Human human, int number) {
		this.human = human;
		this.number = number;
		this.predicate = c -> true;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.handPile.filter(predicate), Utils.format(actionString.tip, number),
				number);
		humanOper.setDetachCallback(() -> {
			addFirst(new ArrangePileAction(human));
			addFirst(new DiscardCardAction(human, humanOper.getSelectedPile()));
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
