package com.backinfile.card.view.group;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.view.group.CardView.CardViewState;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;
import com.backinfile.support.ObjectPool;
import com.backinfile.support.func.Action0;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

// 管理所有卡牌
public class CardGroupView extends BaseView {
	public ObjectPool<CardView> cardViewPool;
	private HashMap<Long, CardView> cardViews = new HashMap<>(); // 正在显示的牌
	private HashMap<Long, CardInfo> cardInfoCacheMap = new HashMap<>();
	private Group cardViewGroup = new Group();

	private Map<Integer, CardView> helpCardViews = new HashMap<>();
	private float viewWidth;
	private float viewHeight;

	public CardGroupView(GameStage gameStage, float width, float height) {
		super(gameStage, 0, 0);
		this.viewWidth = width;
		this.viewHeight = height;

		addActor(cardViewGroup);
		cardViewPool = new ObjectPool<CardView>() {
			@Override
			protected CardView newObject() {
				CardView cardView = new CardView();
				cardView.addListener(new ClickListener(Buttons.RIGHT) {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (cardView.isFlipOver()) {
							return;
						}
						gameStage.showCardView.show(cardView.getImagePathString());
					}
				});
				return cardView;
			}

			@Override
			public CardView obtain() {
				CardView cardView = super.obtain();
				cardView.setLeftClickCallback(null);
				return cardView;
			}
		};

