package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;

public class ArrangePileAction extends TriggerOnceAction {
	private CardPile cardPile = null;

	/**
	 * 默认为手牌
	 */
	public ArrangePileAction(Human human) {
		this.human = human;
	}

	public ArrangePileAction(Human human, CardPile cardPile) {
		this.human = human;
		// 这里比较特殊，用引用传递
		this.cardPile = cardPile;
	}

	@Override
	public void run() {
		if (cardPile == null) {
			board.modifyCard(human.handPile);
		} else {
			board.modifyCard(cardPile);
		}
	}
}
