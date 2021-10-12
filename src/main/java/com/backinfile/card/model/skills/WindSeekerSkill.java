package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

public class WindSeekerSkill extends Skill {

	public WindSeekerSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Passive, SkillAura.Hero, 0, 1);
	}

	@Override
	public void apply() {
	}
}
