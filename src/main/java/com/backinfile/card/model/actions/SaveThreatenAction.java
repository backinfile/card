package com.backinfile.card.model.actions;

import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;

public class SaveThreatenAction extends WaitAction {

	@Override
	public void init() {
		human.clearTargetInfo();

		var targetInfo = new TargetInfo(TargetType.Hand, 1, actionString.tip);
		human.targetInfo = targetInfo;
	}

	@Override
	public void pulse() {
		if (!human.targetInfo.isSelected()) {
			return;
		}
		human.handPile.removeAll(human.targetInfo.getSelected());
		human.markPile.addAll(human.targetInfo.getSelected());
	}

	@Override
	public void dispose() {
		human.clearTargetInfo();
	}
}
