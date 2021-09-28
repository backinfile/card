package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToMarkStoreAction;
import com.backinfile.card.model.cards.chapter2.MonsterCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard.MonsterSkillType;

public class BirdRideSkill extends Skill {
	public BirdRideSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Slot, 0, 1);
	}

	@Override
	public boolean triggerable() {
		return !human.handPile.filter(c -> {
			if (c instanceof MonsterCard) {
				return ((MonsterCard) c).isMonsterType(MonsterSkillType.Harass);
			}
			return false;
		}).isEmpty();
	}

	@Override
	public void apply() {
		addLast(new SelectToMarkStoreAction(human, human.handPile.filter(c -> {
			if (c instanceof MonsterCard) {
				return ((MonsterCard) c).isMonsterType(MonsterSkillType.Harass);
			}
			return false;
		})));
	}

}
