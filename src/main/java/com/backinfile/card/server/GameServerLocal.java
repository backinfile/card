package com.backinfile.card.server;

import com.backinfile.card.model.Board;

// 进行一局本地游戏
public class GameServerLocal {
	private Board board;

	public void init() {
		board = new Board();
		board.init(null);
	}
}
