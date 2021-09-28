package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.ArrangePileAction;
import com.backinfile.card.model.actions.RefreshSlotAction;

public class CatHarassSkill extends Skill {

	public CatHarassSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceHarass, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {
		addFirst(new RefreshSlotAction());
		addFirst(new ArrangePileAction(human));
		addFirst(new Put2OpponentDrawPileAction(human, card));
	}

}
