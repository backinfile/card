package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

// 主动回合结束技能
public class TurnEndSkill extends Skill {
	public TurnEndSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 0);
	}
}
