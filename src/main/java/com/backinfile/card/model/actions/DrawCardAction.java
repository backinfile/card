package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class DrawCardAction extends TriggerOnceAction {
	public int number;
	public Type type;

	public static enum Type {
		Normal, TurnStart, GameStart,
	}

	public DrawCardAction(Human human, int number) {
		this(human, number, Type.Normal);
	}

	public DrawCardAction(Human human, int number, Type type) {
		this.human = human;
		this.number = number;
		this.type = type;
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
