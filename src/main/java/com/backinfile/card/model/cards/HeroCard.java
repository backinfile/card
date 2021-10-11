package com.backinfile.card.model.cards;

import com.backinfile.card.manager.LocalString.LocalCardString;
import com.backinfile.card.model.Card;

public abstract class HeroCard extends Card {

	public HeroCard(LocalCardString cardString) {
		super(cardString);

		mainType = CardType.HERO;

	}

	public HeroCard() {
		this(null);
	}
}
