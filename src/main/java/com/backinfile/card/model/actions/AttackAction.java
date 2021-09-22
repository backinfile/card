package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.support.Log;

public class AttackAction extends WaitAction {
	private Human targetHuman;

	public AttackAction(Human human, Card card, Human targetHuman) {
		this.human = human;
		this.card = card;
		this.targetHuman = targetHuman;
	}

	@Override
	public void init() {
		Log.game.error("TODO");
	}

	@Override
	public void pulse() {
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
