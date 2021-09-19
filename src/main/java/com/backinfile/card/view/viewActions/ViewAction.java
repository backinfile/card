package com.backinfile.card.view.viewActions;

import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.IAction;
import com.backinfile.support.Time2;

public abstract class ViewAction implements IAction {
	public GameStage gameStage;
	public float duration = 0; // 持续时间
	public long endTime = 0; // 结束时间

	public ViewAction() {
	}

	@Override
	public void init() {
		if (duration > 0) {
			endTime = Time2.getCurMillis() + (long) (duration * Time2.SEC);
		}
	}

	public void begin() {

	}

	@Override
	public void pulse() {
	}

	@Override
	public boolean isDone() {
		return endTime > 0 && Time2.getCurMillis() > endTime;
	}
}
