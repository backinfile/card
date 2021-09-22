package com.backinfile.card.view.group;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.view.group.CardView.CardViewState;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;
import com.backinfile.support.ObjectPool;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

// 管理所有卡牌
public class CardGroupView extends BaseView {
	public ObjectPool<CardView> cardActorPool;
	private HashMap<Long, CardView> cardViews = new HashMap<>(); // 正在显示的牌
	private HashMap<Long, CardInfo> cardInfoCacheMap = new HashMap<>();

	private List<CardView> helpCardViews = new ArrayList<>();

	public CardGroupView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		cardActorPool = new ObjectPool<CardView>() {
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
				cardView.addListener(new ClickListener(Buttons.LEFT) {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						cardView.invokeLeftClickCallback();
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

	}

	public void updateAllCardInfo(List<DCardInfo> allCardInfo, boolean set) {
		for (var cardInfo : allCardInfo) {
			updateCard(new CardInfo(cardInfo), set);
		}
		adjustCardLayer();
	}

	private void adjustCardLayer() {
		clearChildren();
		List<CardView> sorted = cardViews.values().stream().sorted(Comparator.comparing(CardView::getZIndex))
				.collect(Collectors.toList());
		for (var cardView : sorted) {
			addActor(cardView);
		}
	}

	public final CardView getCurCardView(long id) {
		return cardViews.get(id);
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

	public void setHelpCard() {

	}

	public CardViewState getCardViewState(CardInfo cardInfo) {
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
			if (pilePosition == PilePosition.Self) {
				cardViewState.position.set(getWidth() * 0.365f, getHeight() * 0.1125f);
			} else {
				cardViewState.position.set(getWidth() * 0.593f, getHeight() * 0.845f);
			}
			cardViewState.flipOver = true;
			break;
		case SlotPile:
			int index = cardInfo.pileInfo.getPileIndex();
			if (pilePosition == PilePosition.Self) {
				switch (index) {
				case 1:
					cardViewState.position.set(getWidth() * 0.215f, getHeight() * 0.3725f);
					break;
				case 2:
					cardViewState.position.set(getWidth() * 0.347f, getHeight() * 0.37875f);
					break;
				case 3:
					cardViewState.position.set(getWidth() * 0.478f, getHeight() * 0.3825f);
					break;
				case 4:
					cardViewState.position.set(getWidth() * 0.61f, getHeight() * 0.3775f);
					break;
				case 5:
					cardViewState.position.set(getWidth() * 0.746f, getHeight() * 0.371f);
					break;
				default:
					Log.game.error("unknown pile index for slotPile:{}", index);
				}
			} else {
				switch (index) {
				case 1:
					cardViewState.position.set(getWidth() * 0.743f, getHeight() * 0.585f);
					break;
				case 2:
					cardViewState.position.set(getWidth() * 0.611f, getHeight() * 0.581f);
					break;
				case 3:
					cardViewState.position.set(getWidth() * 0.476f, getHeight() * 0.575f);
					break;
				case 4:
					cardViewState.position.set(getWidth() * 0.346f, getHeight() * 0.578f);
					break;
				case 5:
					cardViewState.position.set(getWidth() * 0.2129f, getHeight() * 0.5825f);
					break;
				default:
					Log.game.error("unknown pile index for slotPile:{}", index);
				}
			}
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
		default:
			break;
		}
		return cardViewState;
	}

}
