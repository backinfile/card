package com.backinfile.card.model;

import java.util.List;

import com.backinfile.support.Param;

public class TargetInfo {

	public static final TargetInfo EMPTY = new TargetInfo(TargetType.None);
	public TargetInfo.TargetType targetType = TargetType.None;
	public int number = 1; // 需求数量 -1表示不限制数量
	public int minNumber = -1; // 如果>=0， 则可选取minNumber~number张
	public String tip = "select a card"; // 选取提示
	public boolean exceptSelf = true; // 作为skill的目标时，排除自身
	public Param param = new Param(); // 额外参数

	public TargetSelect select = null;

	public static class TargetSelect {
		public CardPile selected = new CardPile();
		public boolean confirm = false;
	}

	public TargetInfo(TargetInfo.TargetType targetType) {
		this.targetType = targetType;
	}

	public TargetInfo(TargetType targetType, int number) {
		this.targetType = targetType;
		this.number = number;
	}

	public TargetInfo(TargetType targetType, int number, int minNumber) {
		this.targetType = targetType;
		this.number = number;
		this.minNumber = minNumber;
	}

	public TargetInfo(TargetType targetType, int number, int minNumber, String tip) {
		this.targetType = targetType;
		this.number = number;
		this.minNumber = minNumber;
		this.tip = tip;
	}

	public TargetInfo(TargetType targetType, int number, String tip) {
		this.targetType = targetType;
		this.number = number;
		this.tip = tip;
	}

	public TargetInfo(TargetType targetType, String tip) {
		this.targetType = targetType;
		this.number = -1;
		this.tip = tip;
	}

	public static enum TargetType {
		None, // 不能选目标
		Confirm, // 确认与否
		Hand, // 自己手牌里选
		Store, // 储备卡
		ReadyStore, // 储备完成的储备
		EmptySlot, // 空白储备位
		EmptySlotExceptPlan, // 空白储备位
		StoreSlotExceptPlan, // 有储备的槽
	}

	// 检查棋盘上是否有可以满足
	public boolean test(Board board, Human human) { // TODO
		int minestNumber = minNumber >= 0 ? minNumber : number;
		switch (targetType) {
		case None:
			return true;
		case Hand:
			return human.handPile.size() >= minestNumber;
		case Confirm:
			return true;
		case EmptySlot:
			return human.getEmptySlots(false).size() >= minestNumber;
		case EmptySlotExceptPlan:
			return human.getEmptySlots(true).size() >= minestNumber;
		case ReadyStore:
			return human.getAllStoreCards(true).size() >= minestNumber;
		case Store:
			return human.getAllStoreCards(false).size() >= minestNumber;
		case StoreSlotExceptPlan:
			return human.getStoreSlots(false, true).size() >= minestNumber;
		default:
			break;
		}
		return false;
	}

	// 检查当前选择的卡是否可以满足
	public boolean test(Board board, Human human, CardPile targetPile) {
		return false;
	}

	// 获取下一张可选目标
	public CardPile getNextTargetCardPile(Board board, CardPile selectedPile) {
		CardPile cardPile = new CardPile();

		return cardPile;
	}

	public void setSelected(List<Card> selectedCards) {
		select = new TargetSelect();
		select.selected.addAll(selectedCards);
	}

	public boolean isSelected() {
		return select != null;
	}

	public Card getSelectedOne() {
		if (select.selected.isEmpty()) {
			return null;
		}
		return select.selected.get(0);
	}

	public CardPile getSelected() {
		return select.selected;
	}

	public boolean isConfirm() {
		return select.confirm;
	}
}