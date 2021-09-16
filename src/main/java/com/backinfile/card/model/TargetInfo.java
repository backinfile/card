package com.backinfile.card.model;

import com.backinfile.card.gen.GameMessageHandler.DTargetInfo;
import com.backinfile.card.gen.GameMessageHandler.DTargetSelect;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.model.cards.SlotHelpCard;

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

	public boolean needSelectTarget() {
		return targetInfo != null;
	}

	public void clear() {
		targetInfo = null;
		targetSelect = null;
	}

	// 测试是否能满足条件 // TODO
	public boolean test() {
		if (targetInfo == null) {
			return true;
		}
		// 无需卡牌也能满足条件
		var minNumber = targetInfo.getMinNumber();
		if (minNumber <= 0) {
			return true;
		}
		var targetHuman = targetInfo.getOpponent() ? human.getOpponent() : human;
		switch (targetInfo.getType()) {
		case None:
		case Confirm:
			return true;
		case EmptySlot:
			return targetHuman.getEmptySlots(targetInfo.getExceptPlan()).size() >= minNumber;
		case HandPile:
		case DiscardPile:
		case DrawPile:
		case Store:
		case StoreInSlot:
			return getCommonSelectFromPile().size() >= minNumber;
		default:
			break;
		}
		return false;
	}

	// 分步进行卡牌选择
	public static class SelectCardStepInfo {
		public CardPile cardPile = new CardPile(); // 为空表示选择完成
		public boolean optional = false; // 可结束选择了

		public SCSelectCards toMsg() {
			SCSelectCards msg = new SCSelectCards();
			msg.addAllCardIds(cardPile.getCardIdList());
			msg.setCancel(optional);
			return msg;
		}
	}

	// 分步进行卡牌选择
	public SelectCardStepInfo stepSelectCard(CardPile selected) {
		SelectCardStepInfo info = new SelectCardStepInfo();
		var minNumber = targetInfo.getMinNumber();
		var maxNumber = targetInfo.getMaxNumber() < 0 ? Integer.MAX_VALUE : targetInfo.getMaxNumber();

		if (selected.size() >= maxNumber) {
			return info;
		}
		if (selected.size() >= minNumber) {
			info.optional = true;
		}

		switch (targetInfo.getType()) {
		case None:
		case Confirm:
		case EmptySlot:
			break;
		case DiscardPile:
		case HandPile:
		case DrawPile:
		case Store:
		case StoreInSlot: {
			var cardPile = getCommonSelectFromPile();
			cardPile.removeAll(selected);
			info.cardPile.addAll(cardPile);
			return info;
		}
		default:
			break;
		}

		return info;
	}

	public boolean isSelectCardType() {
		if (targetInfo == null) {
			return false;
		}
		switch (targetInfo.getType()) {
		case None:
		case Confirm:
		case EmptySlot:
			return false;
		case DiscardPile:
		case DrawPile:
		case HandPile:
		case Store:
		case StoreInSlot:
			return true;
		}
		return false;
	}

	private CardPile getCommonSelectFromPile() {
		var targetHuman = targetInfo.getOpponent() ? human.getOpponent() : human;
		switch (targetInfo.getType()) {
		case None:
		case Confirm:
		case EmptySlot:
			return null;
		case HandPile:
			return targetHuman.handPile.copy();
		case DiscardPile:
			return targetHuman.discardPile.copy();
		case DrawPile:
			return targetHuman.drawPile.copy();
		case Store:
			return targetHuman.getAllStoreCards(targetInfo.getOnlyReady(), targetInfo.getExceptPlan(),
					targetInfo.getExceptHand());
		case StoreInSlot:
			return targetHuman.getAllStoreInSlot(targetInfo.getOnlyReady(), targetInfo.getExceptPlan());
		default:
			break;
		}
		return null;
	}
}
