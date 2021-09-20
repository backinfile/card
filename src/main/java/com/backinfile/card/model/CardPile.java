package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.support.Log;
import com.backinfile.support.SysException;
import com.backinfile.support.Tuple2;
import com.backinfile.support.Utils;

public class CardPile implements Iterable<Card> {
	private ECardPileType pileType = ECardPileType.None;
	private LinkedList<Card> cards = new LinkedList<Card>();

	public CardPile() {
	}

	public CardPile(Iterable<Card> cards) {
		this.addAll(cards);
	}

	public CardPile(Card... cards) {
		for (var card : cards) {
			this.add(card);
		}
	}

	public CardPile(ECardPileType pileType) {
		this.pileType = pileType;
	}

	public void add(Card card) {
		if (card != null) {
			if (!cards.stream().anyMatch(c -> c.id == card.id)) {
				cards.add(card);
			}
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

	public List<Long> getCardIdList() {
		return cards.stream().map(c -> c.id).collect(Collectors.toList());
	}

	public boolean remove(Card card) {
		if (card != null) {
			return cards.remove(card);
		}
		return false;
	}

	public boolean removeAll(Predicate<Card> predicate) {
		boolean removed = false;
		for (var card : new ArrayList<>(cards)) {
			if (predicate.test(card)) {
				removed |= remove(card);
			}
		}
		return removed;
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

	public boolean contains(long id) {
		return cards.stream().anyMatch(c -> c.id == id);
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

	public Card pollTop() {
		return cards.pollLast();
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
		if (isEmpty()) {
			return;
		}
		CardPile newCardPile = new CardPile(this);
		this.clear();
		while (!newCardPile.isEmpty()) {
			int rnd = Utils.nextInt(0, newCardPile.size());
			Card card = newCardPile.get(rnd);
			newCardPile.remove(card);
			this.add(card);
		}
	}

	public ECardPileType getPileType() {
		return pileType;
	}

	public CardPile copy() {
		return new CardPile(this);
	}

}
