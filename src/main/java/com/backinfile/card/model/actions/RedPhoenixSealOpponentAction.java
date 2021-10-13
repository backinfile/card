package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.server.humanOper.ConfirmOper;

public class RedPhoenixSealOpponentAction extends WaitAction {
	public RedPhoenixSealOpponentAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		if (human.markPile.isEmpty() || human.handPile.filter(ActionCard.class).isEmpty()) {
			setDone();
			return;
		}

		var humanOper = new ConfirmOper(true, actionString.tips[0]);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getResult()) {
				onConfirm();
			} else {
				setDone();
			}
		});
		human.addHumanOper(humanOper);
	}

	private void onConfirm() {
		board.gameLog(human, EGameLogType.Skill, actionString.tips[1]);
		addFirst(new SelectToHarassAction(human, human.markPile));
		addFirst(new SelectToDiscardHandAction(human, 1, c -> c instanceof ActionCard));
		setDone();
	}

}
