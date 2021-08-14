package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.backinfile.card.support.Utils;

public class CardPile implements Iterable<Card> {
	private LinkedList<Card> cards = new LinkedList<Card>();

	public CardPile() {
	}

	public CardPile(CardPile cardPile) {
		this.addAll(cardPile);
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

}
