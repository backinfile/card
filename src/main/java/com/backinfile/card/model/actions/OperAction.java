package com.backinfile.card.model.actions;

import com.backinfile.card.model.Skill;

public class OperAction extends TriggerOnceAction {
	private Skill skill;
	private IOperable operable;

	public OperAction(Skill skill, IOperable operable) {
		this.skill = skill;
		this.operable = operable;
	}

	@Override
	public void run() {
		operable.emit(skill);
	}

}
