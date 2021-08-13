package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.support.IAlive;

public abstract class Board implements IAlive {
	public List<Human> heros = new ArrayList<>();
	public Human curTurnHero;
	public Human curActionHero;
	public ActionQueue actionQueue;

	@Override
	public void init() {
		actionQueue = new ActionQueue(this);
		actionQueue.init();
	}

	@Override
	public void pulse() {
		while (!actionQueue.isEmpty()) {
			actionQueue.pulse();
		}
	}

}
