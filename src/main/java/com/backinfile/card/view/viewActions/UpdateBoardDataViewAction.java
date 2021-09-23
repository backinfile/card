package com.backinfile.card.view.viewActions;

import com.backinfile.card.gen.GameMessageHandler.DBoardData;
import com.backinfile.card.manager.LocalData;

public class UpdateBoardDataViewAction extends ViewAction {
	private DBoardData boardData;

	public UpdateBoardDataViewAction(DBoardData boardData) {
		this.boardData = boardData;
	}

	@Override
	public void begin() {
		gameStage.boardView.mulPileView.setPileNumber(boardData.getPileNumbersList());
		gameStage.boardView.boardUIView.setData(LocalData.instance().name, boardData.getOpponentPlayerName());
		gameStage.boardView.boardUIView.setActionPoint(boardData.getActionPoint());
		setDone();
	}
}
