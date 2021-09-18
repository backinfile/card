package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.DiscardCardAction;

// 强攻释放两张储备技能
public class PlayAttackSkill extends Skill {
	public PlayAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
	}

	@Override
	public void apply() {
		// TODO
		addLast(new DiscardCardAction(human, card));
	}
}
