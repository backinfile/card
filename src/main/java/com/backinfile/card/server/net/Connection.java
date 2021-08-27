package com.backinfile.card.server.net;

import java.util.concurrent.Delayed;

public interface Connection extends Delayed {
	public long getId();

	public void pulse();

	public boolean isAlive();

	public GameMessage getGameMessage();

	public void sendGameMessage(GameMessage gameMessage);

	public void close();

}
