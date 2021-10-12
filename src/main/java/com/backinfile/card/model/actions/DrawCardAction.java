package com.backinfile.card.model.actions;

import java.util.function.Supplier;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Board.BoardState;
import com.backinfile.card.model.cards.Chap2HeroCard.DreamBuilder;
import com.backinfile.card.model.cards.MonsterCard.Cat;
import com.backinfile.card.model.Human;

public class DrawCardAction extends TriggerOnceAction {
	private Supplier<Integer> numberSupplier;

	public DrawCardAction(Human human, int number) {
		this.human = human;
		this.number = number;
	}

	public DrawCardAction(Human human, Supplier<Integer> numberSupplier) {
		this.human = human;
		this.numberSupplier = numberSupplier;
	}

	@Override
	public void init() {
		if (numberSupplier != null) {
			this.number = numberSupplier.get();
		}
		super.init();
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

		if (board.state != BoardState.GamePrepare) {
			// 摸到对手的梦妖
			if (card instanceof Cat && !card.oriHumanToken.equals(human.token)) {
				addFirst(new DiscardCardAction(human, human.handPile));
				board.gameLog(human, EGameLogType.Action, actionString.tips[0]);
			}

			// 筑梦师摸到梦妖
			if (human.isHero(DreamBuilder.class) && card instanceof Cat && card.oriHumanToken.equals(human.token)) {
				addFirst(new DreamBuilderDrawCatAction(human, card));
			}
		}

		// 记录回合开始阶段抽到的牌
		if (board.state == BoardState.TurnStart) {
			human.drawnCardsInTurnStart.add(card);
		}
	}
}
