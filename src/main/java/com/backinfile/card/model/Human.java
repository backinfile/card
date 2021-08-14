package com.backinfile.card.model;

import java.util.HashMap;
import java.util.Map;

public class Human extends SkillCaster {
	public long id;
	public Card heroCard;
	public CardPile handPile = new CardPile();
	public Map<Integer, CardSlot> cardSlotMap = new HashMap<>();
	public CardPile markPile = new CardPile();
	public CardPile drawPile = new CardPile();
	public CardPile discardPile = new CardPile();
	public CardPile trashPile = new CardPile();

	public CardPile selectedPile = new CardPile(); // 当前已确认选择的卡
	public TargetInfo targetInfo = null; // 当前正在进行的选择

	public void init() {
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
}
