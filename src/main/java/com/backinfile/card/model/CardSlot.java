package com.backinfile.card.model;

public class CardSlot {
	public Card storeCard = null; // 储备卡
	public Card sealCard = null; // 封印卡
	public Card rideCard = null; // 骑乘卡
	public CardPile chargeCards = new CardPile(); // 充能卡
	public Card planCard = null; // 计划卡

	public boolean ready = false;

	public CardPile getAll() {
		CardPile cardPile = new CardPile();
		cardPile.add(storeCard);
		cardPile.add(sealCard);
		cardPile.add(rideCard);
		cardPile.addAll(chargeCards);
		cardPile.add(planCard);
		return cardPile;
	}
}
