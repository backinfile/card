package com.backinfile.card.model.actions;

import java.util.function.Supplier;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.MonsterCard.Cat;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class TakeSpecCardInHandAction extends WaitAction {
	private CardPile cardPile = new CardPile();
	private int minNumber;
	private int maxNumber;
	private Supplier<CardPile> cardPileSupplier;

	public TakeSpecCardInHandAction(Human human, CardPile cardPile, int minNumber, int maxNumber) {
		this.human = human;
		this.cardPile.addAll(cardPile);
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	public TakeSpecCardInHandAction(Human human, Supplier<CardPile> cardPileSupplier, int minNumber, int maxNumber) {
		this.human = human;
		this.cardPileSupplier = cardPileSupplier;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	@Override
	public void init() {
		if (cardPileSupplier != null) {
			cardPile.addAll(cardPileSupplier.get());
		}

		if (cardPile.isEmpty()) {
			setDone();
			return;
		}

		var humanOper = new SelectCardOper(cardPile, Utils.format(actionString.tip, maxNumber), minNumber, maxNumber);
		humanOper.setDetachCallback(() -> {
			onSelected(humanOper.getSelectedPile());
		});
		human.addHumanOper(humanOper);
	}

	private void onSelected(CardPile selected) {
		for (var card : selected) {
			board.removeCard(card);
		}
		human.handPile.addAll(selected);

		// 摸到对手的梦妖
		for (var card : selected) {
			if (card instanceof Cat && !card.oriHumanToken.equals(human.token)) {
				addFirst(new DiscardCardAction(human, human.handPile));
				board.gameLog(human, EGameLogType.Action, actionString.tips[0]);
			}
		}

		addFirst(new ArrangePileAction(human));
		setDone();
	}
}
