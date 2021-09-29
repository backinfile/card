package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class DiscardHandToMaxAction extends TriggerOnceAction {
	public DiscardHandToMaxAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		int number = human.handPile.size() - human.handPileMaxSize;
		if (number > 0) {
			addFirst(new SelectToDiscardHandAction(human, number));
		}
	}

}
