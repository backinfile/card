package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class SelfDiscardFromHandAction extends WaitAction {
	private int number;

	public SelfDiscardFromHandAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void init() {
		human.clearTargetInfo();
		human.targetInfo = new TargetInfo(TargetType.Hand, number, actionString.tip);
	}

	@Override
	public void pulse() {
		if (!human.targetInfo.isSelected()) {
			return;
		}

		addLast(new DiscardCardAction(human, human.targetInfo.getSelected()));
		setDone();
	}

	@Override
	public void dispose() {
		human.clearTargetInfo();
	}

}
