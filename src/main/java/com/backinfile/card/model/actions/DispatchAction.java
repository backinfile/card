package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.ETargetType;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.model.Human;

public class DispatchAction extends WaitAction {
	private List<Human> humans = new ArrayList<>();

	public DispatchAction(List<Human> humans) {
		this.humans.addAll(humans);
	}

	@Override
	public void init() {
		for (var human : humans) {
			human.targetInfo.clear();

			human.targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.HandPile, actionString.tip));
		}
	}

	@Override
	public void pulse() {
		for (var human : new ArrayList<>(humans)) {
			if (human.targetInfo.isSelected()) {
				var targetSelected = human.targetInfo.getTargetSelected();
				addLast(new PutbackHandCardAction(human, targetSelected));
				addLast(new DrawCardAction(human, targetSelected.size()));
				human.targetInfo.clear();
				humans.remove(human);
			}
		}

		if (humans.isEmpty()) {
			setDone();
		}
	}
}
