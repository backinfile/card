package com.backinfile.card.model.skills;

import com.backinfile.card.Res;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.PassCardAction;

public class Draw2Discard2Skill extends Skill {

	public Draw2Discard2Skill(long id) {
		super(id, Res.SKILL_D2D2);
	}

	@Override
	public void apply() {
		addLast(new PassCardAction(human, 2));
	}

}
