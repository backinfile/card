package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class WhaleRecallAction extends WaitAction {
	public WhaleRecallAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		CardPile toSelect = new CardPile();
		// 目标储备
		toSelect.addAll(human.getOpponent().getAllStoreCards(true, false, false, true, true));
		// 目标标记
		toSelect.addAll(human.getOpponent().markPile);
		// 弃牌
		toSelect.addAll(human.discardPile);

		if (toSelect.isEmpty()) {
			setDone();
			return;
		}

		var humanOper = new SelectCardOper(toSelect, actionString.tip, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelect(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelect(Card returnCard) {
		Human targetHuman = human.getCard(returnCard.id) != null ? human : human.getOpponent();
		board.removeCard(returnCard);
		targetHuman.handPile.add(returnCard);
		board.modifyCard(targetHuman.handPile);
	}
}
