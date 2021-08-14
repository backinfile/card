package com.backinfile.card.model;

import com.backinfile.card.support.IAlive;
import com.backinfile.card.support.IDisposable;

public abstract class Action implements IAlive, IDisposable {

	public Board board; // 当前棋盘（自动设置）
	public Human human; // 当前正在执行这个动作的human
	public Card card; // 当前卡
	public Card targetCard; // 如果需要，目标卡

	public Action() {
	}

	public Action(Human human) {
		this.human = human;
	}

	public Action(Human human, Card card) {
		this.human = human;
		this.card = card;
	}

	public Action(Human human, Card card, Card targetCard) {
		this.human = human;
		this.card = card;
		this.targetCard = targetCard;
	}

	public void init() {
	}

	@Override
	public void pulse() {
	}

	public boolean isDone() {
		return true;
	}

	public void dispose() {

	}

	public void setTargetCard(Card targetCard) {
		this.targetCard = targetCard;
	}

	public Card getTargetCard() {
		return targetCard;
	}

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	/**
	 * Action中紧急的动作用addFirst，不紧急的用addLast
	 */
	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}
}
