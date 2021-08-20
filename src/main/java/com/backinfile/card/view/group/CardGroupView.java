package com.backinfile.card.view.group;

import java.util.HashMap;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardInfo;
import com.backinfile.support.ObjectPool;

public class CardGroupView extends BaseView {

	private ObjectPool<CardView> cardActorPool;
	private HashMap<Card, CardView> showingCardActors = new HashMap<>();
	private HashMap<Card, CardInfo> saveCardInfos = new HashMap<>();

	public CardGroupView(float width, float height) {
		super(width, height);
		cardActorPool = new ObjectPool<>(() -> new CardView());
	}

	public void updateCard(CardInfo cardInfo, boolean set) {
		var lastCardInfo = saveCardInfos.get(cardInfo.card);
		if (lastCardInfo == null) {
			set = true;
		}

		saveCardInfos.put(cardInfo.card, cardInfo);

		var cardActor = showingCardActors.get(cardInfo.card);

		if (cardActor == null) {
			cardActor = cardActorPool.apply();
			cardActor.setVisible(true);
		}
	}
}