		// 初始化辅助卡
		String[] helpCardNames = new String[] { "", "slotoccupycard", "slotstorecard" };
		for (int index = 1; index <= 5; index++) {
			for (int pos = 0; pos <= 1; pos++) {
				for (int type = 1; type <= 2; type++) {
					int key = index * 100 + pos * 10 + type;
					CardViewState cardViewState = new CardViewState();
					cardViewState.position.set(getSlotPilePosition(index, pos));
					var cardView = new CardView();
					cardView.setCardString(LocalString.getCardString(helpCardNames[type]));
					cardView.setState(cardViewState);
					cardView.setVisible(false);
					addActor(cardView);
					helpCardViews.put(key, cardView);
				}
			}
		}
	}

	public void setHelpCard(int index, int pos, int type, boolean visible, Action0 callback) {
		int key = index * 100 + pos * 10 + type;
		CardView cardView = helpCardViews.get(key);
		cardView.setVisible(visible);
		cardView.setLeftClickCallback(callback);
	}

	public void hideAllHelpCard() {
		for (var cardView : helpCardViews.values()) {
			cardView.setVisible(false);
		}
	}

	public void updateAllCardInfo(List<DCardInfo> allCardInfo, boolean set) {
		for (var cardInfo : allCardInfo) {
			updateCard(new CardInfo(cardInfo), set);
		}
		adjustCardLayer();
	}

	private void adjustCardLayer() {
		cardViewGroup.clearChildren();
		List<CardView> sorted = cardViews.values().stream().sorted(Comparator.comparing(CardView::getZIndex))
				.collect(Collectors.toList());
		for (var cardView : sorted) {
			cardViewGroup.addActor(cardView);
		}
	}

	public final CardView getCurCardView(long id) {
		return cardViews.get(id);
	}

	public final CardInfo getCardInfoCache(long id) {
		return cardInfoCacheMap.get(id);
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
			cardView.moveToState(getCardViewState(cardInfo), () -> {
				if (!isCardVisible(cardInfo)) {
					removeCardView(cardInfo);
				}
			});
		}
		cardInfoCacheMap.put(cardInfo.getId(), cardInfo);
	}

	private final CardView getCardView(CardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.getId());
		if (cardView == null) {
			cardView = cardViewPool.obtain();
			cardViewGroup.addActor(cardView);
			cardView.setCardString(LocalString.getCardString(cardInfo.info.getSn()),
					cardInfo.getPilePosition().ordinal());
			cardViews.put(cardInfo.getId(), cardView);
		}
		return cardView;
	}

	private final void removeCardView(CardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.getId());
		if (cardView != null) {
			cardViewPool.free(cardView);
			cardViews.remove(cardInfo.getId());
			cardViewGroup.removeActor(cardView);
		}
	}

	private final boolean isCardVisible(CardInfo cardInfo) {
		switch (cardInfo.info.getPileInfo().getPileType()) {
		case DiscardPile:
		case DrawPile:
		case MarkPile:
		case TrashPile:
		case ThreatenPile:
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

	public CardViewState getCardViewState(CardInfo cardInfo) {
		var pileType = cardInfo.info.getPileInfo().getPileType();
		var pilePosition = cardInfo.getPilePosition();
		CardViewState cardViewState = new CardViewState();
		cardViewState.position.set(getCommonPilePosition(viewWidth, viewHeight, pileType, pilePosition));
		switch (pileType) {
		case DiscardPile: {
			cardViewState.rotated = true;
			cardViewState.flipOver = true;
			break;
		}
		case DrawPile: {
			cardViewState.rotated = true;
			cardViewState.flipOver = true;
			break;
		}
		case HandPile: {
			cardViewState.flipOver = pilePosition == PilePosition.Opponent;
			float offset = cardInfo.pileInfo.getPileIndex() - cardInfo.pileInfo.getPileSize() / 2f + 0.5f;
			cardViewState.position.x += offset * cardViewState.cardSize.width * 0.8f;
			cardViewState.zIndex = 11000 + cardInfo.pileInfo.getPileIndex();
			break;
		}
		case HeroPile: {
			break;
		}
		case MarkPile:
			cardViewState.flipOver = true;
			break;
		case ThreatenPile: {
			cardViewState.flipOver = true;
			break;
		}
		case SlotPile: {
			int index = cardInfo.pileInfo.getSlotIndex();
			cardViewState.position.set(getSlotPilePosition(index, pilePosition.ordinal()));
			switch (cardInfo.pileInfo.getSlotType()) {
			case Store:
				cardViewState.rotated = !cardInfo.pileInfo.getReady();
				cardViewState.zIndex = 1;
				break;
			case Seal:
				cardViewState.flipOver = true;
				cardViewState.zIndex = 1;
				break;
			case Plan:
				cardViewState.flipOver = true;
				cardViewState.zIndex = 1;
				break;
			case Ride: // 在储备牌之下
				cardViewState.rotated = true;
				cardViewState.zIndex = 0;
				break;
			case Harass: // 在封印之上
				cardViewState.rotated = true;
				cardViewState.zIndex = 2;
				break;
			case Charge: // 在储备之上
				cardViewState.zIndex = 3;
				break;
			default:
				break;
			}
			break;
		}
		default:
			break;
		}
		return cardViewState;
	}

	public static Vector2 getCommonPilePosition(float width, float height, ECardPileType type,
			PilePosition pilePosition) {
		Vector2 position = new Vector2();
		switch (type) {
		case DiscardPile: {
			if (pilePosition == PilePosition.Self) {
				position.set(width * 0.776f, height * 0.225f);
			} else {
				position.set(width * 0.22f, height * 0.77f);
			}
			break;
		}
		case DrawPile: {
			if (pilePosition == PilePosition.Self) {
				position.set(width * 0.225f, height * 0.225f);
			} else {
				position.set(width * 0.77f, height * 0.77f);
			}
			break;
		}
		case HandPile: {
			if (pilePosition == PilePosition.Self) {
				position.set(width * 0.5f, height * 0f);
			} else {
				position.set(width * 0.5f, height * 1f);
			}
			break;
		}
		case HeroPile: {
			if (pilePosition == PilePosition.Self) {
				position.set(width * 0.5f, height * 0.893f);
			} else {
				position.set(width * 0.5f, height * 0.107f);
			}
			break;
		}
		case MarkPile:
			if (pilePosition == PilePosition.Self) {
				position.set(width * 0.365f, height * 0.1119f);
			} else {
				position.set(width * 0.607f, height * 0.89f);
			}
			break;
		case ThreatenPile: {
			if (pilePosition == PilePosition.Self) {
				position.set(width * 0.62f, height * 0.118f);
			} else {
				position.set(width * 0.381f, height * 0.895f);
			}
			break;
		}
		default:
			break;
		}
		return position;
	}

	public Vector2 getSlotPilePosition(int index, int pilePosition) {
		float width = this.viewWidth;
		float height = this.viewHeight;
		Vector2 position = new Vector2();
		if (pilePosition == 0) {
			switch (index) {
			case 1:
				position.set(width * 0.226f, height * 0.386f);
				break;
			case 2:
				position.set(width * 0.359f, height * 0.394f);
				break;
			case 3:
				position.set(width * 0.498f, height * 0.399f);
				break;
			case 4:
				position.set(width * 0.635f, height * 0.391f);
				break;
			case 5:
				position.set(width * 0.777f, height * 0.389f);
				break;
			default:
				Log.game.error("unknown pile index for slotPile:{}", index);
			}
		} else {
			switch (index) {
			case 1:
				position.set(width * 0.777f, height * 0.611f);
				break;
			case 2:
				position.set(width * 0.637f, height * 0.604f);
				break;
			case 3:
				position.set(width * 0.497f, height * 0.600f);
				break;
			case 4:
				position.set(width * 0.361f, height * 0.604f);
				break;
			case 5:
				position.set(width * 0.220f, height * 0.608f);
				break;
			default:
				Log.game.error("unknown pile index for slotPile:{}", index);
			}
		}
		return position;
	}

}
