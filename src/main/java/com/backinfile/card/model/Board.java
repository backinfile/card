package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.backinfile.card.gen.GameMessage.DBoardInit;
import com.backinfile.card.gen.GameMessage.DCardInfo;
import com.backinfile.card.gen.GameMessage.DCardPileInfo;
import com.backinfile.card.model.actions.ChangeBoardStateAction;
import com.backinfile.card.model.actions.DispatchAction;
import com.backinfile.support.IAlive;
import com.backinfile.support.Utils;

public class Board implements IAlive {
	public List<Human> humans = new ArrayList<>();
	public Human curTurnHuman = null;
	private ActionQueue actionQueue;
	private LinkedList<Human> nextTurnQueue = new LinkedList<>();
	public int turnCount = 1; // 公共轮次
	public int playerTurnCount = 0; // 玩家轮次之和
	public boolean requireFlushCardView = false;

	public BoardState state = BoardState.GamePrepare;

	public CardPile aidCardPile = new CardPile();

	public static enum BoardState {
		GamePrepare, TurnStart, InTurn, TurnEnd,
	}

	public void init(DBoardInit boardInit) {
		actionQueue = new ActionQueue(this);
		actionQueue.init();

		Utils.setRndSeed(boardInit.getSeed());
		for (var dhuman : boardInit.getHumanInitsList()) {
			Human human = new Human();
			human.board = this;
			human.init(dhuman);
			humans.add(human);
		}
	}

	@Override
	public void pulse() {
		if (state == BoardState.GamePrepare) {
			for (var human : humans) {
				human.onGameStart();
			}
			actionQueue.addLast(new DispatchAction(humans));
			actionQueue.addLast(new ChangeBoardStateAction(BoardState.TurnStart));
		} else if (state == BoardState.TurnStart) {
			if (nextTurnQueue.isEmpty()) {
				int rnd = Utils.nextInt(0, humans.size());
				curTurnHuman = humans.get(rnd);
			} else {
				curTurnHuman = nextTurnQueue.pollFirst();
			}
			playerTurnCount++;
			// 找到下一个回合的玩家
			if (nextTurnQueue.isEmpty()) {
				int curIndex = humans.indexOf(curTurnHuman);
				nextTurnQueue.addLast(humans.get((curIndex + 1) % humans.size()));
			}
			// 回合开始
			curTurnHuman.onTurnStart();
			playerTurnCount++;
		} else if (state == BoardState.InTurn) {

		} else if (state == BoardState.TurnEnd) {
			if (playerTurnCount % humans.size() == 0) {
				turnCount++;
			}
			actionQueue.addLast(new ChangeBoardStateAction(BoardState.TurnStart));
		}

		actionQueue.pulse();
	}

	public void precess() {

	}

	public boolean removeCard(Card card) {
		for (var human : humans) {
			if (human.removeCard(card)) {
				return true;
			}
		}
		return false;
	}

	public ActionQueue getActionQueue() {
		return actionQueue;
	}

	public boolean isWaitingHumanOper() {
		for (var human : humans) {
			if (human.targetInfo != null && !human.targetInfo.isSelected()) {
				return true;
			}
		}
		if (actionQueue.isEmpty()) {
			return true;
		}
		return false;
	}

	public Human getHuman(String token) {
		for (var human : humans) {
			if (human.token.equals(token)) {
				return human;
			}
		}
		return null;
	}

	public Human getAnyHuman() {
		return humans.get(0);
	}

	public Human getOpponent(Human human) {
		int index = humans.indexOf(human);
		return humans.get((index + 1) % humans.size());
	}

	public final Map<Card, DCardInfo> getAllCardInfo() {
		var cardInfos = new HashMap<Card, DCardInfo>();
		for (var human : humans) {
			for (var cardPile : human.getNormalPiles()) {
				for (var tuple : cardPile.cardsWithIndex()) {
					var card = tuple.value2;
					var index = tuple.value1;
					var cardInfo = new DCardInfo();
					cardInfo.setId(card.id);
					cardInfo.setSn(card.cardString.sn);
					DCardPileInfo pileInfo = new DCardPileInfo();
					cardInfo.setPileInfo(pileInfo);
					pileInfo.setPlayerToken(human.token);
					pileInfo.setPileType(cardPile.getPileType());
					pileInfo.setPileSize(cardPile.size());
					pileInfo.setPileIndex(index);
					cardInfos.put(card, cardInfo);
				}
			}
			for (var cardSlot : human.cardSlotMap.values()) {
				for (var entry : cardSlot.slotPileMap.entrySet()) {
					var slotType = entry.getKey();
					var pile = entry.getValue();
					for (var tuple : pile.cardsWithIndex()) {
						var card = tuple.value2;
						var index = tuple.value1;
						var cardInfo = new DCardInfo();
						cardInfo.setId(card.id);
						cardInfo.setSn(card.cardString.sn);
						DCardPileInfo pileInfo = new DCardPileInfo();
						cardInfo.setPileInfo(pileInfo);
						pileInfo.setPlayerToken(human.token);
						pileInfo.setPileType(pile.getPileType());
						pileInfo.setSlotType(slotType);
						pileInfo.setPileSize(pile.size());
						pileInfo.setPileIndex(index);
						pileInfo.setAsPlanSlot(cardSlot.asPlanSlot);
						pileInfo.setReady(cardSlot.ready);
						cardInfos.put(card, cardInfo);
					}
				}
			}
		}
		return cardInfos;
	}

	public Card getCard(long cardId) { // TODO
		return null;
	}

}
