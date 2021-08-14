package com.backinfile.card.model;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.model.actions.DrawCardAction;

public class Human extends SkillCaster {
	// 固有属性
	public long id;

	// 被赋值属性
	public Board board;

	// 可变属性
	public Card heroCard;
	public CardPile handPile = new CardPile();
	public Map<Integer, CardSlot> cardSlotMap = new HashMap<>();
	public CardPile markPile = new CardPile();
	public CardPile drawPile = new CardPile();
	public CardPile discardPile = new CardPile();
	public CardPile trashPile = new CardPile();

	// 可被远程使用的属性
	public CardPile selectedPile = new CardPile(); // 当前已确认选择的卡
	public TargetInfo targetInfo = null; // 当前正在进行的选择

	public final void onTurnStart() {
		addLast(new DrawCardAction(this, 3, true));
	}

	public final void onTurnEnd() {
	}

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

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}
}
