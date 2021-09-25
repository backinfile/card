package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.support.func.Function0;

public class GainAPAction extends TriggerOnceAction {
	private Function0<Integer> apGetter;

	public GainAPAction(Human human, Function0<Integer> apGetter) {
		this.human = human;
		this.apGetter = apGetter;
	}

	public GainAPAction(Human human, int number) {
		this.human = human;
		this.apGetter = () -> number;
	}

	@Override
	public void run() {
		human.actionPoint += apGetter.invoke();
	}

}
