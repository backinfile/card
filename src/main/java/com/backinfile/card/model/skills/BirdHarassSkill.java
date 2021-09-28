package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.HarassAction;
import com.backinfile.card.model.actions.RemoveOpponentHandAction;

public class BirdHarassSkill extends Skill {
	public BirdHarassSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceHarass, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {
		addFirst(new RemoveOpponentHandAction(human, 4, 1));
		addFirst(new HarassAction(human, card));
	}

}
