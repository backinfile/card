package com.backinfile.card.model.cards;

import com.backinfile.card.model.Card;

public abstract class StoreCard extends Card {

	public StoreCard(int id, String name) {
		super(id, name);

		mainType = CardType.STORE;
	}

}
