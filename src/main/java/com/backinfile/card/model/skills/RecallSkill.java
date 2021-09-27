package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.OptionalAction;
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
		// 对手弃牌堆中
		if (!human.getOpponent().discardPile
				.filter(c -> c instanceof StoreCard && c.oriHumanToken.equals(human.token)).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void apply() {
		AttackAction attackAction = param.get("attackAction");
		var recallAction = new RecallAction(human, card, attackAction);
		addFirst(new OptionalAction(human, recallAction, attackAction, true, skillString.tips[0]));
	}
}
