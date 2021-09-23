package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class DispatchAction extends WaitAction {
	private List<Human> humans = new ArrayList<>();
	private HashMap<Human, CardPile> selectedCards = new HashMap<>();

	public DispatchAction(List<Human> humans) {
		this.humans.addAll(humans);
	}

	@Override
	public void init() {
		for (var human : humans) {
			var humanOper = new SelectCardOper(human.handPile, actionString.tip, actionString.tips[0], 0,
					human.handPile.size());
			human.addHumanOper(humanOper);
			humanOper.setDetachCallback(() -> {
				selectedCards.put(human, humanOper.getSelectedPile());
			});
		}
	}

	@Override
	public void pulse() {
		for (var human : new ArrayList<>(humans)) {
			var cardPile = selectedCards.get(human);
			if (cardPile == null) {
				continue;
			}
			humans.remove(human);
			if (!cardPile.isEmpty()) {
				addLast(new DrawCardAction(human, cardPile.size()));
				addLast(new PutbackHandCardAction(human, cardPile));
			}
		}

		if (humans.isEmpty()) {
			setDone();
		}
	}
}
