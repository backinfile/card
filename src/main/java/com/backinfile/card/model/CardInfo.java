package com.backinfile.card.model;

import com.backinfile.card.model.CardPile.PileType;
import com.backinfile.card.model.CardSlot.SlotType;

public class CardInfo { // 记录一张卡与其位置
	public Card card;
	public Human human;
	public int index;
	public int maxLength;
	public PileType pileType;
	public SlotType slotType;

	public CardInfo(Card card, Human human, int index, int maxLength, PileType pileType, SlotType slotType) {
		this.card = card;
		this.human = human;
		this.index = index;
		this.maxLength = maxLength;
		this.pileType = pileType;
		this.slotType = slotType;
	}

	public CardInfo(Card card, Human human, int index, int maxLength, PileType pileType) {
		this(card, human, index, maxLength, pileType, SlotType.None);
	}

	public long getId() {
		return card.id;
	}

	public boolean showing() {
		switch (pileType) {
		case DrawPile:
		case DiscardPile:
		case TrashPile:
		case MarkPile:
			return false;
		default:
			break;
		}
		return true;
	}

	// 越大距离屏幕外侧越近
	public int getIndex() {
		switch (pileType) {
		case DrawPile:
		case DiscardPile:
		case TrashPile:
		case MarkPile:
		case HandPile:
			return pileType.getZIndexStart();
		case SlotPile:
			return pileType.getZIndexStart() + slotType.getOffset();
		default:
			break;
		}
		return 0;
	}

}