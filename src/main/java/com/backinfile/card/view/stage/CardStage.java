package com.backinfile.card.view.stage;

import java.util.HashMap;

import com.backinfile.card.support.ObjectPool;
import com.backinfile.card.view.actor.CardActor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CardStage extends Stage {

	private ObjectPool<CardActor> cardActorPool;
	private HashMap<Long, CardActor> showingCardActors = new HashMap<>();

	public CardStage(Viewport viewport) {
		super(viewport);

		init();
	}

	private void init() {
		cardActorPool = new ObjectPool<>(() -> new CardActor());
	}

	public void updateCard(CardInfo lastCardInfo, CardInfo cardInfo) {
		boolean set = false;
		var cardActor = showingCardActors.get(cardInfo.getId());
		if (lastCardInfo == null || cardActor == null) {
			set = true;
		}
		if (cardActor == null) {
			cardActor = cardActorPool.apply();
			cardActor.setVisible(true);
		}
	}

	@Override
	public void draw() {
		super.draw();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

}
