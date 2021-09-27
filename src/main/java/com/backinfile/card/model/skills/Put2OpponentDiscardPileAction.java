package com.backinfile.card.model.skills;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.actions.TriggerOnceAction;

public class Put2OpponentDiscardPileAction extends TriggerOnceAction {
	public Put2OpponentDiscardPileAction(Human human, Card card) {
		this.human = human;
		this.card = card;
	}

	@Override
	public void run() {
		board.removeCard(card);
		human.getOpponent().discardPile.add(card);
		board.modifyCard(card);
	}

}
