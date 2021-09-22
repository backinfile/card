package com.backinfile.card.view.viewActions;

import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.IAction;

public abstract class ViewAction implements IAction {
	public GameStage gameStage;
	private boolean isDone = false;

	public ViewAction() {
	}

	@Override
	public void init() {
	}

	public void begin() {
	}

	@Override
	public void pulse() {
	}

	public final void setDone() {
		isDone = true;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

}
