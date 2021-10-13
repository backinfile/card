package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.CSSelectOption;
import com.backinfile.card.gen.GameMessageHandler.SCSelectOption;
import com.backinfile.card.view.group.boardView.ButtonInfo;

public class SelectOptionViewAction extends ViewAction {
	private SCSelectOption msg;

	public SelectOptionViewAction(SCSelectOption msg) {
		this.msg = msg;
	}

	@Override
	public void begin() {
		List<ButtonInfo> buttonInfos = new ArrayList<ButtonInfo>();
		var options = msg.getOptionsList();
		for (int i = 0; i < options.size(); i++) {
			final var index = i;
			var buttonInfo = new ButtonInfo();
			buttonInfo.index = index;
			buttonInfo.text = options.get(index);
			buttonInfo.callback = () -> {
				onSelect(index);
			};
			buttonInfos.add(buttonInfo);
		}
		gameStage.buttonsView.setButtonInfos(buttonInfos);
		gameStage.boardView.boardUIView.setTipText(msg.getTip());
	}

	private void onSelect(int index) {
		gameStage.buttonsView.setButtonInfos();
		gameStage.boardView.boardUIView.setTipText("");

		CSSelectOption msg = new CSSelectOption();
		msg.setIndex(index);
		gameStage.boardView.gameClient.sendMessage(msg);
		setDone();
	}
}
