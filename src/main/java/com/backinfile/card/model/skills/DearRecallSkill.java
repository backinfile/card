package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.HeartFirePassive2Action;
import com.backinfile.card.model.actions.SelectToAttackAction;
import com.backinfile.card.model.cards.Chap2HeroCard.HeartFire;

public class DearRecallSkill extends Skill {
	public DearRecallSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Recall, SkillAura.AnyWhere, 0);
	}

	@Override
	public void apply() {

		// 焚心之火技能
		if (human.isHero(HeartFire.class)) {
			addFirst(new HeartFirePassive2Action(human));
		}

		addFirst(new SelectToAttackAction(human));
	}

}
