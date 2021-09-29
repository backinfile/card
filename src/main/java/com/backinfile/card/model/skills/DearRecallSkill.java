package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToAttackAction;

public class DearRecallSkill extends Skill {
	public DearRecallSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Recall, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {
		addLast(new SelectToAttackAction(human));
	}

}
