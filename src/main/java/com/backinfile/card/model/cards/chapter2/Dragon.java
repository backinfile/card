package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.model.skills.DragonAttackSkill;
import com.backinfile.card.model.skills.DragonRideSkill;

public class Dragon extends MonsterCard {
	public Dragon() {
		monsterSkillTypes.add(MonsterSkillType.Attack);
		monsterSkillTypes.add(MonsterSkillType.Ride);

		addSkill(new DragonAttackSkill());
		addSkill(new DragonRideSkill());
	}
}
