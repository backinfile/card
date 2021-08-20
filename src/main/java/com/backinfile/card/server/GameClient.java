package com.backinfile.card.server;

import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.boards.StandaloneBoard;
import com.backinfile.card.server.proto.DBoardInit;
import com.backinfile.card.server.proto.DHumanOper;
import com.backinfile.support.IAlive;
import com.backinfile.support.IdAllot;
import com.backinfile.support.MsgConsumer;

public abstract class GameClient extends MsgConsumer<DHumanOper> implements IAlive {
	public static GameClient Instance;
	public Board board;

	public GameClient() {
		IdAllot.reset();
	}

	public void startGame(DBoardInit boardInit) {
		board = new StandaloneBoard();
		board.init(boardInit);
	}

	@Override
	public void pulse() {
		if (board != null) {
			board.pulse();
		}
	}

	public Human getLocalHuman() {
		if (board == null) {
			return null;
		}
		for (var human : board.humans) {
			if (human.token.equals(LocalData.instance().token)) {
				return human;
			}
		}
		return null;
	}
}
