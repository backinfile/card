package com.backinfile.card.model.skills;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.TakeSpecCardInHandAction;
import com.backinfile.card.model.cards.MonsterCard.Bee;

public class BeeKeeperActiveSkill extends Skill {
	public BeeKeeperActiveSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 1);
	}

	@Override
	public boolean triggerable() {
		// 需要储备区有蜂
		if (human.getAllStoreInSlot(true, true, false, false).filter(Bee.class).isEmpty()) {
			return false;
		}
		if (!human.drawPile.filter(Bee.class).isEmpty()) {
			return true;
		}
		if (!human.discardPile.filter(Bee.class).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void apply() {
		var selectFrom = new CardPile();
		selectFrom.addAll(human.drawPile.filter(Bee.class));
		selectFrom.addAll(human.discardPile.filter(Bee.class));
		addLast(new TakeSpecCardInHandAction(human, selectFrom, 0, 2));
	}
}
