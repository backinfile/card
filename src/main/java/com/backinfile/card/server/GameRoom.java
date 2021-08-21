package com.backinfile.card.server;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.boards.StandaloneBoard;
import com.backinfile.card.server.proto.DBoardInit;
import com.backinfile.card.server.proto.DHumanInit;
import com.backinfile.card.server.proto.DRoom;
import com.backinfile.support.IAlive;

public class GameRoom implements IAlive {
	public DRoom room;
	public Board board;
	public List<DHumanInit> humanInits = new ArrayList<>();

	public ClietState clietState = ClietState.Normal;

	public static enum ClietState {
		Normal, Room, Game, Select, // 执行targetInfo
		Action, // 执行一项行动
	}

	public void startGame(DBoardInit boardInit) {
		board = new StandaloneBoard();
		board.init(boardInit);
		clietState = ClietState.Game;
	}

	@Override
	public void pulse() {
		board.requireFlushCardView = false;
		switch (clietState) {
		case Normal:
			break;
		case Room: {

			break;
		}
		case Game: {
			board.pulse();
			break;
		}
		case Select: {
			break;
		}
		case Action: {

			break;
		}
		}
	}
}
