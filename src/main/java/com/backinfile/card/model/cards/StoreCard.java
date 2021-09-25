package com.backinfile.card.model.cards;

import com.backinfile.card.manager.LocalString.LocalCardString;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.skills.StoreSelfSkill;

public abstract class StoreCard extends Card {

	public StoreCard(LocalCardString cardString) {
		super(cardString);

		mainType = CardType.STORE;

		addSkill(new StoreSelfSkill());
	}

	public StoreCard() {
		this(null);
	}

}
