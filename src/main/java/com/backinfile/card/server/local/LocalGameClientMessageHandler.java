package com.backinfile.card.server.local;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.gen.GameMessageHandler.CSSelectOption;
import com.backinfile.card.gen.GameMessageHandler.DBoardData;
import com.backinfile.card.gen.GameMessageHandler.DBoardSetup;
import com.backinfile.card.gen.GameMessageHandler.DCardInfoList;
import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.gen.GameMessageHandler.SCGameLog;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.gen.GameMessageHandler.SCSelectConfirm;
import com.backinfile.card.gen.GameMessageHandler.SCSelectEmptySlot;
import com.backinfile.card.gen.GameMessageHandler.SCSelectOption;
import com.backinfile.card.gen.GameMessageHandler.SCSelectSkillToActive;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.card.view.viewActions.ConfirmViewAction;
import com.backinfile.card.view.viewActions.MoveCardViewAction;
import com.backinfile.card.view.viewActions.SelectCardSkillViewAction;
import com.backinfile.card.view.viewActions.SelectCardViewAction;
import com.backinfile.card.view.viewActions.SelectEmptySlotViewAction;
import com.backinfile.card.view.viewActions.SelectOptionViewAction;
import com.backinfile.card.view.viewActions.UpdateBoardDataViewAction;
import com.backinfile.support.Log;

@SuppressWarnings("unused")
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
		gameStage.addViewAction(new UpdateBoardDataViewAction(data.getCardInfos().getData()));
		gameStage.addViewAction(new MoveCardViewAction(data.getCardInfos().getCardsList(), true));

	}

	@Override
	public void onMessage(SCSelectCards data) {
		gameStage.addViewAction(new SelectCardViewAction(data));
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
		gameStage.addViewAction(new UpdateBoardDataViewAction(data.getData()));
		gameStage.addViewAction(new MoveCardViewAction(data.getCardsList(), false));
	}

	@Override
	public void onMessage(SCSelectEmptySlot data) {
		gameStage.addViewAction(new SelectEmptySlotViewAction(data));
	}

	@Override
	public void onMessage(SCSelectOption data) {
		gameStage.addViewAction(new SelectOptionViewAction(data));
	}

	@Override
	public void onMessage(SCSelectConfirm data) {
		gameStage.addViewAction(new ConfirmViewAction(data));
	}

	@Override
	public void onMessage(SCGameLog data) {
		String log = "#" + getTypeName(data.getType()) + "#\n";
		log += data.getPlayerName();
		log += data.getLog();
		gameStage.boardView.boardLogScrollView.addLog(log);
	}

	private String getTypeName(EGameLogType type) {
		switch (type) {
		case Action:
			return LocalString.getUIString("BoardLogic").strs[8];
		case Skill:
			return LocalString.getUIString("BoardLogic").strs[7];
		case Turn:
			return LocalString.getUIString("BoardLogic").strs[6];
		default:
			break;
		}
		return type.name().toUpperCase();
	}
}
