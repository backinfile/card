package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class SelectToAttackAction extends WaitAction {
	private Human targetHuman;
	private boolean withAttackEffect = false;

	public SelectToAttackAction(Human human) {
		this.human = human;
		this.targetHuman = human.getOpponent();
	}

	public SelectToAttackAction(Human human, boolean withAttackEffect) {
		this.human = human;
		this.targetHuman = human.getOpponent();
		this.withAttackEffect = withAttackEffect;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.getAllStoreCards(true, true, false, false, false),
				Utils.format(actionString.tips[withAttackEffect ? 1 : 0], 1), actionString.tips[2], 0, 1);
		humanOper.setDetachCallback(() -> {
			for (var card : humanOper.getSelectedPile().reverse()) {
				board.removeCard(card);
				addFirst(new AttackAction(human, card, targetHuman, withAttackEffect));
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
