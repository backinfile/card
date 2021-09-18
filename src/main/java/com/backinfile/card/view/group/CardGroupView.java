package com.backinfile.card.view.group;

import java.util.HashMap;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.view.group.CardView.CardViewState;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.ObjectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

// 管理所有卡牌
public class CardGroupView extends BaseView {
	private ObjectPool<CardView> cardActorPool;
	private HashMap<Long, CardView> cardViews = new HashMap<>(); // 正在显示的牌
	private HashMap<Long, CardInfo> cardInfoCacheMap = new HashMap<>();
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
				addActor(cardView);
				return cardView;
			}

			@Override
			public void free(CardView obj) {
				super.free(obj);
				removeActor(obj);
			}
		};

		addActor(cardGroup);
	}

	public void updateAllCardInfo(List<DCardInfo> allCardInfo, boolean set) {
		for (var cardInfo : allCardInfo) {
			updateCard(new CardInfo(cardInfo), set);
		}
		adjustCardLayer();
	}

	private void adjustCardLayer() {
	}

	private void updateCard(CardInfo cardInfo, boolean set) {
		var lastCardInfo = cardInfoCacheMap.get(cardInfo.getId());
		if (lastCardInfo == null) { // 没有cache 直接设置到指定位置
			set = true;
		}
		if (set) { // 设置卡牌到指定位置
			if (isCardVisible(cardInfo)) {
				var cardView = getCardView(cardInfo);
				cardView.setState(getCardViewState(cardInfo));
			} else {
				// 卡牌不显示
				removeCardView(cardInfo);
			}
		} else { // 移动卡牌到指定位置
			if (!isCardVisible(lastCardInfo)) { // 之前不存在，先显示出来
				var cardView = getCardView(lastCardInfo);
				cardView.setState(getCardViewState(lastCardInfo));
			}
			var cardView = getCardView(lastCardInfo);
			cardView.moveToState(getCardViewState(cardInfo));
		}
		cardInfoCacheMap.put(cardInfo.getId(), cardInfo);
	}

	private final CardView getCardView(CardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.getId());
		if (cardView == null) {
			cardView = cardActorPool.obtain();
			cardViews.put(cardInfo.getId(), cardView);
		}
		return cardView;
	}

	private final void removeCardView(CardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.getId());
		if (cardView != null) {
			cardActorPool.free(cardView);
			cardViews.remove(cardInfo.getId());
		}
	}

	private final boolean isCardVisible(CardInfo cardInfo) {
		switch (cardInfo.info.getPileInfo().getPileType()) {
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

	private CardViewState getCardViewState(CardInfo cardInfo) { // TODO
		var pileType = cardInfo.info.getPileInfo().getPileType();
		var pilePosition = cardInfo.getPilePosition();
		switch (pileType) {
		case HandPile: {
			break;
		}
		case HeroPile:
			break;
		case MarkPile:
			break;
		case None:
			break;
		case SlotPile:
			break;
		case TrashPile:
			break;
		default:
			break;
		}
		return null;
	}

}
