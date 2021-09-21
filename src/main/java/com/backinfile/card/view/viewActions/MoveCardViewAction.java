package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.manager.Res;
import com.backinfile.support.Time2;

public class MoveCardViewAction extends ViewAction {
	public long endTime = 0; // 结束时间
	private List<DCardInfo> cardInfoList = new ArrayList<>();
	private boolean set;

	public MoveCardViewAction(List<DCardInfo> cardInfoList, boolean set) {
		this.cardInfoList.addAll(cardInfoList);
		this.set = set;
	}

	@Override
	public void init() {
		endTime = Time2.getCurMillis() + (long) (Res.BASE_DURATION * Time2.SEC);
	}

	@Override
	public void begin() {
		gameStage.boardView.cardGroupView.updateAllCardInfo(cardInfoList, set);
	}

	@Override
	public boolean isDone() {
		return endTime > 0 && Time2.getCurMillis() > endTime;
	}
}
