package com.backinfile.card.model;

import com.backinfile.support.Param;

public class TargetInfo {

	public static final TargetInfo EMPTY = new TargetInfo(TargetType.None);
	public TargetInfo.TargetType targetType = TargetType.None;
	public int number = 1; // 需求数量 -1表示不限制数量
	public int minNumber = -1; // 需求最小数目 optional==true时需求的数目
	public boolean optional = false; // 是否可选任意张
	public String tip = "select a card"; // 选取提示
	public boolean exceptSelf = true; // 作为skill的目标时，排除自身
	public Param param = new Param(); // 额外参数

	public TargetInfo(TargetInfo.TargetType targetType) {
		this.targetType = targetType;
	}

	public TargetInfo(TargetType targetType, int number, boolean optional) {
		this.targetType = targetType;
		this.number = number;
		this.optional = optional;
	}

	public TargetInfo(TargetType targetType, int number, boolean optional, String tip) {
		this.targetType = targetType;
		this.number = number;
		this.optional = optional;
		this.tip = tip;
	}

	public static enum TargetType {
		None, // 不能选目标
		Confirm, // 确认与否
		Hand, // 自己手牌里选
		Store, // 储备卡
		ReadyStore, // 储备完成的储备
		EmptySlotExceptPlan, // 空白储备位
		StoreSlotExceptPlan, // 有储备的槽
	}

	// 检查棋盘上是否有可以满足
	public boolean test(Board board) { // TODO
		return false;
	}

	// 检查当前选择的卡是否可以满足
	public boolean test(CardPile targetPile) {
		return false;
	}

	public CardPile getNextTargetCardPile(CardPile selectedPile) {
		CardPile cardPile = new CardPile();

		return cardPile;
	}
}