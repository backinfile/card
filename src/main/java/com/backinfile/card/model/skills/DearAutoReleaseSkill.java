package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.ArrangePileAction;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.HeartFirePassive1Action;
import com.backinfile.card.model.cards.Chap2HeroCard.HeartFire;

public class DearAutoReleaseSkill extends Skill {
	public DearAutoReleaseSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Store, 1);
		marks.add(SkillMark.Release);
	}

	@Override
	public void apply() {
		addLast(new AttackAction(human, card, human.getOpponent()));
		addLast(new ArrangePileAction(human));

		if (human.isHero(HeartFire.class)) {
			addLast(new HeartFirePassive1Action(human));
		}
	}
}
