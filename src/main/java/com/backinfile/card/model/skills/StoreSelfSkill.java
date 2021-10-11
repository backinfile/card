package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.ArrangePileAction;
import com.backinfile.card.model.actions.StoreCardAction;

// 储备牌 储备本身的技能
public class StoreSelfSkill extends Skill {

	public StoreSelfSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
	}

	@Override
	public void apply() {
		addLast(new StoreCardAction(human, card, false, true, 1));
		addLast(new ArrangePileAction(human));
	}
}
