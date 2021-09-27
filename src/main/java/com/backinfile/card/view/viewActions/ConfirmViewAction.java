package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.CSSelectConfirm;
import com.backinfile.card.gen.GameMessageHandler.SCSelectConfirm;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.view.group.boardView.ButtonInfo;
import com.backinfile.support.Utils;

public class ConfirmViewAction extends ViewAction {
	private SCSelectConfirm msg;

	public ConfirmViewAction(SCSelectConfirm msg) {
		this.msg = msg;
	}

	@Override
	public void begin() {
		List<ButtonInfo> buttonInfos = new ArrayList<ButtonInfo>();
		{
			ButtonInfo buttonInfo = new ButtonInfo();
			buttonInfo.index = 0;
			buttonInfo.text = Utils.isNullOrEmpty(msg.getConfirmTip()) ? LocalString.getUIString("boardUIView").strs[4]
					: msg.getConfirmTip();
			buttonInfo.callback = () -> {
				onConfirm(true);
			};
			buttonInfos.add(buttonInfo);
		}
		if (msg.getCancel()) {
			ButtonInfo buttonInfo = new ButtonInfo();
			buttonInfo.index = 1;
			buttonInfo.text = Utils.isNullOrEmpty(msg.getCancelTip()) ? LocalString.getUIString("boardUIView").strs[2]
					: msg.getCancelTip();
			buttonInfo.callback = () -> {
				onConfirm(false);
			};
			buttonInfos.add(buttonInfo);
		}
		gameStage.buttonsView.setButtonInfos(buttonInfos);
		gameStage.boardView.boardUIView.setTipText(msg.getTip());
	}

	private void onConfirm(boolean confirm) {
		gameStage.buttonsView.setButtonInfos();
		gameStage.boardView.boardUIView.setTipText("");

		CSSelectConfirm msg = new CSSelectConfirm();
		msg.setConfirm(confirm);
		gameStage.boardView.gameClient.sendMessage(msg);
		setDone();
	}
}
