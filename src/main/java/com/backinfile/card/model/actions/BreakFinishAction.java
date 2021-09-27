package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

// 完成击破事件
public class BreakFinishAction extends TriggerOnceAction {
	public BreakFinishAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		addFirst(new DrawCardAction(human, 1));
	}

}
