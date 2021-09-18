package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;

public class DrawCardAction extends TriggerOnceAction {
	public int number;

	public DrawCardAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	@Override
	public void run() {
		if (number < 1) {
			return;
		}

		// 转化成一张一张的抽
		if (number == 1) {
			// 牌不够抽，就先去洗回弃牌堆
			if (human.drawPile.isEmpty() && !human.discardPile.isEmpty()) {
				addFirst(new DrawCardAction(human, 1));
				addFirst(new ShuffleDiscardPileToDrawPileAction(human));
			} else {
				drawOneCard();
			}
		} else {
			addFirst(new DrawCardAction(human, number - 1));
			addFirst(new DrawCardAction(human, 1));
		}
	}

	private void drawOneCard() {
		var card = human.drawPile.pollTop();
		human.handPile.add(card);
		board.modifyCard(card);
	}
}
