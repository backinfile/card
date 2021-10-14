package com.backinfile.card.model.actions;

import java.util.function.Supplier;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SaveMarkAction extends WaitAction {
	private CardPile selectFrom = new CardPile();
	private Supplier<CardPile> cardPileSupplier;

	public SaveMarkAction(Human human, CardPile selectFrom) {
		this.human = human;
		this.selectFrom.addAll(selectFrom);
	}

	public SaveMarkAction(Human human, Supplier<CardPile> cardPileSupplier) {
		this.human = human;
		this.cardPileSupplier = cardPileSupplier;
	}

	@Override
	public void init() {
		if (this.cardPileSupplier != null) {
			selectFrom.addAll(cardPileSupplier.get());
		}

		var humanOper = new SelectCardOper(selectFrom, actionString.tip, 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				var card = humanOper.getSelectedPile().getAny();
				board.removeCard(card);
				human.markPile.add(card);
				board.modifyCard(card, human.handPile);
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
