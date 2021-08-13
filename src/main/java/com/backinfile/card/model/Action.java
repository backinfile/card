package com.backinfile.card.model;

import com.backinfile.card.support.IAlive;
import com.backinfile.card.support.IDisposable;

public abstract class Action implements IAlive, IDisposable {

	public Board board; // 当前棋盘（自动设置）
	public Human hero; // 当前hero
	public Card card; // 当前卡
	public Card targetCard; // 如果需要，目标卡

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
}
