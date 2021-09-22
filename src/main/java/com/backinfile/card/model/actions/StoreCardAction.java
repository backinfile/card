package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.support.Log;

public class StoreCardAction extends WaitAction {
	private boolean fast;
	private Mode mode = Mode.EmptySlot;

	private static enum Mode {
		EmptySlot, // 补充空位
		Replace, // 替换
	}

	public StoreCardAction(Human human, Card card, boolean fast) {
		this.human = human;
		this.card = card;
		this.fast = fast;
	}

	public StoreCardAction(Human human, Card card) {
		this(human, card, false);
	}

	@Override
	public void init() {
		Log.game.error("TODO");
	}

	@Override
	public void pulse() {
	}
}
