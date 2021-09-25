package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.skills.RideSkill;

public class Ride extends ActionCard {
	public Ride() {
		addSkill(new RideSkill());
	}
}
