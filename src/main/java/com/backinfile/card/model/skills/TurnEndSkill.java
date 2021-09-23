package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.TurnEndAction;

// 主动回合结束技能
public class TurnEndSkill extends Skill {
	public TurnEndSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {
		addLast(new TurnEndAction(human));
	}
}
