package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class ReturnOpponentStoreOrMarkAction extends TriggerOnceAction {
	public ReturnOpponentStoreOrMarkAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		addFirst(new RemoveOpponentStoreOrMarkAction(human, true));
	}
}
