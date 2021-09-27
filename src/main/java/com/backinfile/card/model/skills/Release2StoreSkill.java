package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.ArrangePileAction;
import com.backinfile.card.model.actions.DiscardCardAction;
import com.backinfile.card.model.actions.SelectToAttackAction;

// 强攻释放两张储备技能
public class Release2StoreSkill extends Skill {
	public Release2StoreSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
		marks.add(SkillMark.Release);
	}

	@Override
	public boolean triggerable() {
		if (human.getAllStoreCards(true, true, false, false, false).isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		addLast(new DiscardCardAction(human, card));
		addLast(new ArrangePileAction(human));
		addLast(new SelectToAttackAction(human, human.getOpponent(), 1, 2));
	}
}
