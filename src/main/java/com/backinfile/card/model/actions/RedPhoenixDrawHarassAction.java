package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.StoreCard;
import com.backinfile.card.server.humanOper.ConfirmOper;

public class RedPhoenixDrawHarassAction extends WaitAction {
	public RedPhoenixDrawHarassAction(Human human, Card harass) {
		this.human = human;
		this.card = harass;
	}

	@Override
	public void init() {
		var humanOper = new ConfirmOper(true, actionString.tip);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getResult()) {
				board.gameLog(human, EGameLogType.Action, actionString.tips[0], card.cardString.name);
				addFirst(new SaveMarkAction(human, human.handPile.filter(StoreCard.class)));
				addFirst(new ArrangePileAction(human));
				addFirst(new DiscardCardAction(human, card));
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
