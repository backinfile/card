package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class DiscardThreatenAction extends WaitAction {
	public DiscardThreatenAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void init() {
		if (human.threatenPile.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(human.threatenPile, Utils.format(actionString.tips[0], number), number);
		humanOper.setDetachCallback(() -> {
			addLast(new DiscardCardAction(human, humanOper.getSelectedPile()));
			addLast(new ArrangePileAction(human));
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
