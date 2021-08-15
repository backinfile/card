package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class RestoreActionNumberAction extends TriggerOnceAction {
	public int number;

	public RestoreActionNumberAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void run() {
		human.actionNumber = number;
	}
}
