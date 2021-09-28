package com.backinfile.card.model.actions;

import com.backinfile.card.model.Action;

// 执行一次然后结束的Action
public abstract class TriggerOnceAction extends Action {
	@Override
	public void init() {
		run();
	}

	@Override
	public boolean isDone() {
		return true;
	}

	public abstract void run();
}
