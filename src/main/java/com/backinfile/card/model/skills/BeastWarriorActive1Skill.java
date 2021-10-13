package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SaveMarkAction;

public class BeastWarriorActive1Skill extends Skill {

	public BeastWarriorActive1Skill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 0, 1);
	}

	@Override
	public int getRealCostAP() {
		return super.getRealCostAP() + (human.markPile.isEmpty() ? 0 : 1);
	}

	@Override
	public boolean triggerable() {
		if (human.handPile.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		addLast(new SaveMarkAction(human, human.handPile));
	}

}
