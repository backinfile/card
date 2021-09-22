package com.backinfile.card.model.actions;

import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.card.server.humanOper.SelectEmptySlotOper;

public class StoreCardAction extends WaitAction {
	private boolean fast;

	public StoreCardAction(Human human, Card card, boolean fast) {
		this.human = human;
		this.card = card;
		this.fast = fast;
	}

	public StoreCardAction(Human human, Card card) {
		this(human, card, false);
	}

	@Override
	public void init() {
		var emptySlots = human.getEmptySlots(false);
		if (!emptySlots.isEmpty()) {
			var humanOper = new SelectEmptySlotOper(emptySlots.stream().map(s -> s.index).collect(Collectors.toList()),
					ETargetSlotAimType.Store, false, actionString.tips[0]);
			humanOper.setDetachCallback(() -> {
				selectIndex(humanOper.getSelected());
			});
			human.addHumanOper(humanOper);
		} else {
			CardPile toReplace = new CardPile();
			for (var slot : human.cardSlotMap.values()) {
				toReplace.addAll(slot.getPile(ESlotType.Store));
				toReplace.addAll(slot.getPile(ESlotType.Plan));
			}
			if (toReplace.isEmpty()) {
				setDone();
				return;
			}
			var humanOper = new SelectCardOper(toReplace, actionString.tips[1], 1);
			humanOper.setDetachCallback(() -> {
				replaceCard(humanOper.getSelectedPile().get(0));
			});
			human.addHumanOper(humanOper);
		}
	}

	private void replaceCard(Card toReplace) {
		var slot = human.getCardSlotByCard(toReplace);
		slot.remove(toReplace);
		slot.getPile(ESlotType.Store).add(card);
		slot.ready = fast;
		board.modifyCard(toReplace, card);
	}

	private void selectIndex(int index) {
		var slot = human.cardSlotMap.get(index);
		slot.getPile(ESlotType.Store).add(card);
		slot.ready = fast;
		board.modifyCard(card);
	}

	@Override
	public void pulse() {
	}
}
