package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

// 普通的骚扰技能
public class HarassAction extends WaitAction {
	public HarassAction(Human human, Card card) {
		this.human = human;
		this.card = card;
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.getOpponent().getAllSealCard(true), actionString.tips[0], 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelectSeal(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelectSeal(Card seal) {
		board.removeCard(card);
		var slot = human.getOpponent().getCardSlotByCard(seal);
		slot.getPile(ESlotType.Harass).add(card);
		board.modifyCard(card);
	}
}
