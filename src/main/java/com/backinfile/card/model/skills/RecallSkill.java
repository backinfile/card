package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.RecallAction;
import com.backinfile.card.model.cards.Chap2ActionCard.Recall;
import com.backinfile.card.model.cards.MonsterCard.Cat;

public class RecallSkill extends Skill {
	public RecallSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Defend, SkillAura.Hero, 0);
	}

	@Override
	public boolean triggerable() {
		// 手牌中需要有recall
		if (human.handPile.filter(c -> c instanceof Recall).isEmpty()) {
			return false;
		}
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
		// 对手牌堆中的骚扰
		if (!human.getOpponent().drawPile.filter(c -> c instanceof Cat && c.oriHumanToken.equals(human.token))
				.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void apply() {
		AttackAction attackAction = param.get("attackAction");
		addFirst(new RecallAction(human, attackAction));
	}
}
