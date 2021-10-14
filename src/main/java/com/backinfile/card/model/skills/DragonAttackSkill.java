package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.DiscardCardAction;
import com.backinfile.card.model.actions.FlipStoreAction;

public class DragonAttackSkill extends Skill {
	public DragonAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceRelease, SkillAura.AnyWhere, 1);
	}

	// 对手有储备可以翻转
	@Override
	public boolean triggerable() {
		return !human.getOpponent().getAllStoreInSlot(false, false, false, true).isEmpty();
	}

	@Override
	public void apply() {
		addFirst(new FlipStoreAction(human));
		addFirst(new DiscardCardAction(human, card));
	}
}
