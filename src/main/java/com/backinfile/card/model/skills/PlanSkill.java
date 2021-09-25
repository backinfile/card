package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToPlanAction;

public class PlanSkill extends Skill {
	public PlanSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.AnyWhere, 1);
	}

	@Override
	public boolean triggerable() {
		if (human.handPile.isEmpty()) {
			return false;
		}
		// 计划区被封印了，不能计划
		CardSlot slot = human.getPlanSlot();
		if (!slot.getPile(ESlotType.Seal).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		addLast(new SelectToPlanAction(human));
	}

}
