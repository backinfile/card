package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToHarassAction;
import com.backinfile.card.model.cards.MonsterCard.Cat;

public class HarassSkill extends Skill {

	public HarassSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
	}

	// 必须要有骚扰技能的储备
	// 对面有封印
	@Override
	public boolean triggerable() {

		var allStoreCards = human.getAllStoreCards(true, true, false, false, false);
		if (allStoreCards.isEmpty()) {
			return false;
		}

		// 如果对面有封印即可
		if (!human.getOpponent().getAllSealCard(true).isEmpty()) {
			return true;
		}

		// 有猫也可
		if (allStoreCards.stream().anyMatch(c -> c instanceof Cat)) {
			return true;
		}
		return false;
	}

	@Override
	public void apply() {
		addLast(new SelectToHarassAction(human));
	}

}
