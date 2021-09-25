package com.backinfile.card.model.cards;

import com.backinfile.card.manager.LocalString.LocalCardString;
import com.backinfile.card.model.Card;

public abstract class ActionCard extends Card {
	public ActionCard(LocalCardString cardString) {
		super(cardString);
		mainType = CardType.ACTION;
	}

	public ActionCard() {
		this(null);
	}

}
