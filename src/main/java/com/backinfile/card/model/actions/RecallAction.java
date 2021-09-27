package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;

public class RecallAction extends WaitAction {
	private AttackAction attackAction;

	public RecallAction(Human human, Card card, AttackAction attackAction) {
		this.human = human;
		this.card = card;
		this.attackAction = attackAction;
	}

	@Override
	public void init() {
		
	}
}
