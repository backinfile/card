package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DCombination;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class BeastTalkerActive1Action extends WaitAction {
	private List<DCombination> combinations = new ArrayList<>();

	public BeastTalkerActive1Action(Human human, List<DCombination> combinations) {
		this.human = human;
		this.combinations.addAll(combinations);
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(human.handPile, actionString.tip, 2, 2, combinations);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				addFirst(new ArrangePileAction(human));
				for (var card : humanOper.getSelectedPile()) {
					addFirst(new StoreCardAction(human, card));
				}
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
