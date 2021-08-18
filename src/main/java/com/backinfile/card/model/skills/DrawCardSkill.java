package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.DrawCardAction;

public class DrawCardSkill extends Skill {
	public int number;

	public DrawCardSkill(int number) {
		this.number = number;
	}

	@Override
	public void apply() {
		board.getActionQueue().addLast(new DrawCardAction(human, number));
	}

}
