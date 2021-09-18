package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.DrawCardAction;

// 人物基础技能 抽1
public class DrawCardSkill extends Skill {
	public DrawCardSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 1);
	}

	@Override
	public void apply() {
		board.getActionQueue().addLast(new DrawCardAction(human, 1));
	}

}
