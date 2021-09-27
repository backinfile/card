package com.backinfile.card.model.cards.chapter2;

import com.backinfile.card.model.skills.BeeAttackSkill;

public class Bee extends MonsterCard {
	public Bee() {
		monsterSkillTypes.add(MonsterSkillType.Attack);
		monsterSkillTypes.add(MonsterSkillType.Harass);

		addSkill(new BeeAttackSkill());
	}
}
