package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.StoreCardAction;

public class StoreSelfSkill extends Skill {

	public StoreSelfSkill() {
		duration = SkillDuration.Combat;
		aura = SkillAura.Hand;
		trigger = SkillTrigger.Active;
	}

	@Override
	public void apply() {
		addLast(new StoreCardAction(human, card));
	}
}
