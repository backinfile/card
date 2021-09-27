package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.RecallAction;
import com.backinfile.card.model.cards.StoreCard;

public class RecallSkill extends Skill {
	public RecallSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Defend, SkillAura.Hand, 0);
	}

	@Override
	public boolean triggerable() {
		// 储备
		if (!human.getAllStoreCards(true, true, false, false, false).isEmpty()) {
			return true;
		}
		// 骚扰
		for (var slot : human.getOpponent().cardSlotMap.values()) {
			if (!slot.getPile(ESlotType.Harass).isEmpty()) {
				return true;
			}
		}
		if (!human.getOpponent().discardPile.getFiltered(c -> c instanceof StoreCard).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void apply() {
		AttackAction attackAction = param.get("attackAction");
		addFirst(new RecallAction(human, card, attackAction));
	}
}
