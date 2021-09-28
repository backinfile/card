package com.backinfile.card.model.actions;

import com.backinfile.card.model.Board.BoardState;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.chapter2.MonsterCard.Cat;

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
		board.modifyCard(human.handPile);

		if (card instanceof Cat && !card.oriHumanToken.equals(human.token)) {
			addFirst(new DiscardCardAction(human, human.handPile));
		}

		if (board.state == BoardState.TurnStart) {
			human.drawnCardsInTurnStart.add(card);
		}
	}
}
