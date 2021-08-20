package com.backinfile.card.server;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.server.proto.DPlayer;
import com.backinfile.card.server.proto.ProtoManager;
import com.backinfile.support.IAlive;
import com.backinfile.support.MsgConsumer;
import com.backinfile.support.Utils;

public class GameServer extends MsgConsumer<String> implements IAlive {
	public static GameServer Instance;
	private GameServerMsgHandler gameServerMsgHandler;

	private Map<String, GameRoom> gameRooms = new HashMap<>();
	private Map<String, DPlayer> onlinePlayers = new HashMap<>();

	public GameServer() {
		Instance = this;
		gameServerMsgHandler = new GameServerMsgHandler(this);
	}

	@Override
	public void pulse() {
		pulseInput();
	}

	private void pulseInput() {
		while (hasMsg()) {
			var input = pollMsg();
			if (input != null) {
				ProtoManager.callMsgHandler(gameServerMsgHandler, input);
			}
		}
	}

	public void playerUpdate(DPlayer player) {
		if (Utils.isNullOrEmpty(player.token)) {
			return;
		}
		if (!onlinePlayers.containsKey(player.token)) {
			onlinePlayers.put(player.token, player);
		}
	}

}
