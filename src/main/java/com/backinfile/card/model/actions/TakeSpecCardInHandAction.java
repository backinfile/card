package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class TakeSpecCardInHandAction extends WaitAction {
	private CardPile cardPile = new CardPile();
	private int minNumber;
	private int maxNumber;

	public TakeSpecCardInHandAction(Human human, CardPile cardPile, int minNumber, int maxNumber) {
		this.human = human;
		this.cardPile.addAll(cardPile);
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(cardPile, Utils.format(actionString.tip, maxNumber), minNumber, maxNumber);
		humanOper.setDetachCallback(() -> {
			onSelected(humanOper.getSelectedPile());
		});
		human.addHumanOper(humanOper);
	}

	private void onSelected(CardPile selected) {
		for (var card : selected) {
			board.removeCard(card);
		}
		human.handPile.addAll(selected);
		addFirst(new ArrangePileAction(human));
		setDone();
	}
}
