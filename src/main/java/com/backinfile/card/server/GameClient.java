package com.backinfile.card.server;

import com.backinfile.support.IAlive;
import com.backinfile.support.IdAllot;

public abstract class GameClient implements IAlive {
	public static GameClient Instance;

	public GameClient() {
		IdAllot.reset();
	}

	@Override
	public void pulse() {
	}
}
