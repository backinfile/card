package com.backinfile.card.server;

import com.backinfile.card.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.boards.StandaloneBoard;
import com.backinfile.card.server.proto.DBoardInit;
import com.backinfile.support.IAlive;
import com.backinfile.support.IdAllot;

public abstract class GameClient implements IAlive {
	public static GameClient Instance;
	public Board board;
	public LocalData localData;

	public GameClient() {
		IdAllot.reset();
		localData = LocalData.load();
	}

	public void startGame(DBoardInit boardInit) {
		board = new StandaloneBoard();
		board.init(boardInit);
	}

	@Override
	public void pulse() {
	}
}
