package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;

public class ArrangePileAction extends TriggerOnceAction {
	private CardPile cardPile = null;

	/**
	 * 默认为手牌
	 */
	public ArrangePileAction() {
	}

	public ArrangePileAction(CardPile cardPile) {
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
