package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backinfile.card.model.actions.DrawCardAction;
import com.backinfile.card.model.actions.SaveThreatenAction;
import com.backinfile.card.model.actions.TurnEndAction;
import com.backinfile.card.model.actions.TurnStartAction;
import com.backinfile.card.model.actions.DrawCardAction.Type;

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
	public int actionNumber = 0;
	public boolean turnEndMark = false;// 回合标记结束, 所有行动执行结束后，回合真正结束

	// 可被远程使用的属性
	public CardPile selectedPile = new CardPile(); // 当前已确认选择的卡, 不是真正的牌库
	public TargetInfo targetInfo = null; // 当前正在进行的选择

	public void init(CardPile drawPile) {
		this.drawPile.addAll(drawPile);
		this.drawPile.shuffle();

		// 初始化储备位
		for (int i = 1; i <= 5; i++) {
			var cardSlot = new CardSlot();
			cardSlot.asPlanSlot = i == 5;
			cardSlot.index = i;
			cardSlotMap.put(cardSlot.index, cardSlot);
		}
	}

	public final void onGameStart() {
		addLast(new DrawCardAction(this, 5, Type.GameStart));
	}

	public final void onTurnStart() {
		turnEndMark = false;
		actionNumber = 2;
		addLast(new DrawCardAction(this, 3, Type.TurnStart));
		addLast(new TurnStartAction());
	}

	public final void onTurnEnd() {
		turnEndMark = true;
		addLast(new SaveThreatenAction());
		addLast(new TurnEndAction());
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

	public List<CardSlot> getEmptySlots(boolean exceptPlanSlot) {
		List<CardSlot> cardSlots = new ArrayList<>();
		for (var cardSlot : cardSlotMap.values()) {
			if (cardSlot.asPlanSlot && exceptPlanSlot) {
				continue;
			}
			if (cardSlot.getAllCards().isEmpty()) {
				cardSlots.add(cardSlot);
			}
		}
		return cardSlots;
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

	public final void clearSelectInfo() {
		selectedPile.clear();
		targetInfo = null;
	}

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}
}
