package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;

public abstract class WaitAction extends Action {

	public boolean isDone = false;

	@Override
	public boolean isDone() {
		return isDone;
	}
}
