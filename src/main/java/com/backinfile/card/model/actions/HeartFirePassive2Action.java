package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Human;

public class HeartFirePassive2Action extends TriggerOnceAction {

	public HeartFirePassive2Action(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		board.gameLog(human, EGameLogType.Action, actionString.tips[0]);
		addLast(new RemoveOpponentStoreOrMarkAction(human));
	}

}
