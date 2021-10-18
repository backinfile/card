package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

public class OutTurnSkillCancelSkill extends Skill {
	public OutTurnSkillCancelSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {
		this.human.actionPoint = 0;
	}

}
