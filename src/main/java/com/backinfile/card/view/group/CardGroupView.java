package com.backinfile.card.view.group;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.manager.Res;
import com.backinfile.card.model.Card;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.ObjectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

// 管理所有卡牌
public class CardGroupView extends BaseView {

	private ObjectPool<CardView> cardActorPool;
	private HashMap<Long, CardView> cardViews = new HashMap<>();
	private HashMap<Long, DCardInfo> cardInfoCacheMap = new HashMap<>();
	private Group cardGroup = new Group();

	public CardGroupView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		cardActorPool = new ObjectPool<CardView>() {
			@Override
			protected CardView newObject() {
				return new CardView();
			}

			@Override
			public CardView obtain() {
				var cardView = super.obtain();
				cardView.clearState();
				return cardView;
			}
		};

		addActor(cardGroup);
	}

	public void updateAllCardInfo(Map<Card, DCardInfo> allCardInfo, boolean set) {
		for (var cardInfo : allCardInfo.values()) {
			updateCard(cardInfo, set);
		}
		adjustCardLayer();
	}

	private void adjustCardLayer() {
	}

	private void updateCard(DCardInfo cardInfo, boolean set) {
		var lastCardInfo = cardInfoCacheMap.get(cardInfo.getId());
		if (lastCardInfo == null) {
			set = true;
		}

		cardInfoCacheMap.put(cardInfo.getId(), cardInfo);

		var cardActor = cardViews.get(cardInfo.getId());

		if (cardActor == null) {
			cardActor = cardActorPool.obtain();
			cardActor.setVisible(true);
		}
	}

	private void setCard(DCardInfo cardInfo) {
		if (!isCardVisible(cardInfo)) {
			removeCardView(cardInfo);
		}
	}

	private void moveCard(DCardInfo oldCardInfo, DCardInfo newCardInfo) {

	}

	private CardView getCardView(DCardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.getId());
		if (cardView == null) {
			cardView = cardActorPool.obtain();
			cardViews.put(cardInfo.getId(), cardView);
		}
		return cardView;
	}

	private void removeCardView(DCardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.getId());
		if (cardView != null) {
			cardView.setVisible(false);
			cardActorPool.free(cardView);
			cardViews.remove(cardInfo.getId());
		}
	}

	private boolean isCardVisible(DCardInfo cardInfo) {
		switch (cardInfo.getPileInfo().getPileType()) {
		case DiscardPile:
		case DrawPile:
		case MarkPile:
		case TrashPile:
			return false;
		case HandPile:
		case HeroPile:
		case SlotPile:
			return true;
		case None:
			break;
		default:
			break;
		}
		return false;
	}

	private Vector2 getCardFinalPos(DCardInfo cardInfo) {
		switch (cardInfo.getPileInfo().getPileType()) {
		case DiscardPile: {
		}
		}
		return null;
	}

	private Vector2 getHandPileBasePos(boolean self) {
		var pos = new Vector2();
		pos.x = getWidth() / 2;
		if (self) {
			pos.y = Res.CARD_HEIGHT / 2f;
		} else {
			pos.y = getHeight() - Res.CARD_HEIGHT / 2;
		}
		return pos;
	}

}
