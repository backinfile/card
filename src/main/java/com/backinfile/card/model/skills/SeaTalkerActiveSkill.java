package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToDiscardHandAction;
import com.backinfile.card.model.actions.UnsealAction;
import com.backinfile.card.model.cards.MonsterCard.Whale;

public class SeaTalkerActiveSkill extends Skill {
	public SeaTalkerActiveSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 1);
		marks.add(SkillMark.Unseal);
	}

	@Override
	public boolean triggerable() {
		if (human.handPile.filter(Whale.class).isEmpty()) {
			return false;
		}
		if (human.getAllSealCard(false).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		addLast(new SelectToDiscardHandAction(human, 1, c -> c instanceof Whale));
		addLast(new UnsealAction(human, 1));
	}
}
