package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;

public class DiscardCardAction extends TriggerOnceAction {
	private CardPile cards = new CardPile();

	public DiscardCardAction(Human human, Iterable<Card> cards) {
		this.human = human;
		this.cards.addAll(cards);
	}

	@Override
	public void run() {
		for (var card : cards) {
			human.handPile.remove(card);
		}
	}
}
