package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class RemoveOpponentHandAction extends WaitAction {
	private int showNumber;
	private int selectNumber;

	public RemoveOpponentHandAction(Human human, int showNumber, int selectNumber) {
		this.human = human;
		this.showNumber = showNumber;
		this.selectNumber = selectNumber;
	}

	@Override
	public void init() {
		var toSelectFrom = human.getOpponent().handPile.getRandom(showNumber);
		if (toSelectFrom.isEmpty()) {
			setDone();
			return;
		}

		var humanOper = new SelectCardOper(toSelectFrom, Utils.format(actionString.tip, selectNumber), selectNumber);
		humanOper.setDetachCallback(() -> {
			addFirst(new ArrangePileAction(human.getOpponent()));
			addFirst(new DiscardCardAction(human, humanOper.getSelectedPile()));
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
