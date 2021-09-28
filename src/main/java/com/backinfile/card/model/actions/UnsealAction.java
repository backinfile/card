package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class UnsealAction extends WaitAction {
	public UnsealAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void init() {
		CardPile toUnseal = human.getAllUnSealableCard();
		if (toUnseal.isEmpty()) {
			setDone();
			return;
		}

		// 转化为一次解封1张
		var humanOper = new SelectCardOper(toUnseal, Utils.format(actionString.tip, number), 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onUnseal(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onUnseal(Card unsealCard) {
		if (number > 1) {
			addFirst(new UnsealAction(human, number - 1));
		}
		addFirst(new DiscardCardAction(human, unsealCard));
		setDone();
	}
}
