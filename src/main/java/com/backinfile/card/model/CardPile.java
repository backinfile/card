package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
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

	public CardPile(Iterator<Card> cards) {
		cards.forEachRemaining(this::add);
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
			if (!cards.contains(card)) {
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

	public Card getAny() {
		return get(0);
	}

	public Card getCard(long id) {
		for (var card : cards) {
			if (card.id == id) {
				return card;
			}
		}
		return null;
	}

	public CardPile getTop(int n) {
		CardPile cardPile = new CardPile();
		for (int i = 0; i < n; i++) {
			cardPile.add(this.get(size() - i - 1));
		}
		return cardPile;
	}

	public Card getTop() {
		return cards.getLast();
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

	public CardPile pollTop(int n) {
		var cardPile = getTop(n);
		for (var card : cardPile) {
			remove(card);
		}
		return cardPile;
	}

	public CardPile getRandom(int n) {
		CardPile copy = new CardPile(this);
		CardPile polls = new CardPile();
		for (int i = 0; i < n; i++) {
			if (copy.isEmpty()) {
				break;
			}
			var card = copy.get(Utils.nextInt(copy.size()));
			copy.remove(card);
			polls.add(card);
		}
		return polls;
	}

	public CardPile pollRandom(int n) {
		CardPile polls = new CardPile();
		for (int i = 0; i < n; i++) {
			if (isEmpty()) {
				break;
			}
			var card = get(Utils.nextInt(size()));
			remove(card);
			polls.add(card);
		}
		return polls;
	}

	public CardPile reverse() {
		Collections.reverse(cards);
		return this;
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

	public CardPile filter(Predicate<Card> predicate) {
		return new CardPile(cards.stream().filter(predicate).iterator());
	}

	public CardPile filter(Class<? extends Card> clazz) {
		return new CardPile(cards.stream().filter(c -> clazz.isInstance(c)).iterator());
	}

	public Stream<Card> stream() {
		return cards.stream();
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
