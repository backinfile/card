package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;

public class WhiteTigerRefreshSlotAction extends TriggerOnceAction {
	private CardSlot cardSlot;
	private Card mark;

	public WhiteTigerRefreshSlotAction(Human human, CardSlot slot, Card mark) {
		this.human = human;
		this.cardSlot = slot;
		this.mark = mark;
	}

	@Override
	public void run() {
		board.removeCard(mark);

		addFirst(new RefreshSlotAction());
		addFirst(new DiscardCardAction(human, cardSlot.getPile(ESlotType.Store)));
		cardSlot.getPile(ESlotType.Store).add(mark);
		board.modifyCard(mark);
	}

}
