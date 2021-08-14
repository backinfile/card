package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.Res;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;

public class DispatchAction extends WaitAction {
	private List<Human> humans = new ArrayList<>();
	private boolean asGameStart = false;

	public DispatchAction(List<Human> humans) {
		this(humans, false);
	}

	public DispatchAction(List<Human> humans, boolean asGameStart) {
		this.humans.addAll(humans);
		this.asGameStart = asGameStart;
	}

	@Override
	public void init() {
		for (var human : humans) {
			TargetInfo targetInfo = new TargetInfo(TargetType.Hand);
			targetInfo.number = -1;
			targetInfo.optional = true;
			targetInfo.tip = Res.DispatchActionString;
			human.targetInfo = targetInfo;
			human.selectedPile.clear();
		}

	}

	@Override
	public void pulse() {
		for (var human : new ArrayList<>(humans)) {
			if (!human.selectedPile.isEmpty()) {
				addFirst(new DrawCardAction(human, human.selectedPile.size(), asGameStart));
				addFirst(new PutbackHandCardAction(human, human.selectedPile));
				human.clearSelectInfo();
			}
		}

		if (humans.isEmpty()) {
			isDone = true;
		}
	}
}
