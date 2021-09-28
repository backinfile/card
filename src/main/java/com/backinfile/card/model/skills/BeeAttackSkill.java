package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.BeeAttackAction;

public class BeeAttackSkill extends Skill {
	public BeeAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceRelease, SkillAura.AnyWhere, 0);
		marks.add(SkillMark.Release);
	}

	@Override
	public void apply() {
		addFirst(new BeeAttackAction(human, card));
	}
}
