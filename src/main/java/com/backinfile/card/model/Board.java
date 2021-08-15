package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.backinfile.card.model.actions.ChangeBoardStateAction;
import com.backinfile.card.model.actions.DispatchAction;
import com.backinfile.support.IAlive;
import com.backinfile.support.Utils;

public abstract class Board implements IAlive {
	public List<Human> humans = new ArrayList<>();
	public Human curTurnHuman = null;
	private ActionQueue actionQueue;
	private LinkedList<Human> nextTurnQueue = new LinkedList<>();
	public int turnCount = 1; // 公共轮次
	public int playerTurnCount = 0; // 玩家轮次之和

	public BoardState state = BoardState.GamePrepare;

	public CardPile aidCardPile = new CardPile();

	public static enum BoardState {
		GamePrepare, TurnStart, InTurn, TurnEnd,
	}

	public void init() {
		actionQueue = new ActionQueue(this);
		actionQueue.init();

		for (var human : humans) {
			human.board = this;
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

	public boolean removeCard(Card card) {
		for (var human : humans) {
			if (human.removeCard(card)) {
				return true;
			}
		}
		return false;
	}

	public void castSkill(int cardId) {
	}

	public ActionQueue getActionQueue() {
		return actionQueue;
	}

	public Human getHuman(long id) {
		for (var human : humans) {
			if (human.id == id) {
				return human;
			}
		}
		return null;
	}

	public Human getOpponent(Human human) {
		int index = humans.indexOf(human);
		return humans.get((index + 1) % humans.size());
	}

}
