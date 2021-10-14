package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SaveMarkAction;

public class BlackTurtleRecallSkill extends Skill {
	public BlackTurtleRecallSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.HeroRecall, SkillAura.Hero, 0);
	}

	@Override
	public void apply() {
		addFirst(new SaveMarkAction(human, () -> human.handPile));
	}
}
