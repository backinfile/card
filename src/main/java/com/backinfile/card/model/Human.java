package com.backinfile.card.model;

import java.util.HashMap;
import java.util.Map;

public class Human {
	public Card heroCard;
	public CardPile handPile = new CardPile();
	public Map<Integer, CardSlot> cardSlotMap = new HashMap<>();
	public CardPile markPile = new CardPile();
	public CardPile discardPile = new CardPile();
	public CardPile trashPile = new CardPile();

	public int handPileMaxSize; // 手牌上限

	public void init() {
	}
}
