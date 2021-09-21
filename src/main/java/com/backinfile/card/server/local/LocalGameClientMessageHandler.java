package com.backinfile.card.server.local;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.gen.GameMessageHandler.DBoardData;
import com.backinfile.card.gen.GameMessageHandler.DBoardSetup;
import com.backinfile.card.gen.GameMessageHandler.DCardInfoList;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.gen.GameMessageHandler.SCSelectSkillToActive;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.card.view.viewActions.MoveCardViewAction;
import com.backinfile.card.view.viewActions.SelectCardSkillViewAction;
import com.backinfile.card.view.viewActions.SelectCardViewAction;
import com.backinfile.card.view.viewActions.UpdateBoardDataViewAction;

public class LocalGameClientMessageHandler extends GameMessageHandler.DSyncListener {
	private LocalGameClient gameClient;
	private GameStage gameStage;

	public LocalGameClientMessageHandler(LocalGameClient gameClient, GameStage gameStage) {
		this.gameClient = gameClient;
		this.gameStage = gameStage;
	}

	@Override
	public void onMessage(DBoardSetup data) {
		// 更新卡牌位置
		gameStage.addViewAction(new UpdateBoardDataViewAction(data.getData()));
		gameStage.addViewAction(new MoveCardViewAction(data.getCardInfos().getCardsList(), true));

	}

	@Override
	public void onMessage(SCSelectCards data) {
		gameStage.addViewAction(new SelectCardViewAction(data.getCardIdsList(), data.getCancel()));
	}

	@Override
	public void onMessage(DBoardData data) {
		gameStage.addViewAction(new UpdateBoardDataViewAction(data));
	}

	@Override
	public void onMessage(SCSelectSkillToActive data) {
		gameStage.addViewAction(new SelectCardSkillViewAction(data.getSkillInfosList()));
	}

	@Override
	public void onMessage(DCardInfoList data) {
		super.onMessage(data);
		gameStage.addViewAction(new MoveCardViewAction(data.getCardsList(), false));
	}
}
