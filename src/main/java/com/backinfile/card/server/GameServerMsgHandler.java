package com.backinfile.card.server;

import com.backinfile.card.server.proto.IMsgHandler;
import com.backinfile.card.server.proto.DChat.CSChat;
import com.backinfile.card.server.proto.DPlayer.CSOnlinePulse;
import com.backinfile.card.server.proto.DRoom.CSRoomCreate;

public class GameServerMsgHandler implements IMsgHandler {

	private GameServer gameServer;

	public GameServerMsgHandler(GameServer gameServer) {
		this.gameServer = gameServer;
	}

	public void handleMsg(CSOnlinePulse msg) {

	}

	public void handleMsg(CSChat msg) {
	}

	public void handleMsg(CSRoomCreate msg) {
	}
}
