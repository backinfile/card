package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class DrawCardAction extends TriggerOnceAction {
	public DrawCardAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {

	}
}
