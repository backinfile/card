package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.PassCardAction;

// 人物基础能力，过牌2
public class Pass2CardSkill extends Skill {

	public Pass2CardSkill() {
		super();

		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 1);
	}

	@Override
	public void apply() {
		addLast(new PassCardAction(human, 2));
	}

}
