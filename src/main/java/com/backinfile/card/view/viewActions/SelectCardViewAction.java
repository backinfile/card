package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCards;
import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.manager.ConstGame;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.view.group.CardView;
import com.backinfile.card.view.group.PileView.HumanPosition;
import com.backinfile.card.view.group.boardView.ButtonInfo;
import com.backinfile.support.Log;

public class SelectCardViewAction extends ViewAction {

	private SCSelectCards data;
	private List<Long> selectFrom = new ArrayList<>();

	private List<Long> selected = new ArrayList<>();

	public SelectCardViewAction(SCSelectCards data) {
		this.data = data;
		this.selectFrom = data.getCardIdsList();
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
						&& info.getPilePosition() == HumanPosition.Opponent)) {
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
			gameStage.showCardListView.show(cardInfos, data.getTip(), data.getMinNumber(), data.getMaxNumber(),
					cardInfo -> {
						onSelect(cardInfo);
					});
			return;
		}

		// 选择手牌，储备位上的牌
		for (Long cardId : selectFrom) {
			CardView cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
			if (cardView != null) {
				cardView.setLeftClickCallback(() -> {
					if (selected.contains(cardId)) {
						selected.remove(cardId);
						cardView.setChecked(false);
					} else {
						selected.add(cardId);
						cardView.setChecked(true);
					}
					onCardClick();
				});
				cardView.setDark(false);
			}
		}
		gameStage.boardView.boardUIView.setTipText(data.getTip());
		onCardClick();
	}

	private void clearSelectCardState() {
		for (var cardId : selectFrom) {
			CardView cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
			if (cardView != null) {
				cardView.setLeftClickCallback(null);
				cardView.setDark(true);
				cardView.setChecked(false);
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

	private void onCardClick() {
		if (data.getMinNumber() <= selected.size() && selected.size() <= data.getMaxNumber()) {
			// 出现确认按钮
			var buttonInfo = new ButtonInfo();
			buttonInfo.index = 0;
			buttonInfo.text = LocalString.getUIString("boardUIView").strs[selected.isEmpty() ? 2 : 4];
			buttonInfo.callback = this::onSelectOver;
			gameStage.buttonsView.setButtonInfos(buttonInfo);
		} else {
			gameStage.buttonsView.setButtonInfos();
		}
	}

	private void onSelectOver() {
		clearSelectCardState();

		Log.game.info("select id:{}", selected);
		CSSelectCards msg = new CSSelectCards();
		msg.addAllCardIds(selected);
		gameStage.boardView.gameClient.sendMessage(msg);
		setDone();
	}

	private void onSelect(List<CardInfo> cardInfos) {
		clearSelectCardListState();

		CSSelectCards msg = new CSSelectCards();
		for (var cardInfo : cardInfos) {
			msg.addCardIds(cardInfo.getId());
		}
		Log.game.info("select id:{}", msg.getCardIdsList());
		gameStage.boardView.gameClient.sendMessage(msg);
		setDone();
	}
}
