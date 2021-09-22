package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.support.Log;

public class SelfDiscardFromHandAction extends WaitAction {
	private int number;

	public SelfDiscardFromHandAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void init() {
		Log.game.error("TODO");
	}

	@Override
	public void pulse() {
	}
}
