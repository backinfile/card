package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessage.DTargetInfo;
import com.backinfile.card.gen.GameMessage.ETargetType;
import com.backinfile.card.model.Human;

public class DispatchAction extends WaitAction {
	private List<Human> humans = new ArrayList<>();

	public DispatchAction(List<Human> humans) {
		this.humans.addAll(humans);
	}

	@Override
	public void init() {
		for (var human : humans) {
			DTargetInfo targetInfo = new DTargetInfo();
			targetInfo.setType(ETargetType.CardPile);
			targetInfo.setMaxNumber(-1);
			targetInfo.setMinNumber(0);
			targetInfo.setTip(tip);
			human.targetInfo = new TargetInfo(TargetType.Hand, -1, 0, actionString.tip);
			human.clearTargetInfo();
		}

	}

	@Override
	public void pulse() {
		for (var human : new ArrayList<>(humans)) {
			if (human.isTargetSelected()) {
				var targetSelected = human.getTargetSelected();
				addFirst(new DrawCardAction(human, targetSelected.size()));
				addFirst(new PutbackHandCardAction(human, targetSelected));
				human.clearTargetInfo();
			}
		}

		if (humans.isEmpty()) {
			setDone();
		}
	}
}
