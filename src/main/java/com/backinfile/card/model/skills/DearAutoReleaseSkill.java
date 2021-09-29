package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;

public class DearAutoReleaseSkill extends Skill {
	public DearAutoReleaseSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Slot, 1);
		marks.add(SkillMark.Release);
	}

	// 需要已经储备完成
	@Override
	public boolean triggerable() {
		var slot = human.getCardSlotByCard(card);
		if (slot == null) {
			return false;
		}
		if (!slot.getCardInPile(ESlotType.Store, ESlotType.Plan).contains(card)) {
			return false;
		}
		return slot.ready;
	}

	@Override
	public void apply() {
		addLast(new AttackAction(human, card, human.getOpponent()));
	}
}
