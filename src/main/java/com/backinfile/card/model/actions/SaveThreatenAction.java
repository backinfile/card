package com.backinfile.card.model.actions;

import com.backinfile.card.Res;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;

public class SaveThreatenAction extends WaitAction {

	@Override
	public void init() {
		human.clearSelectInfo();

		var targetInfo = new TargetInfo(TargetType.Hand);
		targetInfo.number = 1;
		targetInfo.optional = false;
		targetInfo.tip = Res.ACTION_SaveThreatenAction;
		human.targetInfo = targetInfo;
	}

	@Override
	public void pulse() {
		if (human.selectedPile.isEmpty()) {
			return;
		}
		human.handPile.removeAll(human.selectedPile);
		human.markPile.addAll(human.selectedPile);
	}

	@Override
	public void dispose() {
		human.clearSelectInfo();
	}
}
