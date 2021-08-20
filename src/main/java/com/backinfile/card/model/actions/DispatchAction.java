package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;

public class DispatchAction extends WaitAction {
	private List<Human> humans = new ArrayList<>();

	public DispatchAction(List<Human> humans) {
		this.humans.addAll(humans);
	}

	@Override
	public void init() {
		for (var human : humans) {
			human.targetInfo = new TargetInfo(TargetType.Hand, -1, 0, actionString.tip);
			human.clearTargetInfo();
		}

	}

	@Override
	public void pulse() {
		for (var human : new ArrayList<>(humans)) {
			if (human.targetInfo.isSelected()) {
				addFirst(new DrawCardAction(human, human.targetInfo.getSelected().size()));
				addFirst(new PutbackHandCardAction(human, human.targetInfo.getSelected()));
				human.clearTargetInfo();
			}
		}

		if (humans.isEmpty()) {
			setDone();
		}
	}
}
