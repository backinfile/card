package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

public class PlanSkill extends Skill {
	public PlanSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 1);
	}

	@Override
	public void apply() {
	}

}
