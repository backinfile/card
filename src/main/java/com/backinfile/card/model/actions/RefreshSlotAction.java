package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardPile;

// 使用了储备后，需要手动刷新一下储备位
public class RefreshSlotAction extends TriggerOnceAction {

	@Override
	public void run() {
		// 如果没有储备牌，则弃置骑乘牌
		CardPile toDiscard = new CardPile();
		for (var human : board.humans) {
			for (var slot : human.cardSlotMap.values()) {
				if (slot.getPile(ESlotType.Store).isEmpty()) {
					toDiscard.addAll(slot.getPile(ESlotType.Ride));
				}
			}
		}
		if (!toDiscard.isEmpty()) {
			addFirst(new DiscardCardAction(human, toDiscard));
		}
	}

}
