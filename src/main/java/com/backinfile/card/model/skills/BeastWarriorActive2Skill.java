package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.BeastWarriorActive2Action;

public class BeastWarriorActive2Skill extends Skill {
	public BeastWarriorActive2Skill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 1);
	}

	@Override
	public boolean triggerable() {
		return human.markPile.size() > 3;
	}

	@Override
	public void apply() {
		addLast(new BeastWarriorActive2Action()); 
	}

}
