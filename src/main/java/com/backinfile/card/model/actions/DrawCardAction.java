package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class DrawCardAction extends TriggerOnceAction {
	public int number;
	public boolean asTurnStart;

	public DrawCardAction(Human human, int number) {
		this(human, number, false);
	}

	public DrawCardAction(Human human, int number, boolean asTurnStart) {
		this.human = human;
		this.number = number;
		this.asTurnStart = asTurnStart;
	}

	@Override
	public void run() {
		int firstDrawSize = human.drawPile.size();
		if (firstDrawSize <= number) {
			int leftSize = number - firstDrawSize;
			board.getActionQueue().addFirst(new DrawCardAction(human, leftSize));
			board.getActionQueue().addFirst(new ShuffleDiscardPileToDrawPileAction(human));
			board.getActionQueue().addFirst(new DrawCardAction(human, firstDrawSize));
			return;
		}
	}
}
