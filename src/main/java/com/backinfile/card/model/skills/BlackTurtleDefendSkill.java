package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.BlackTurtleDefendAction;

public class BlackTurtleDefendSkill extends Skill {
	public BlackTurtleDefendSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Defend, SkillAura.Hero, 0);
	}

	@Override
	public boolean triggerable() {
		return !human.markPile.isEmpty();
	}

	@Override
	public void apply() {
		AttackAction attackAction = param.get("attackAction");
		addFirst(new BlackTurtleDefendAction(human, attackAction));
	}

}
