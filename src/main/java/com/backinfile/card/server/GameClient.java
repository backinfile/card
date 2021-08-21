package com.backinfile.card.server;

import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.boards.StandaloneBoard;
import com.backinfile.card.server.proto.DBoardInit;
import com.backinfile.card.server.proto.DHumanOper;
import com.backinfile.card.server.proto.DRoom;
import com.backinfile.support.IAlive;
import com.backinfile.support.IdAllot;
import com.backinfile.support.MsgConsumer;

public abstract class GameClient extends MsgConsumer<DHumanOper> implements IAlive {
	public static GameClient Instance;

	public GameClient() {
		IdAllot.reset();
	}


	@Override
	public void pulse() {
	}
}
