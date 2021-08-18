package com.backinfile.card.model.cards;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.LocalString.LocalCardString;

public abstract class ActionCard extends Card {
	public ActionCard(LocalCardString cardString) {
		super(cardString);
		mainType = CardType.ACTION;
	}

	public ActionCard() {
		this(null);
	}

}
