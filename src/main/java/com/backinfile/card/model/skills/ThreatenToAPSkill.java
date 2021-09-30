package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.DiscardThreatenAction;
import com.backinfile.card.model.actions.GainAPAction;

public class ThreatenToAPSkill extends Skill {

	public ThreatenToAPSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 0, 1);
	}

	@Override
	public boolean triggerable() {
		return human.threatenPile.size() >= 5;
	}

	@Override
	public void apply() {
		if (human.threatenPile.size() >= 10) {
			addLast(new DiscardThreatenAction(human, 1));
			addLast(new GainAPAction(human, 1));
		} else if (human.threatenPile.size() >= 5) {
			addLast(new DiscardThreatenAction(human, 2));
			addLast(new GainAPAction(human, 1));
		}
	}

}
