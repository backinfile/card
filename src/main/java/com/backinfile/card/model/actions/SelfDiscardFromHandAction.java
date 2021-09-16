package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ETargetType;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.model.Human;

public class SelfDiscardFromHandAction extends WaitAction {
	private int number;

	public SelfDiscardFromHandAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void init() {
		human.targetInfo.clear();
		human.targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.HandPile, number, actionString.tip));
	}

	@Override
	public void pulse() {
		if (!human.targetInfo.isSelected()) {
			return;
		}

		addLast(new DiscardCardAction(human, human.targetInfo.getTargetSelected()));
		setDone();
	}
}
