package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.ConfirmOper;

public class DreamBuilderLandAction extends WaitAction {
	public DreamBuilderLandAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		var humanOper = new ConfirmOper(true, actionString.tip, actionString.tips[0], actionString.tips[1]);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getResult()) {
				human.board.gameLog(human, EGameLogType.Action, actionString.tips[2] + actionString.tips[0]);
				human.getOpponent().turnStartDrawNumber--;
				setDone();
			} else {
				human.board.gameLog(human, EGameLogType.Action, actionString.tips[2] + actionString.tips[1]);
				addLast(new PassCardAction(human.getOpponent(), 4, 4));
				setDone();
			}
		});
		human.addHumanOper(humanOper);
	}
}
