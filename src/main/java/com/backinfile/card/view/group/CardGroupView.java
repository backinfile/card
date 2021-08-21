package com.backinfile.card.view.group;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.model.CardPile.PileType;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.ObjectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class CardGroupView extends BaseView {

	private ObjectPool<CardView> cardActorPool;
	private HashMap<Card, CardView> cardViews = new HashMap<>();
	private HashMap<Card, CardInfo> saveCardInfos = new HashMap<>();
	private Group cardGroup = new Group();

	private PileView myDrawPile;
	private PileView opDrawPile;
	private PileView myDiscardPile;
	private PileView opDiscardPile;

	public CardGroupView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		cardActorPool = new ObjectPool<>(() -> new CardView());

		myDrawPile = new PileView(gameStage, width, height, PileType.DrawPile, true);
		opDrawPile = new PileView(gameStage, width, height, PileType.DrawPile, false);
		myDiscardPile = new PileView(gameStage, width, height, PileType.DiscardPile, true);
		opDiscardPile = new PileView(gameStage, width, height, PileType.DiscardPile, false);

		addActor(myDrawPile);
		addActor(opDrawPile);
		addActor(myDiscardPile);
		addActor(opDiscardPile);

		addActor(cardGroup);
	}

	public void updateAllCardInfo(Map<Card, CardInfo> allCardInfo, boolean set) {
		for (var cardInfo : allCardInfo.values()) {
			updateCard(cardInfo, set);
		}
		adjustCardLayer();
	}

	private void adjustCardLayer() {
	}

	private void updateCard(CardInfo cardInfo, boolean set) {
		var lastCardInfo = saveCardInfos.get(cardInfo.card);
		if (lastCardInfo == null) {
			set = true;
		}

		saveCardInfos.put(cardInfo.card, cardInfo);

		var cardActor = cardViews.get(cardInfo.card);

		if (cardActor == null) {
			cardActor = cardActorPool.apply();
			cardActor.setVisible(true);
		}
	}

	private void setCard(CardInfo cardInfo) {
		if (!isCardVisible(cardInfo)) {
			removeCardView(cardInfo);
		}
	}

	private void moveCard(CardInfo oldCardInfo, CardInfo newCardInfo) {

	}

	private CardView getCardView(CardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.card);
		if (cardView == null) {
			cardView = cardActorPool.apply();
			cardViews.put(cardInfo.card, cardView);
		}
		return cardView;
	}

	private void removeCardView(CardInfo cardInfo) {
		var cardView = cardViews.get(cardInfo.card);
		if (cardView != null) {
			cardView.setVisible(false);
			cardActorPool.putBack(cardView);
			cardViews.remove(cardInfo.card);
		}
	}

	private boolean isCardVisible(CardInfo cardInfo) {
		switch (cardInfo.pileType) {
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

	private Vector2 getCardFinalPos(CardInfo cardInfo) {
		switch (cardInfo.pileType) {
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
