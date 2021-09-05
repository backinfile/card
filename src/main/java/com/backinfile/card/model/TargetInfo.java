package com.backinfile.card.model;

import com.backinfile.card.gen.GameMessage.DTargetInfo;
import com.backinfile.card.gen.GameMessage.DTargetSelect;
import com.backinfile.card.model.cards.SlotHelpCard;
import com.backinfile.card.model.cards.SlotHelpCard.SlotOccupyCard;

// human正在进行的操作
public class TargetInfo {
	public Human human;
	public DTargetInfo targetInfo = null; // 当前正在进行的选择
	public DTargetSelect targetSelect = null;

	public TargetInfo(Human human) {
		this.human = human;
	}

	public void setTargetInfo(DTargetInfo targetInfo) {
		this.targetInfo = targetInfo;
	}

	public boolean isSelected() {
		return targetSelect != null;
	}

	public Card getTargetSelectOne() {
		if (targetSelect == null || targetSelect.getSelectedCardCount() == 0) {
			return null;
		}
		return human.board.getCard(targetSelect.getSelectedCardList().get(0));
	}

	public int getSelectSlotIndex() {
		var helpCard = (SlotHelpCard) human.targetInfo.getTargetSelectOne();
		if (helpCard == null) {
			return -1;
		}
		return helpCard.getSlotIndex();
	}

	public CardPile getTargetSelected() {
		CardPile cardPile = new CardPile();
		if (targetSelect == null) {
			return cardPile;
		}
		for (var id : targetSelect.getSelectedCardList()) {
			cardPile.add(human.board.getCard(id));
		}
		return cardPile;
	}

	public void clear() {
		targetInfo = null;
		targetSelect = null;
	}

	// 测试是否可以进行选择
	public boolean test() {
		return false; // TODO
	}
}
