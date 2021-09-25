package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.skills.RecallSkill;

public class Recall extends ActionCard {
	public Recall() {
		addSkill(new RecallSkill());
	}
}
