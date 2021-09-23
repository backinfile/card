package com.backinfile.card.model;

import com.backinfile.card.model.LocalString.LocalActionString;
import com.backinfile.support.IAction;

/**
 * 一个动作，通常情况下，Board上的数据修改都通过Action来进行
 */
public abstract class Action implements IAction {

	public LocalActionString actionString;
	public Board board; // 当前棋盘（自动设置）

	// 定义一些变量方便使用，并不强制要求设置
	public Human human; // 当前正在执行这个动作的human
	public Card card; // 当前卡
	public Card targetCard; // 如果需要，目标卡
	public int number; // 如果需要，数目

	public Action() {
		this.actionString = LocalString.getActionString(getClass().getSimpleName());
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
	 * 必须要立即执行的动作用addFirst，一般情况下用addLast
	 */
	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}
}
