package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.ConfirmOper;

public class DreamBuilderDrawCatAction extends WaitAction {
	public DreamBuilderDrawCatAction(Human human, Card card) {
		this.human = human;
		this.card = card;
	}

	@Override
	public void init() {
		human.board.gameLog(human, EGameLogType.Action, actionString.tips[0]);

		var humanOper = new ConfirmOper(true, actionString.tip);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getResult()) {
				onReveal();
			} else {
				setDone();
			}
		});
		human.addHumanOper(humanOper);
	}

	private void onReveal() {

		addFirst(new ShuffleAction(human));
		addFirst(new TakeSpecCardInHandAction(human, human.drawPile, 0, 1));
		setDone();
	}
}
