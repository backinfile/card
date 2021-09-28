package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.WhaleRecallAction;

public class WhaleRecallSkill extends Skill {
	public WhaleRecallSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Recall, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {
		addLast(new WhaleRecallAction(human));
	}
}
