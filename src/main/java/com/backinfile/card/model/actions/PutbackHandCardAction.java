package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;

public class PutbackHandCardAction extends TriggerOnceAction {
	public CardPile cardPile = new CardPile();

	public PutbackHandCardAction(Human human, CardPile cardPile) {
		this.human = human;
		this.cardPile.addAll(cardPile);
	}

	@Override
	public void run() {
		human.handPile.removeAll(cardPile);
		human.drawPile.addAll(cardPile);
		addFirst(new ShuffleAction(human));
	}

}
