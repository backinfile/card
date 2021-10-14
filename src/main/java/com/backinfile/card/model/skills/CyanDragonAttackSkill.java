package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.CyanDragonAttackAction;

public class CyanDragonAttackSkill extends Skill {
	public CyanDragonAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 1);
	}

	@Override
	public boolean triggerable() {
		return human.markPile.size() > 2;
	}

	@Override
	public void apply() {
		addLast(new CyanDragonAttackAction(human));
	}
}
