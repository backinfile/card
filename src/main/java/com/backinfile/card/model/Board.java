package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.backinfile.card.support.IAlive;
import com.backinfile.card.support.Utils;

public abstract class Board implements IAlive {
	public List<Human> humans = new ArrayList<>();
	public Human curTurnHuman = null;
	private ActionQueue actionQueue;
	private LinkedList<Human> nextTurnQueue = new LinkedList<>();
	public int turnCount = 0; // 公共轮次
	public int playerTurnCount = 0; // 玩家轮次之和

	public void init() {
		actionQueue = new ActionQueue(this);
		actionQueue.init();

		for (var human : humans) {
			human.board = this;
		}
	}

	@Override
	public void pulse() {
		if (turnCount == 0) {
			turnCount = 1;
			playerTurnCount = 0;

			for (var human : humans) {
				human.onGameStart();
			}
		}

		// 找到当前回合的玩家
		if (curTurnHuman == null) {
			if (nextTurnQueue.isEmpty()) {
				int rnd = Utils.nextInt(0, humans.size());
				curTurnHuman = humans.get(rnd);
			} else {
				curTurnHuman = nextTurnQueue.pollFirst();
			}
			playerTurnCount++;
			if (playerTurnCount % humans.size() == 0) {
				turnCount++;
			}
			// 回合开始
			curTurnHuman.onTurnStart();
			// 找到下一个回合的玩家
			if (nextTurnQueue.isEmpty()) {
				int curIndex = humans.indexOf(curTurnHuman);
				nextTurnQueue.addLast(humans.get((curIndex + 1) % humans.size()));
			}
		}

		processActionQueue();
	}

	private void processActionQueue() {
		while (!actionQueue.isEmpty()) {
			actionQueue.pulse();
		}
	}

	public void castSkill(int cardId) {
	}

	public ActionQueue getActionQueue() {
		return actionQueue;
	}

}
