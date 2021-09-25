package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToHarassAction;

public class HarassSkill extends Skill {

	public HarassSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
	}

	// 必须要有骚扰技能的储备
	// 对面有封印
	@Override
	public boolean triggerable() {
		// 如果对面有封印，则有储备即可
		if (!human.getOpponent().getAllSealCard(true).isEmpty()) {
			return !human.getAllStoreCards(true, true, false, false, false).isEmpty();
		}

		// TODO 特殊处理：有猫也可
		return false;
	}

	@Override
	public void apply() {
		addLast(new SelectToHarassAction(human));
	}

}
