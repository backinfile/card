package com.backinfile.card.model;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.model.CardPile.PileType;

// 储备位
public class CardSlot {
	public int index = 1;
	public boolean asPlanSlot = false; // 可作为计划区使用
	public boolean ready = false; // 储备完成
	public Map<SlotType, CardPile> slotPileMap = new HashMap<>();

	public static enum SlotType {
		None(0), // 未知
		Store(0), // 储备卡(计划卡）
		Seal(0), // 封印
		Ride(-1), // 骑乘
		Harass(1), // 骚扰
		Charge(1), // 充能
		;

		private int offset = 0;

		private SlotType(int offset) {
			this.offset = offset;
		}

		public int getOffset() {
			return offset;
		}
	}

	public CardPile getAllCards() {
		CardPile cardPile = new CardPile(PileType.SlotPile);
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

	public void put(SlotType slotType, Card card) {
		var pile = slotPileMap.get(slotType);
		if (pile == null) {
			pile = new CardPile(PileType.SlotPile);
			slotPileMap.put(slotType, pile);
		}
		pile.add(card);
	}

	public void remove(SlotType slotType) {
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

	public SlotType getSlotType(Card card) {
		for (var entry : slotPileMap.entrySet()) {
			if (entry.getValue().contains(card)) {
				return entry.getKey();
			}
		}
		return SlotType.None;
	}

	public CardPile getPile(SlotType slotType) {
		var pile = slotPileMap.get(slotType);
		if (pile == null) {
			pile = new CardPile(PileType.SlotPile);
			slotPileMap.put(slotType, pile);
		}
		return pile;
	}

	public void clear() {
		slotPileMap.clear();
	}
}
