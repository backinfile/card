package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;
import com.backinfile.card.model.ActionQueue;

public class SequenceAction extends WaitAction {
	private ActionQueue actionQueue = new ActionQueue();

	public SequenceAction(Action... actions) {
		for (var action : actions) {
			actionQueue.addLast(action);
		}
	}

	public void addAction(Action action) {
		actionQueue.addLast(action);
	}

	@Override
	public void init() {
		actionQueue.board = board;
	}

	@Override
	public void pulse() {
		actionQueue.pulse();
		if (actionQueue.isEmpty()) {
			setDone();
		}
	}
}
