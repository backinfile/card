package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;

public abstract class TriggerOnceAction extends Action {

	@Override
	public void pulse() {
		run();
	}

	@Override
	public boolean isDone() {
		return true;
	}

	public abstract void run();
}
