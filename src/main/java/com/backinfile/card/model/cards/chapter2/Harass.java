package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.skills.HarassSkill;

public class Harass extends ActionCard {
	public Harass() {
		addSkill(new HarassSkill());
	}
}
