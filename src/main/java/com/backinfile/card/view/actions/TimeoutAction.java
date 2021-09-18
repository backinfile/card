package com.backinfile.card.view.actions;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;

public class TimeoutAction extends TemporalAction {
	private Runnable runnable;

	public TimeoutAction(float duration, Runnable runnable) {
		super(duration);
		this.runnable = runnable;
	}

	@Override
	protected void update(float percent) {
		if (percent == 1) {
			runnable.run();
		}
	}

}
