package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backinfile.card.manager.CardManager;
import com.backinfile.card.model.actions.DrawCardAction;
import com.backinfile.card.model.actions.RestoreActionNumberAction;
import com.backinfile.card.model.actions.SaveThreatenAction;
import com.backinfile.card.server.proto.DHumanInit;

public class Human extends SkillCaster {
	// 固有属性
	public String token;

	// 被赋值属性
	public Board board;

	// 可变属性
	public Card heroCard;
	public CardPile handPile = new CardPile();
	public Map<Integer, CardSlot> cardSlotMap = new HashMap<>();
	public CardPile markPile = new CardPile();
	public CardPile drawPile = new CardPile();
	public CardPile discardPile = new CardPile();
	public CardPile trashPile = new CardPile();
	public int actionNumber = 0;

	// 可被远程使用的属性
	public TargetInfo targetInfo = null; // 当前正在进行的选择

	public void init(DHumanInit humanInit) {
		this.token = humanInit.controllerToken;
		this.heroCard = CardManager.getCard(humanInit.startHeroCard, token);
		for (var entry : humanInit.startPileData.pile.entrySet()) {
			var name = entry.getKey();
			var number = entry.getValue();
			for (int i = 0; i < number; i++) {
				this.drawPile.add(CardManager.getCard(name, token));
				this.drawPile.shuffle();
			}
		}

		// 初始化储备位
		for (int i = 1; i <= 5; i++) {
			var cardSlot = new CardSlot();
			cardSlot.asPlanSlot = i == 5;
			cardSlot.index = i;
			cardSlotMap.put(cardSlot.index, cardSlot);
		}
	}

	public final void onGameStart() {
		addLast(new DrawCardAction(this, 5));
	}

	public final void onTurnStart() {
		addLast(new RestoreActionNumberAction(this, 2));
		addLast(new DrawCardAction(this, 3));
	}

	public final void onTurnEnd() {
		addLast(new SaveThreatenAction());
	}

	public boolean removeCard(Card card) {
		if (markPile.remove(card)) {
			return true;
		}
		if (drawPile.remove(card)) {
			return true;
		}
		if (discardPile.remove(card)) {
			return true;
		}
		if (trashPile.remove(card)) {
			return true;
		}
		for (var slot : cardSlotMap.values()) {
			if (slot.removeCard(card)) {
				return true;
			}
		}
		return false;
	}

	public CardPile getAllCards() {
		CardPile cardPile = new CardPile();
		cardPile.add(heroCard);
		cardPile.addAll(markPile);
		cardPile.addAll(drawPile);
		cardPile.addAll(discardPile);
		cardPile.addAll(trashPile);
		for (var slot : cardSlotMap.values()) {
			cardPile.addAll(slot.getAllCards());
		}
		return cardPile;
	}

	public List<CardSlot> getEmptySlots(boolean exceptPlanSlot) {
		List<CardSlot> cardSlots = new ArrayList<>();
		for (var cardSlot : cardSlotMap.values()) {
			if (cardSlot.asPlanSlot && exceptPlanSlot) {
				continue;
			}
			if (cardSlot.getAllCards().isEmpty()) {
				cardSlots.add(cardSlot);
			}
		}
		return cardSlots;
	}

	public Card getCard(long id) {
		for (var card : getAllCards()) {
			if (card.id == id) {
				return card;
			}
		}
		return null;
	}

	public CardPile getAllStoreCards(boolean ready) {
		CardPile cardPile = new CardPile();
		for (var slot : cardSlotMap.values()) {
			if (ready && !slot.ready) {
				continue;
			}
			cardPile.add(slot.storeCard);
		}
		return cardPile;
	}

	public final void clearTargetInfo() {
		targetInfo = null;
	}

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}
}
