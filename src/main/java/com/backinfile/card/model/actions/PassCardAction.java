package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class PassCardAction extends TriggerOnceAction {
	public int drawNumber;
	public int discardNumber;

	public PassCardAction(Human human, int number) {
		this.human = human;
		this.drawNumber = number;
		this.discardNumber = number;
	}

	public PassCardAction(Human human, int drawNumber, int discardNumber) {
		this.human = human;
		this.drawNumber = drawNumber;
		this.discardNumber = discardNumber;
	}

	@Override
	public void run() {
		addFirst(new SelectToDiscardHandAction(human, discardNumber));
		addFirst(new DrawCardAction(human, drawNumber));
	}
}
