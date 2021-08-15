package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.Res;
import com.backinfile.card.model.cards.ActionCard;

public class Attack extends ActionCard {

	public Attack(int id) {
		super(id, Res.CARD_ATTACK);

		subType = CardSubType.ACTION_ORDER;
	}
	
	

}
