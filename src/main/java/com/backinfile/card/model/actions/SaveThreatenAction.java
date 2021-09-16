package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ETargetType;
import com.backinfile.card.manager.GameUtils;

public class SaveThreatenAction extends WaitAction {

	@Override
	public void init() {
		human.targetInfo.clear();
		human.targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.HandPile, 1, actionString.tip));
	}

	@Override
	public void pulse() {
		if (!human.targetInfo.isSelected()) {
			return;
		}
		human.handPile.removeAll(human.targetInfo.getTargetSelected());
		human.markPile.addAll(human.targetInfo.getTargetSelected());
	}

	@Override
	public void dispose() {
		human.targetInfo.clear();
	}
}
