package com.backinfile.card.view.group;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.manager.Res;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.view.group.CardView.CardViewState;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;
import com.backinfile.support.ObjectPool;
import com.backinfile.support.Time2;
import com.backinfile.support.Tuple2;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

// 管理所有卡牌
public class CardGroupView extends BaseView {
	private ObjectPool<CardView> cardActorPool;
	private HashMap<Long, CardView> cardViews = new HashMap<>(); // 正在显示的牌
	private HashMap<Long, CardInfo> cardInfoCacheMap = new HashMap<>();

	// 控制动画播放
	private LinkedList<Tuple2<List<DCardInfo>, Boolean>> cardUpdateCache = new LinkedList<>();
	private long animeEndTime = 0;

	public CardGroupView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		cardActorPool = new ObjectPool<CardView>() {
			@Override
			protected CardView newObject() {
				CardView cardView = new CardView();
				cardView.addListener(new ClickListener(Buttons.RIGHT) {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						Log.game.info("cardView click");
						if (cardView.isFlipOver()) {
							return;
						}
						gameStage.boardView.showCardView.show(cardView.getImagePathString());
					}
				});
				return cardView;
			}
		};

	}

	public void updateAllCardInfo(List<DCardInfo> allCardInfo, boolean set) {
		cardUpdateCache.add(new Tuple2<List<DCardInfo>, Boolean>(allCardInfo, set));
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		// 这次动画要等到上次动画执行完毕
		if (Time2.getCurMillis() > animeEndTime && !cardUpdateCache.isEmpty()) {
			var cache = cardUpdateCache.pollFirst();
			List<DCardInfo> allCardInfo = cache.value1;
			boolean set = cache.value2;
			for (var cardInfo : allCardInfo) {
				updateCard(new CardInfo(cardInfo), set);
			}
			adjustCardLayer();

			if (!set) {
				animeEndTime = Time2.getCurMillis() + (long) (Res.BASE_DURATION * Time2.SEC) + 1;
			}
		}
	}

	private void adjustCardLayer() {
		clearChildren();
		List<CardView> sorted = cardViews.values().stream().sorted(Comparator.comparing(CardView::getZIndex))
				.collect(Collectors.toList());
		for (var cardView : sorted) {
			addActor(cardView);
		}
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
			addActor(cardView);
			cardView.setCardString(LocalString.getCardString(cardInfo.info.getSn()),
					cardInfo.getPilePosition().ordinal());
			cardViews.put(cardInfo.getId(), cardView);
		}
		return cardView;
	}

	private final void removeCardView(CardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.getId());
		if (cardView != null) {
			cardActorPool.free(cardView);
			cardViews.remove(cardInfo.getId());
			removeActor(cardView);
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

	private CardViewState getCardViewState(CardInfo cardInfo) {
		var pileType = cardInfo.info.getPileInfo().getPileType();
		var pilePosition = cardInfo.getPilePosition();
		CardViewState cardViewState = new CardViewState();
		switch (pileType) {
		case DiscardPile: {
			if (pilePosition == PilePosition.Self) {
				cardViewState.position.set(getWidth() * 0.776f, getHeight() * 0.225f);
			} else {
				cardViewState.position.set(getWidth() * 0.22f, getHeight() * 0.77f);
			}
			cardViewState.rotated = true;
			cardViewState.flipOver = true;
			break;
		}
		case DrawPile: {
			if (pilePosition == PilePosition.Self) {
				cardViewState.position.set(getWidth() * 0.225f, getHeight() * 0.225f);
			} else {
				cardViewState.position.set(getWidth() * 0.77f, getHeight() * 0.77f);
			}
			cardViewState.rotated = true;
			cardViewState.flipOver = true;
			break;
		}
		case HandPile: {
			if (pilePosition == PilePosition.Self) {
				cardViewState.position.set(getWidth() * 0.5f, getHeight() * 0f);
			} else {
				cardViewState.position.set(getWidth() * 0.5f, getHeight() * 1f);
				cardViewState.flipOver = true;
			}
			float offset = cardInfo.pileInfo.getPileIndex() - cardInfo.pileInfo.getPileSize() / 2f + 0.5f;
			cardViewState.position.x += offset * cardViewState.cardSize.width * 0.8f;
			cardViewState.zIndex = 11000 + cardInfo.pileInfo.getPileIndex();
			break;
		}
		case HeroPile: {
			if (pilePosition == PilePosition.Self) {
				cardViewState.position.set(getWidth() * 0.5f, getHeight() * 0.893f);
			} else {
				cardViewState.position.set(getWidth() * 0.5f, getHeight() * 0.107f);
			}
			break;
		}
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
		return cardViewState;
	}

}
