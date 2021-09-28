package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCard;
import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.manager.ConstGame;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.view.group.CardView;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.group.boardView.ButtonInfo;
import com.backinfile.support.Log;
import com.backinfile.support.Utils;

public class SelectCardViewAction extends ViewAction {
	private long selected = -1; // -1表示尚未选择 0表示忽略 >0表示已做出选择

	private SCSelectCards data;
	private List<Long> selectFrom = new ArrayList<>();
	private boolean optional;

	public SelectCardViewAction(SCSelectCards data) {
		this.data = data;
		this.selectFrom = data.getCardIdsList();
		this.optional = data.getCancel();
	}

	private boolean isShowList() {
		if (ConstGame.CARD_LIST_VIEW) {
			return true;
		}
		if (selectFrom.stream().anyMatch(id -> gameStage.boardView.cardGroupView.getCurCardView(id) == null)) {
			return true;
		}
		if (selectFrom.stream().map(gameStage.boardView.cardGroupView::getCardInfoCache)
				.anyMatch(info -> info.pileInfo.getPileType() == ECardPileType.HandPile
						&& info.getPilePosition() == PilePosition.Opponent)) {
			return true;
		}
		return false;
	}

	@Override
	public void begin() {
		if (isShowList()) {
			// 选择牌库中的牌
			var cardInfos = selectFrom.stream().map(id -> gameStage.boardView.cardGroupView.getCardInfoCache(id))
					.collect(Collectors.toList());
			gameStage.showCardListView.show(cardInfos, data.getTip(), cardInfo -> {
				clearSelectCardListState();
				onSelect(cardInfo.getId());
			});
			gameStage.buttonsView.setButtonInfos();
			if (optional) {
				ButtonInfo buttonInfo = new ButtonInfo();
				buttonInfo.index = 0;
				buttonInfo.text = LocalString.getUIString("boardUIView").strs[2];
				if (!Utils.isNullOrEmpty(data.getCancelTip())) {
					buttonInfo.text = data.getCancelTip();
				}
				buttonInfo.callback = () -> {
					clearSelectCardListState();
					onSelect(0);
				};
				gameStage.buttonsView.setButtonInfos(buttonInfo);
			}
			return;
		}

		// 选择手牌，储备位上的牌
		for (var cardId : selectFrom) {
			CardView cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
			if (cardView != null) {
				cardView.setLeftClickCallback(() -> {
					clearSelectCardState();
					onSelect(cardId);
				});
				cardView.setDark(false);
			}
		}
		gameStage.buttonsView.setButtonInfos();
		if (optional) {
			ButtonInfo buttonInfo = new ButtonInfo();
			buttonInfo.index = 0;
			buttonInfo.text = LocalString.getUIString("boardUIView").strs[2];
			if (!Utils.isNullOrEmpty(data.getCancelTip())) {
				buttonInfo.text = data.getCancelTip();
			}
			buttonInfo.callback = () -> {
				clearSelectCardState();
				onSelect(0);
			};
			gameStage.buttonsView.setButtonInfos(buttonInfo);
		}

		gameStage.boardView.boardUIView.setTipText(data.getTip());
	}

	private void clearSelectCardState() {
		for (var cardId : selectFrom) {
			CardView cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
			if (cardView != null) {
				cardView.setLeftClickCallback(null);
				cardView.setDark(true);
			}
		}
		gameStage.buttonsView.setButtonInfos();
		gameStage.boardView.boardUIView.setTipText("");
	}

	private void clearSelectCardListState() {
		gameStage.buttonsView.setButtonInfos();
		gameStage.boardView.boardUIView.setTipText("");
		gameStage.showCardListView.hide();
	}

	private void onSelect(long id) {
		Log.game.info("select id:{}", id);
		selected = id;
		setDone();
	}

	@Override
	public void dispose() {
		// 发送回server
		CSSelectCard msg = new CSSelectCard();
		msg.setCardId(this.selected);
		gameStage.boardView.gameClient.sendMessage(msg);
	}
}
