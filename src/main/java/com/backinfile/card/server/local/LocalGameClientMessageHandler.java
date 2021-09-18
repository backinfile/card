package com.backinfile.card.server.local;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.gen.GameMessageHandler.DBoardData;
import com.backinfile.card.gen.GameMessageHandler.DBoardSetup;
import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.gen.GameMessageHandler.DCardInfoList;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;

public class LocalGameClientMessageHandler extends GameMessageHandler.DSyncListener {
	private LocalGameClient gameClient;
	private GameStage gameStage;

	public LocalGameClientMessageHandler(LocalGameClient gameClient, GameStage gameStage) {
		this.gameClient = gameClient;
		this.gameStage = gameStage;
	}

	@Override
	public void onMessage(DBoardSetup data) {
		super.onMessage(data);
		Log.game.info("data = {}", data.getData().toMessage());
	}

	@Override
	public void onMessage(SCSelectCards data) {
		super.onMessage(data);
		Log.game.info("data = {}", data.toMessage());
	}

	@Override
	public void onMessage(DBoardData data) {
		super.onMessage(data);
	}

	@Override
	public void onMessage(DCardInfo data) {
		super.onMessage(data);
	}

	@Override
	public void onMessage(DCardInfoList data) {
		super.onMessage(data);
	}
}
