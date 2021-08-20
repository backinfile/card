package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import com.backinfile.support.Tuple2;
import com.backinfile.support.Utils;

public class CardPile implements Iterable<Card> {
	private PileType pileType = PileType.None;
	private LinkedList<Card> cards = new LinkedList<Card>();

	public CardPile() {
	}

	public CardPile(CardPile cardPile) {
		this.addAll(cardPile);
	}

	public CardPile(PileType pileType) {
		this.pileType = pileType;
	}

	public static enum PileType {
		None(0), // 未知
		HandPile(10000), // 手牌
		DrawPile(9000), // 抽牌堆
		DiscardPile(8000), // 弃牌
		TrashPile(7000), // 废弃牌
		MarkPile(6000), // 标记牌
		SlotPile(5000), // 储备区牌
		HeroPile(9000), // 英雄卡
		;

		private int zIndexStart;

		private PileType(int zIndexStart) {
			this.zIndexStart = zIndexStart;
		}

		public int getZIndexStart() {
			return zIndexStart;
		}
	}

	public void add(Card card) {
		if (card != null) {
			cards.add(card);
		}
	}

	public void add(int index, Card card) {
		if (card != null) {
			cards.add(index, card);
		}
	}

	public void addAll(Iterable<Card> cards) {
		for (Card card : cards) {
			add(card);
		}
	}

	public boolean remove(Card card) {
		if (card != null) {
			return cards.remove(card);
		}
		return false;
	}

	public void removeAll(CardPile cardPile) {
		for (var card : cardPile) {
			remove(card);
		}
	}

	public void clear() {
		cards.clear();
	}

	public boolean contains(Card card) {
		return cards.contains(card);
	}

	public Card get(int index) {
		return cards.get(index);
	}

	public CardPile getTop(int n) {
		CardPile cardPile = new CardPile();
		for (int i = 0; i < n; i++) {
			cardPile.add(this.get(size() - i - 1));
		}
		return cardPile;
	}

	public CardPile getBottom(int n) {
		CardPile cardPile = new CardPile();
		for (int i = 0; i < n; i++) {
			cardPile.add(this.get(i));
		}
		return cardPile;
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	public int size() {
		return cards.size();
	}

	@Override
	public Iterator<Card> iterator() {
		return new ArrayList<>(cards).iterator();
	}

	public Set<Tuple2<Integer, Card>> cardsWithIndex() {
		var cardsIndex = new HashSet<Tuple2<Integer, Card>>();
		for (int i = 0; i < cards.size(); i++) {
			var card = cards.get(i);
			cardsIndex.add(new Tuple2<Integer, Card>(i, card));
		}
		return cardsIndex;
	}

	public void shuffle() {
		CardPile newCardPile = new CardPile(this);
		this.clear();
		while (!newCardPile.isEmpty()) {
			int rnd = Utils.nextInt(0, newCardPile.size() - 1);
			Card card = newCardPile.get(rnd);
			newCardPile.remove(card);
			this.add(card);
		}
	}

	public PileType getPileType() {
		return pileType;
	}

}
