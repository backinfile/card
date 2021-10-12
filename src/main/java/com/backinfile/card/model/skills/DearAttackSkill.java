package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.HeartFirePassive2Action;
import com.backinfile.card.model.actions.SelectToStoreAction;
import com.backinfile.card.model.cards.Chap2HeroCard.HeartFire;

public class DearAttackSkill extends Skill {
	public DearAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceRelease, SkillAura.Slot, 0);
	}

	@Override
	public void apply() {
		// 焚心之火技能
		if (human.isHero(HeartFire.class)) {
			addFirst(new HeartFirePassive2Action(human));
		}

		addFirst(new SelectToStoreAction(human, 1, true));
		addFirst(new AttackAction(human, card, human.getOpponent()));
	}

}
