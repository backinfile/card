package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;

public abstract class WaitAction extends Action {

	private boolean isDone = false;

	@Override
	public boolean isDone() {
		return isDone;
	}

	public void setDone() {
		isDone = true;
	}
}
