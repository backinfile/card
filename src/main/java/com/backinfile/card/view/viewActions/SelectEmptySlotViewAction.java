package com.backinfile.card.view.viewActions;

import com.backinfile.card.gen.GameMessageHandler.CSSelectEmptySlot;
import com.backinfile.card.gen.GameMessageHandler.SCSelectEmptySlot;

public class SelectEmptySlotViewAction extends ViewAction {
	private SCSelectEmptySlot data;

	public SelectEmptySlotViewAction(SCSelectEmptySlot data) {
		this.data = data;
	}

	@Override
	public void begin() {
		for (var index : data.getSelectFromList()) {
			gameStage.boardView.cardGroupView.setHelpCard(index, data.getOpponent() ? 1 : 0,
					data.getAimType().ordinal(), true, () -> {
						onSelectIndex(index);
					});
		}
		gameStage.boardView.boardUIView.setTipText(data.getTip());
	}

	private void onSelectIndex(int index) {
		gameStage.boardView.cardGroupView.hideAllHelpCard();
		gameStage.boardView.boardUIView.setTipText("");

		CSSelectEmptySlot msg = new CSSelectEmptySlot();
		msg.setSelected(index);
		gameStage.boardView.gameClient.sendMessage(msg);
		setDone();
	}

}
