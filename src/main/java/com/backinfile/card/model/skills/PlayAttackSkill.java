package com.backinfile.card.model.skills;

import com.backinfile.card.Res;
import com.backinfile.card.model.Skill;

public class PlayAttackSkill extends Skill {

	public PlayAttackSkill(long id) {
		super(id, Res.SKILL_PLAY);

		passive = false;
	}

	@Override
	public boolean canActive() {
		return human.actionNumber > 0 && !human.getAllStoreCards(true).isEmpty();
	}
	
	@Override
	public void apply() {
		
	}
}
