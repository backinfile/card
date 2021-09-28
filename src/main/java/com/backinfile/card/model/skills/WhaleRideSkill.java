package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToDiscardHandAction;
import com.backinfile.card.model.actions.UnsealAction;
import com.backinfile.card.model.cards.ActionCard;

public class WhaleRideSkill extends Skill {
	public WhaleRideSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Slot, 1, 1);
	}

	// 在储备位上，坐骑状态下有东西才可以使用
	@Override
	public boolean triggerable() {
		CardSlot slot = human.getCardSlotByCard(card);
		if (slot == null) {
			return false;
		}
		if (slot.getPile(ESlotType.Store).contains(card)) {
			if (slot.getPile(ESlotType.Ride).isEmpty()) {
				return false;
			}
			if (!human.handPile.filter(c -> c instanceof ActionCard).isEmpty()) {
				if (!human.getAllUnSealableCard().isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void apply() {
		addLast(new SelectToDiscardHandAction(human, 1, c -> c instanceof ActionCard));
		addLast(new UnsealAction(human, 1));
	}
}
