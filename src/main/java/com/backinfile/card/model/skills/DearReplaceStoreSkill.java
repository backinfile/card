package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

public class DearReplaceStoreSkill extends Skill {
	public DearReplaceStoreSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceAttackedStore, SkillAura.Slot, 0);
	}

	// 自身已经储备完成
	@Override
	public boolean triggerable() {
		var slot = human.getCardSlotByCard(card);
		if (slot == null) {
			return false;
		}
		if (slot.ready) {
			return true;
		}
		return false;
	}

	@Override
	public void apply() {
	}

}
