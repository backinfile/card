package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SelectToPlanAction extends WaitAction {
	public SelectToPlanAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		if (human.handPile.isEmpty()) {
			setDone();
			return;
		}
		// 计划区被封印了，不能计划
		CardSlot slot = human.getPlanSlot();
		if (!slot.getPile(ESlotType.Seal).isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(human.handPile, actionString.tip, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onPlan(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onPlan(Card planCard) {
		board.removeCard(planCard);

		CardSlot slot = human.getPlanSlot();
		// 弃置计划区其他所有的牌
		addFirst(new DiscardCardAction(human, slot.getAllCards()));
		slot.getPile(ESlotType.Plan).add(planCard);
		slot.ready = false;
		board.modifyCard(planCard, human.handPile);
	}
}
