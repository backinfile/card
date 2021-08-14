package com.backinfile.card.model;

import java.util.LinkedList;

import com.backinfile.card.Log;
import com.backinfile.card.support.IAlive;

public class ActionQueue implements IAlive {
	public Board board;
	public Action curAction = null;
	public LinkedList<Action> actions = new LinkedList<>(); // 当前执行队列

	public ActionQueue(Board board) {
		this.board = board;
	}

	/**
	 * 一般情况下使用这个
	 */
	public void addLast(Action action) {
		actions.addLast(action);
	}

	public void addFirst(Action action) {
		actions.addFirst(action);
	}

	@Override
	public void init() {
	}

	@Override
	public void pulse() {
		pulseCurAction();
	}

	private void pulseCurAction() {
		if (curAction == null) {
			if (actions.isEmpty()) {
				return;
			}
			// 获取新action
			curAction = actions.pollFirst();
			curAction.board = board;
			// 初始化
			curAction.init();
		}
		// 心跳
		try {
			curAction.pulse();
		} catch (Exception e) {
			Log.game.error("error in action.pulse()", e);
		}
		// 检查是否完成
		if (curAction.isDone()) {
			// 完成后回收资源
			curAction.dispose();
			curAction = null;
		}

	}

	public boolean isEmpty() {
		return actions.isEmpty();
	}
}
