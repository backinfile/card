package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

public class EmptySkill extends Skill {
	public static final EmptySkill Instance = new EmptySkill();

	public EmptySkill() {
		super(-1, ConstSkill.N_EMPTY);
	}

}
