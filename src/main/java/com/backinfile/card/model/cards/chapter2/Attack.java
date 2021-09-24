package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.skills.Release2StoreSkill;
import com.backinfile.card.model.skills.ReleaseAttackStoreSkill;

public class Attack extends ActionCard {

	public Attack() {
		addSkill(new ReleaseAttackStoreSkill());
		addSkill(new Release2StoreSkill());
	}
}
