package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

// 这个技能附在手牌上时，这张储备牌将视为一张已准备好的储备
public class ActAsStoreSkill extends Skill {
	public ActAsStoreSkill(SkillDuration duration, SkillAura aura) {
		setTriggerType(duration, SkillTrigger.ActAsStore, aura, 0);
	}
}
