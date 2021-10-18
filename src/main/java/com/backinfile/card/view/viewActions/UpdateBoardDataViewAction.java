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

		var selfName = "";
		var opponentName = "";
		for (var humanData : boardData.getPlayerDatasList()) {
			if (LocalData.instance().token.equals(humanData.getToken())) {
				selfName = "[" + humanData.getActionPoint() + "] " + LocalData.instance().name;
			} else {
				opponentName = "[" + humanData.getActionPoint() + "] " + humanData.getName();
			}
		}
		gameStage.boardView.boardUIView.setData(selfName, opponentName);
		setDone();
	}
}
