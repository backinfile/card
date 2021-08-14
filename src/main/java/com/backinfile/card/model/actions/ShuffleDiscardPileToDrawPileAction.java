package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class ShuffleDiscardPileToDrawPileAction extends TriggerOnceAction {

	public ShuffleDiscardPileToDrawPileAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		human.drawPile.addAll(human.discardPile);
		human.discardPile.clear();
		human.drawPile.shuffle();
	}

}
