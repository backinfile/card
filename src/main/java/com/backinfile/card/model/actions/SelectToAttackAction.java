package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class SelectToAttackAction extends WaitAction {
	private Human targetHuman;
	private boolean withAttackEffect = false;
	private int minNumber;
	private int maxNumber;

	public SelectToAttackAction(Human human, Human targetHuman, int number) {
		this.human = human;
		this.targetHuman = targetHuman;
		this.minNumber = number;
		this.maxNumber = number;
	}

	public SelectToAttackAction(Human human, Human targetHuman, int minNumber, int maxNumber) {
		this.human = human;
		this.targetHuman = targetHuman;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	public SelectToAttackAction(Human human, Human targetHuman, int number, boolean withAttackEffect) {
		this.human = human;
		this.targetHuman = targetHuman;
		this.minNumber = number;
		this.maxNumber = number;
		this.withAttackEffect = withAttackEffect;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.getAllStoreCards(true, true, false, false, false),
				Utils.format(actionString.tips[withAttackEffect ? 1 : 0], maxNumber), actionString.tips[2], minNumber,
				maxNumber);
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
