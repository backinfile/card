package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.SelectToStoreAction;

public class DearAttackSkill extends Skill {
	public DearAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceRelease, SkillAura.Slot, 0);
	}

	@Override
	public void apply() {
		addFirst(new SelectToStoreAction(human, 1, true));
		addFirst(new AttackAction(human, card, human.getOpponent()));
	}

}
