package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class ShuffleAction extends TriggerOnceAction {

	public ShuffleAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		this.human.drawPile.shuffle();
	}

}
