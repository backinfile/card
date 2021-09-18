package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.manager.CardManager;
import com.backinfile.card.manager.ConstGame;
import com.backinfile.card.model.Card.CardType;
import com.backinfile.card.model.actions.DrawCardAction;
import com.backinfile.card.model.actions.RestoreActionNumberAction;
import com.backinfile.card.model.actions.SaveThreatenAction;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;

public class Human extends SkillCaster {
	// 固有属性
	public String token;
	public String playerName;

	// 被赋值属性
	public Board board;

	// 可变属性
	public CardPile heroPile = new CardPile(ECardPileType.HeroPile);
	public CardPile handPile = new CardPile(ECardPileType.HandPile);
	public CardPile markPile = new CardPile(ECardPileType.MarkPile);
	public CardPile drawPile = new CardPile(ECardPileType.DrawPile);
	public CardPile discardPile = new CardPile(ECardPileType.DiscardPile);
	public CardPile trashPile = new CardPile(ECardPileType.TrashPile);
	public Map<Integer, CardSlot> cardSlotMap = new HashMap<>();
	public int actionPoint = 0;

	// 可被外部Server修改的属性
	public TargetInfo targetInfo; // 当前正在进行的选择
	public LinkedList<DSyncBase> msgCacheQueue = new LinkedList<>(); // 消息缓存

	public void init(DHumanInit humanInit) {
		this.targetInfo = new TargetInfo(this);
		this.token = humanInit.getControllerToken();
		this.playerName = humanInit.getPlayerName();
		this.heroPile.add(CardManager.getCard(humanInit.getHeroCard(), token));
		for (var entry : humanInit.getPileList()) {
			var name = entry.getCard();
			var number = entry.getCount();
			for (int i = 0; i < number; i++) {
				this.drawPile.add(CardManager.getCard(name, token));
			}
		}
		this.drawPile.shuffle();

		// 初始化储备位
		for (int i = 1; i <= ConstGame.SlotPileNumber; i++) {
			var cardSlot = new CardSlot();
			cardSlot.asPlanSlot = i == ConstGame.SlotPileNumber;
			cardSlot.index = i;
			cardSlotMap.put(cardSlot.index, cardSlot);
		}
	}

	public final void onGameStart() {
		addLast(new DrawCardAction(this, 5));
	}

	public final void onTurnStart() {
		addLast(new RestoreActionNumberAction(this, 2));
		addLast(new DrawCardAction(this, 3));
	}

	public final void onTurnEnd() {
		addLast(new SaveThreatenAction());
	}

	public boolean removeCard(Card card) {
		if (markPile.remove(card)) {
			return true;
		}
		if (drawPile.remove(card)) {
			return true;
		}
		if (discardPile.remove(card)) {
			return true;
		}
		if (trashPile.remove(card)) {
			return true;
		}
		for (var slot : cardSlotMap.values()) {
			if (slot.removeCard(card)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取所有卡牌
	 */
	public CardPile getAllCards() {
		CardPile cardPile = new CardPile();
		cardPile.addAll(heroPile);
		cardPile.addAll(markPile);
		cardPile.addAll(drawPile);
		cardPile.addAll(discardPile);
		cardPile.addAll(trashPile);
		cardPile.addAll(handPile);
		for (var slot : cardSlotMap.values()) {
			cardPile.addAll(slot.getAllCards());
		}
		return cardPile;
	}

	/**
	 * 获取除了储备位之外的牌
	 */
	public List<CardPile> getNormalPiles() {
		var list = new ArrayList<CardPile>();
		list.add(heroPile);
		list.add(markPile);
		list.add(drawPile);
		list.add(discardPile);
		list.add(trashPile);
		list.add(handPile);
		return list;
	}

	/**
	 * 获取所有空的slot
	 */
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

	/**
	 * 获取所有具有储备的slot
	 */
	public List<CardSlot> getStoreSlots(boolean needReady, boolean exceptPlanSlot) {
		List<CardSlot> cardSlots = new ArrayList<>();
		for (var cardSlot : cardSlotMap.values()) {
			if (!cardSlot.getPile(ESlotType.Store).isEmpty()) {
				if (needReady && !cardSlot.ready) {
					continue;
				}
				if (exceptPlanSlot && cardSlot.asPlanSlot) {
					continue;
				}
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

	/**
	 * 获取所有储备 TODO 根据skill实时修改
	 */
	public CardPile getAllStoreCards(boolean onlyReady, boolean exceptPlan, boolean exceptHand) {
		CardPile cardPile = new CardPile();
		cardPile.addAll(getAllStoreInSlot(onlyReady, exceptPlan));
		return cardPile;
	}

	/**
	 * 获取所有储备位上的储备
	 */
	public CardPile getAllStoreInSlot(boolean onlyReady, boolean exceptPlan) {
		CardPile cardPile = new CardPile();
		for (var slot : cardSlotMap.values()) {
			if (onlyReady && !slot.ready) {
				continue;
			}
			if (exceptPlan && slot.asPlanSlot) {
				continue;
			}
			cardPile.addAll(slot.getPile(ESlotType.Store));
			cardPile.addAll(slot.getPile(ESlotType.Plan));
		}
		cardPile.removeAll(card -> card.mainType != CardType.STORE);
		return cardPile;
	}

	public CardSlot getCardSlotByCard(Card card) {
		for (var slot : cardSlotMap.values()) {
			if (slot.getAllCards().contains(card)) {
				return slot;
			}
		}
		return null;
	}

	// 获取对手
	public Human getOpponent() {
		return board.getOpponent(this);
	}

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}
}
