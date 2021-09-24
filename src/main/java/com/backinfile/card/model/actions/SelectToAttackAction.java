package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class SelectToAttackAction extends WaitAction {
	private Human targetHuman;
	private boolean withAttackEffect = false;

	public SelectToAttackAction(Human human, Human targetHuman, int number) {
		this.human = human;
		this.targetHuman = targetHuman;
		this.number = number;
	}

	public SelectToAttackAction(Human human, Human targetHuman, int number, boolean withAttackEffect) {
		this.human = human;
		this.targetHuman = targetHuman;
		this.number = number;
		this.withAttackEffect = withAttackEffect;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.getAllStoreCards(true, true, false, false, false),
				Utils.format(actionString.tip, number), number);
		humanOper.setDetachCallback(() -> {
			for (var card : humanOper.getSelectedPile().reverse()) {
				addFirst(new AttackAction(human, card, targetHuman, withAttackEffect));
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
