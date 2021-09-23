package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

// 放置威慑
public class SaveThreatenAction extends WaitAction {

	public SaveThreatenAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		if (human.handPile.isEmpty()) {
			addLast(new DiscardThreatenAction(human, 1));
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(human.handPile, actionString.tip, 1);
		humanOper.setDetachCallback(() -> {
			onSelectHandCard(humanOper.getSelectedPile());
		});
		human.addHumanOper(humanOper);
	}

	private void onSelectHandCard(CardPile selectedPile) {
		var card = selectedPile.get(0);
		human.handPile.remove(card);
		human.threatenPile.add(card);
		board.modifyCard(card);
		board.modifyCard(human.handPile);
		setDone();
	}

}
