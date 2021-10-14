package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class CyanDragonAttackAction extends WaitAction {
	public CyanDragonAttackAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.markPile, actionString.tips[0], 2);
		humanOper.setDetachCallback(() -> {
			for (var card : humanOper.getSelectedPile()) {
				addLast(new AttackAction(human, card, human.getOpponent()));
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
