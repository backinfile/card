package com.backinfile.card.view.viewActions;

import com.backinfile.card.gen.GameMessageHandler.CSSelectEmptySlot;
import com.backinfile.card.gen.GameMessageHandler.SCSelectEmptySlot;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.view.group.boardView.ButtonInfo;
import com.backinfile.support.Utils;

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

		if (data.getCancel()) {
			var buttonInfo = new ButtonInfo();
			buttonInfo.index = 0;
			buttonInfo.text = Utils.isNullOrEmpty(data.getCancelTip()) ? LocalString.getUIString("boardUIView").strs[2]
					: data.getCancelTip();
			buttonInfo.callback = () -> {
				onSelectIndex(-1);
			};
			gameStage.buttonsView.setButtonInfos(buttonInfo);
		}
	}

	private void onSelectIndex(int index) {
		gameStage.boardView.cardGroupView.hideAllHelpCard();
		gameStage.boardView.boardUIView.setTipText("");
		gameStage.buttonsView.setButtonInfos();

		CSSelectEmptySlot msg = new CSSelectEmptySlot();
		msg.setSelected(index);
		gameStage.boardView.gameClient.sendMessage(msg);
		setDone();
	}

}
