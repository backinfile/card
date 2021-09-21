package com.backinfile.card.server.local;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCard;
import com.backinfile.card.gen.GameMessageHandler.CSSelectSkillToActive;
import com.backinfile.card.gen.GameMessageHandler.DSyncListener;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;

public class LocalGameServerMessageHandler extends DSyncListener {
	private LocalGameServer gameServer;
	private Board board;
	private String token;

	public LocalGameServerMessageHandler(LocalGameServer gameServer, Board board) {
		this.gameServer = gameServer;
		this.board = board;
		this.token = LocalData.instance().token;
	}

	@Override
	public void onMessage(CSSelectCard data) {
		gameServer.onClientSelectCard(token, data.getCardId());
	}

	@Override
	public void onMessage(CSSelectSkillToActive data) {
		gameServer.onClientUseSkill(token, data.getSkillId());
	}

}
