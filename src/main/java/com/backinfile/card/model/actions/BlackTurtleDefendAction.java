package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class BlackTurtleDefendAction extends WaitAction {
	private AttackAction attackAction;

	public BlackTurtleDefendAction(Human human, AttackAction attackAction) {
		this.human = human;
		this.attackAction = attackAction;
	}

	@Override
	public void init() {
		if (human.markPile.isEmpty()) {
			cancel();
			return;
		}

		var humanOper = new SelectCardOper(human.markPile, actionString.tip, 0, 1);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getSelectedPile().isEmpty()) {
				cancel();
				return;
			}
			defend(humanOper.getSelectedPile().getAny());
		});
		human.addHumanOper(humanOper);
	}

	private void cancel() {
		addFirst(attackAction);
		setDone();
	}

	private void defend(Card mark) {
		addFirst(new DiscardCardAction(human, mark, attackAction.card));
		setDone();
	}

}
