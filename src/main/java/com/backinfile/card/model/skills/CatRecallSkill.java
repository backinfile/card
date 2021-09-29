package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.PassCardAction;

public class CatRecallSkill extends Skill {
	public CatRecallSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Recall, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {
		addFirst(new PassCardAction(human, 5, 4));
	}
}
