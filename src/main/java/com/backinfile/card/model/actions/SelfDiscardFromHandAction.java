package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;

public class SelfDiscardFromHandAction extends WaitAction {
	private int number;

	public SelfDiscardFromHandAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void init() {
		human.selectedPile.clear();

		TargetInfo targetInfo = new TargetInfo(TargetType.Hand);
		targetInfo.optional = false;
		targetInfo.number = number;
		human.targetInfo = targetInfo;
	}

	@Override
	public void pulse() {
		if (human.selectedPile.isEmpty()) {
			return;
		}

		isDone = true;
		addLast(new DiscardCardAction(human, new CardPile(human.selectedPile)));
	}

}
