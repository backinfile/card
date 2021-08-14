package com.backinfile.card.model.actions;

import com.backinfile.card.model.Skill;

public class WaitOperAction extends WaitAction {
	private int targetCount;
	private int count = 0;

	public WaitOperAction(int targetCount) {
		this.targetCount = targetCount;
	}

	@Override
	public boolean isDone() {
		return count >= targetCount;
	}

	public IOperable getOperable() {
		return new WaitOperActionOperable();
	}

	private class WaitOperActionOperable implements IOperable {
		@Override
		public void emit(Skill skill) {
			count++;
		}
	}

}
