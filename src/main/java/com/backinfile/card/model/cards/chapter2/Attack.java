package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.skills.PlayAttackSkill;

public class Attack extends ActionCard {

	public Attack() {
		subType = CardSubType.ACTION_ORDER;
		addSkill(new PlayAttackSkill(2));
		addSkill(new PlayAttackSkill(1));
	}

}
