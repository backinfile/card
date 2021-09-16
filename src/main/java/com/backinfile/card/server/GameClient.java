package com.backinfile.card.server;

import com.backinfile.support.IAlive;
import com.backinfile.support.IdAllot;

/**
 * 进行一局游戏 <br/>
 * 负责桥接server与gameStage <br/>
 * 接受到server传来的消息后， 解析消息，并转到gameStage
 */
public class GameClient implements IAlive {
	public static GameClient Instance;

	public GameClient() {
		IdAllot.reset();
	}

	@Override
	public void pulse() {
	}
}
