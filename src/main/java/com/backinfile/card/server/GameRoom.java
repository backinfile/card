package com.backinfile.card.server;


import com.backinfile.card.gen.GameMessage.DBoardInit;
import com.backinfile.card.gen.GameMessage.DRoom;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.boards.StandaloneBoard;
import com.backinfile.support.IAlive;

public class GameRoom implements IAlive {
	public DRoom room;
	public Board board;
	public DBoardInit boardInit;

	public static enum ClietState {
		Normal, Room, Game, Select, // 执行targetInfo
		Action, // 执行一项行动
	}

	public void startGame(DBoardInit boardInit) {
		board = new StandaloneBoard();
		board.init(boardInit);
	}

	@Override
	public void pulse() {
	}
}
