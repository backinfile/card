package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToRideAction;
import com.backinfile.card.model.cards.chapter2.MonsterCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard.MonsterSkillType;

public class RideSkill extends Skill {
	public RideSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
	}

	@Override
	public boolean triggerable() {
		// 计划牌里的储备也可以直接骑乘
		for (var card : human.getAllStoreInSlot(true, true, false, false)) {
			if (card instanceof MonsterCard) {
				if (((MonsterCard) card).isMonsterType(MonsterSkillType.Ride)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void apply() {
		addLast(new SelectToRideAction(human, card));
	}
}
