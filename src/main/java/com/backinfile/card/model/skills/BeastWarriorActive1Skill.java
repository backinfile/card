package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.GainAPAction;
import com.backinfile.card.model.actions.SaveMarkAction;

public class BeastWarriorActive1Skill extends Skill {

	public BeastWarriorActive1Skill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 0, 1);
	}

	@Override
	public boolean triggerable() {
		if (human.handPile.isEmpty()) {
			return false;
		}
		if (!human.markPile.isEmpty() && human.actionPoint < 1) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		if (!human.markPile.isEmpty()) {
			addLast(new GainAPAction(human, -1));
		}
		addLast(new SaveMarkAction(human, human.handPile));
	}

}
