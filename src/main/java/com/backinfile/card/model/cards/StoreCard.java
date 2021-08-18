package com.backinfile.card.model.cards;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.LocalString.LocalCardString;

public abstract class StoreCard extends Card {

	public StoreCard(LocalCardString cardString) {
		super(cardString);

		mainType = CardType.STORE;
	}

	public StoreCard() {
		this(null);
	}

}
