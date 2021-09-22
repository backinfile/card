package com.backinfile.card.server.local;

import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.Human;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.IAlive;
import com.backinfile.support.func.Terminal;

public class LocalGameServer extends Terminal<MessageWarpper, MessageWarpper> implements IAlive {
	public Board board;

	public LocalGameServer() {
	}

	public void init() {
		board = new Board();
	}

	public void startGame(DBoardInit boardInit) {
		board.init(boardInit);
		for (var human : board.humans) {
			sendMessage(human, board.getBoardSetup(human.token));
		}
		board.start(LocalData.instance().token);
	}

	@Override
	public void pulse() {
		// 处理客户端来的消息
		while (hasMsg()) {
			var msg = pollMsg();
			// 本地服务器没有用户相关的消息，都是游戏内消息，这里直接传入board
			board.onMessage(msg.token, msg.content);
		}

		// 正常游戏循环
		board.pulse();

		// 推送消息给客户端
		for (var human : board.humans) {
			for (var msg : human.msgCacheQueue) {
				sendMessage(human, msg);
			}
			human.msgCacheQueue.clear();
		}
	}

	public final void sendMessage(Human human, DSyncBase msg) {
		outputMsg(MessageWarpper.pack(human, msg));
	}
}
