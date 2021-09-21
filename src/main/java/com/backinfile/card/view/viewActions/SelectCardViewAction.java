package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCard;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.view.group.CardView;
import com.backinfile.card.view.group.boardView.ButtonInfo;
import com.backinfile.support.Log;

public class SelectCardViewAction extends ViewAction {
	private long selected = -1; // -1表示尚未选择 0表示忽略 >0表示已做出选择
	private List<Long> selectFrom = new ArrayList<>();
	private boolean optional;

	public SelectCardViewAction(List<Long> selectFrom, boolean optional) {
		this.selectFrom = selectFrom;
		this.optional = optional;
	}

	@Override
	public void begin() {
		for (var cardId : selectFrom) {
			CardView cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
			if (cardView != null) {
				cardView.setLeftClickCallback(() -> {
					onSelect(cardId);
				});
				cardView.setDark(false);
			}
		}
		if (optional) {
			ButtonInfo buttonInfo = new ButtonInfo();
			buttonInfo.index = 0;
			buttonInfo.text = LocalString.getUIString("boardUIView").strs[2];
			buttonInfo.callback = () -> {
				onSelect(0);
			};
			gameStage.buttonsView.setButtonInfos(buttonInfo);
		}
	}

	private void onSelect(long id) {
		Log.game.info("select id:{}", id);
		selected = id;
		for (var cardId : selectFrom) {
			CardView cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
			if (cardView != null) {
				cardView.setLeftClickCallback(null);
				cardView.setDark(true);
			}
		}
		if (optional) {
			gameStage.buttonsView.setButtonInfos();
		}

		isDone = true;
	}

	@Override
	public void dispose() {
		// 发送回server
		CSSelectCard msg = new CSSelectCard();
		msg.setCardId(this.selected);
		gameStage.boardView.gameClient.sendMessage(msg);
	}
}
