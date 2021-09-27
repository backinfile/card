package com.backinfile.card.model.actions;

import java.util.Arrays;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;

// 将一些牌弃置到弃牌堆, 一般通过addFirst使用
public class DiscardCardAction extends TriggerOnceAction {
	private CardPile cards = new CardPile();

	public DiscardCardAction(Human human, Iterable<Card> cards) {
		this.human = human;
		this.cards.addAll(cards);
	}

	public DiscardCardAction(Human human, Card... cards) {
		this.human = human;
		this.cards.addAll(Arrays.asList(cards));
	}

	public DiscardCardAction(Human human, Card card) {
		this.human = human;
		this.cards.add(card);
	}

	@Override
	public void run() {
		if (cards.isEmpty()) {
			return;
		}

		for (var card : cards) {
			board.removeCard(card);
			if (card == null || card.oriHumanToken == null) {
				System.out.println();
			}
			if (card.oriHumanToken.equals(human.token)) {
				human.discardPile.add(card);
			} else {
				human.getOpponent().discardPile.add(card);
			}
		}
		board.modifyCard(cards);
	}
}
