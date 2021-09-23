package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

public class DragonAttackSkill extends Skill {
	public DragonAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 1);
	}
}
