package com.backinfile.card.server;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.gen.GameMessageHandler.DPlayer;
import com.backinfile.support.IAlive;
import com.backinfile.support.Utils;
import com.backinfile.support.func.Terminal;

/**
 * GameServer管理游戏棋盘，产生数据->转string->传输到client->dSync解析
 * GameClient产生玩家操作->转string->传输到Server->dSync解析
 */
@SuppressWarnings("unused")
public class GameServer extends Terminal<String, String> implements IAlive {
	public static GameServer Instance;

	private Map<String, GameRoom> gameRooms = new HashMap<>();
	private Map<String, Player> onlinePlayers = new HashMap<>();

	public GameServer() {
		Instance = this;
	}

	@Override
	public void pulse() {
		pulseInput();
	}

	private void pulseInput() {
		while (hasMsg()) {
			var input = pollMsg();
			if (input != null) {
				// TODO
			}
		}
	}

	public void playerUpdate(DPlayer player) {
		if (Utils.isNullOrEmpty(player.getToken())) {
			return;
		}
//		if (!onlinePlayers.containsKey(player.getToken())) {
//			onlinePlayers.put(player.getToken(), player);
//		}
	}

}
