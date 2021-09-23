package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class SelectToDiscardHandAction extends WaitAction {
	public SelectToDiscardHandAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.handPile, Utils.format(actionString.tip, number), number);
		humanOper.setDetachCallback(() -> {
			for (var card : humanOper.getSelectedPile().reverse()) {
				addFirst(new DiscardCardAction(human, card));
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
