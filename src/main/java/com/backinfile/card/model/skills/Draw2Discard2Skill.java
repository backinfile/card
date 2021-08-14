package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.DrawCardAction;
import com.backinfile.card.model.actions.SelfDiscardFromHandAction;

public class Draw2Discard2Skill extends Skill {

	public Draw2Discard2Skill(long id, String name) {
		super(id, name);
	}

	@Override
	public void apply() {
		addLast(new DrawCardAction(human, 2));
		addLast(new SelfDiscardFromHandAction(human, 2));
	}

}
