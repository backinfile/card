package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.skills.BirdRideSkill;
import com.backinfile.card.server.humanOper.ConfirmOper;

public class BirdRideOutTurnAction extends WaitAction {
	private Card bird;
	private Action attackAction;

	public BirdRideOutTurnAction(Human human, Card bird, Action attackAction) {
		this.human = human;
		this.bird = bird;
		this.attackAction = attackAction;
	}

	@Override
	public void init() {
		var humanOper = new ConfirmOper(true, actionString.tip);
		humanOper.setDetachCallback(() -> {
			addFirst(attackAction);
			if (humanOper.getResult()) {
				var skill = bird.getSkill(BirdRideSkill.class);
				board.applySkill(skill);
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
