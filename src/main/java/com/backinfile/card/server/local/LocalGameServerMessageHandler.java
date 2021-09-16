package com.backinfile.card.server.local;

import com.backinfile.card.gen.GameMessageHandler.DSyncListener;
import com.backinfile.card.model.Board;

public class LocalGameServerMessageHandler extends DSyncListener {
	private LocalGameServer gameServer;
	private Board board;

	public LocalGameServerMessageHandler(LocalGameServer gameServer) {
		this.gameServer = gameServer;
		this.board = gameServer.board;
	}

}
