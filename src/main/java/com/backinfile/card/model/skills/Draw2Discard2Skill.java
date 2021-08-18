package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.PassCardAction;

public class Draw2Discard2Skill extends Skill {

	@Override
	public void apply() {
		addLast(new PassCardAction(human, 2));
	}

}
