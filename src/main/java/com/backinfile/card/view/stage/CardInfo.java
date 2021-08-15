package com.backinfile.card.view.stage;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;

public class CardInfo { // 记录一张卡与其位置
	public Card card;
	public Human human;
	public int index;
	public CardInfo.PileType pileType;
	public CardInfo.SlotType slotType;

	public CardInfo(Card card, Human human, int index, PileType pileType, SlotType slotType) {
		this.card = card;
		this.human = human;
		this.index = index;
		this.pileType = pileType;
		this.slotType = slotType;
	}

	public static enum PileType {
		HandPile(10000), // 手牌
		DrawPile(9000), // 抽牌堆
		DiscardPile(8000), // 弃牌
		TrashPile(7000), // 废弃牌
		MarkPile(6000), // 标记牌
		SlotPile(5000), // 储备区牌
		;

		private int zIndexStart;

		private PileType(int zIndexStart) {
			this.zIndexStart = zIndexStart;
		}

		public int getZIndexStart() {
			return zIndexStart;
		}
	}

	public static enum SlotType {
		UnReadyStore(false, true, 0), // 尚未储备完成的储备
		Store(false, false, 0), // 储备完成的储备
		Seal(true, false, 0), // 封印
		Ride(false, true, -1), // 骑乘
		Harass(false, true, 1), // 骚扰
		Charge(false, true, -1), // 充能

		;

		private boolean flip;
		private boolean cross;
		private int zIndexOffSet;

		private SlotType(boolean flip, boolean cross, int zIndexOffSet) {
			this.flip = flip;
			this.cross = cross;
			this.zIndexOffSet = zIndexOffSet;
		}

		public boolean isCross() {
			return cross;
		}

		public boolean isFlip() {
			return flip;
		}

		public int getZIndexOffSet() {
			return zIndexOffSet;
		}
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
			return pileType.getZIndexStart() + slotType.getZIndexOffSet();
		default:
			break;
		}
		return 0;
	}

}