package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Human;

public class HeartFirePassive1Action extends TriggerOnceAction {

	public HeartFirePassive1Action(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		board.gameLog(human, EGameLogType.Action, actionString.tips[0]);
		addLast(new DrawCardAction(human, 1));
		addLast(new GainAPAction(human, 1));
	}

}
