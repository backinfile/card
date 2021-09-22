package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;

// 执行一次然后结束的Action
public abstract class TriggerOnceAction extends Action {
	private boolean isDone = false;

	@Override
	public void pulse() {
		run();
		isDone = true;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	public abstract void run();
}
