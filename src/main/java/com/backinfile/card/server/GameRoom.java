package com.backinfile.card.server;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.gen.GameMessage.DBoardInit;
import com.backinfile.card.gen.GameMessage.DHumanInit;
import com.backinfile.card.gen.GameMessage.DRoom;
import com.backinfile.card.gen.GameMessage.ERoomStage;
import com.backinfile.card.model.Board;
import com.backinfile.support.IAlive;
import com.backinfile.support.Time2;
import com.backinfile.support.Utils;

public class GameRoom implements IAlive {
	public DRoom room;
	public Board board;
	public DBoardInit boardInit;
	public Map<String, Player> players = new HashMap<>();

	public GameRoom() {
		room = new DRoom();
		room.setToken(Utils.getRandomToken());
		room.setState(ERoomStage.Normal);

		boardInit = new DBoardInit();
	}

	public void addHumanInit(DHumanInit humanInit) {
		boardInit.addHumanInits(humanInit);
		if (boardInit.getHumanInitsCount() == 2) {
			boardInit.setSeed(Time2.getCurMillis());
			startGame(boardInit);
		}
	}

	private void startGame(DBoardInit boardInit) {
		board = new Board();
		board.init(boardInit);
	}

	@Override
	public void pulse() {
		if (board != null) {
			board.pulse();
		}
	}
}
