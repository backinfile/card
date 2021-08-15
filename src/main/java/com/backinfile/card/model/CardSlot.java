package com.backinfile.card.model;

// 储备位
public class CardSlot {
	public int index = 1;
	public boolean asPlanSlot = false; // 可作为计划区使用
	public boolean ready = false; // 储备完成

	public Card storeCard = null; // 储备卡
	public Card sealCard = null; // 封印卡
	public Card rideCard = null; // 骑乘卡
	public Card harassCard = null; // 骚扰卡
	public CardPile chargeCards = new CardPile(); // 充能卡
	public Card planCard = null; // 计划卡

	public CardPile getAllCards() {
		CardPile cardPile = new CardPile();
		cardPile.add(storeCard);
		cardPile.add(sealCard);
		cardPile.add(rideCard);
		cardPile.addAll(chargeCards);
		cardPile.add(planCard);
		return cardPile;
	}
}
