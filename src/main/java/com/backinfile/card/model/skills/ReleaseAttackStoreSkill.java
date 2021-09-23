package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.ArrangePileAction;
import com.backinfile.card.model.actions.DiscardCardAction;
import com.backinfile.card.model.actions.SelectToAttackAction;

public class ReleaseAttackStoreSkill extends Skill {
	public ReleaseAttackStoreSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
	}

	@Override
	public boolean triggerable() {
		if (human.getAllStoreCards(true, true, false, false).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		addLast(new DiscardCardAction(human, card));
		addLast(new ArrangePileAction(human));
		addLast(new SelectToAttackAction(human, human.getOpponent(), 1, true));
	}
}
