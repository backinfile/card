package com.backinfile.card.model.cards;

import com.backinfile.card.model.Card;

public abstract class ActionCard extends Card {
	public ActionCard(int id, String name) {
		super(id, name);

		mainType = CardType.ACTION;
	}

}
