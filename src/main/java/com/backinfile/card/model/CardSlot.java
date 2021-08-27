package com.backinfile.card.model;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.gen.GameMessage.ECardPileType;
import com.backinfile.card.gen.GameMessage.ESlotType;

// 储备位
public class CardSlot {
	public int index = 1;
	public boolean asPlanSlot = false; // 可作为计划区使用
	public boolean ready = false; // 储备完成
	public Map<ESlotType, CardPile> slotPileMap = new HashMap<>();


	public CardPile getAllCards() {
		CardPile cardPile = new CardPile(ECardPileType.SlotPile);
		for (var pile : slotPileMap.values()) {
			cardPile.addAll(pile);
		}
		return cardPile;
	}

	public boolean removeCard(Card card) {
		for (var pile : slotPileMap.values()) {
			if (pile.remove(card)) {
				return true;
			}
		}
		return false;
	}

	public void put(ESlotType slotType, Card card) {
		var pile = slotPileMap.get(slotType);
		if (pile == null) {
			pile = new CardPile(ECardPileType.SlotPile);
			slotPileMap.put(slotType, pile);
		}
		pile.add(card);
	}

	public void remove(ESlotType slotType) {
		slotPileMap.remove(slotType);
	}

	public boolean remove(Card card) {
		for (var pile : slotPileMap.values()) {
			if (pile.remove(card)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(Card card) {
		for (var pile : slotPileMap.values()) {
			if (pile.contains(card)) {
				return true;
			}
		}
		return false;
	}

	public ESlotType getSlotType(Card card) {
		for (var entry : slotPileMap.entrySet()) {
			if (entry.getValue().contains(card)) {
				return entry.getKey();
			}
		}
		return ESlotType.None;
	}

	public CardPile getPile(ESlotType slotType) {
		var pile = slotPileMap.get(slotType);
		if (pile == null) {
			pile = new CardPile(ECardPileType.SlotPile);
			slotPileMap.put(slotType, pile);
		}
		return pile;
	}

	public void clear() {
		slotPileMap.clear();
	}
}
