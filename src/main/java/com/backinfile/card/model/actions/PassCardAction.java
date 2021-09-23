package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class PassCardAction extends TriggerOnceAction {
	public int number;

	public PassCardAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void run() {
		addLast(new DrawCardAction(human, number));
		addLast(new SelectToDiscardHandAction(human, number));
	}
}
