package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.manager.Res;

public class MoveCardViewAction extends ViewAction {
	private List<DCardInfo> cardInfoList = new ArrayList<>();
	private boolean set;

	public MoveCardViewAction(List<DCardInfo> cardInfoList, boolean set) {
		this.cardInfoList.addAll(cardInfoList);
		this.set = set;
		this.duration = Res.BASE_DURATION;
	}

	@Override
	public void begin() {
		gameStage.boardView.cardGroupView.updateAllCardInfo(cardInfoList, set);
	}

}
