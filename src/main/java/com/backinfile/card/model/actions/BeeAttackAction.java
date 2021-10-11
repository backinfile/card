package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.MonsterCard.Bee;

public class BeeAttackAction extends TriggerOnceAction {
	public BeeAttackAction(Human human, Card card) {
		this.human = human;
		this.card = card;
	}

	@Override
	public void run() {
		var stores = human.getAllStoreCards(true, true, false, false, false).filter(c -> c instanceof Bee);
		for (var slot : human.getOpponent().cardSlotMap.values()) {
			stores.addAll(slot.getPile(ESlotType.Harass).filter(c -> c instanceof Bee));
		}
		stores.add(card);
		for (var card : stores.reverse()) {
			board.removeCard(card);
			addFirst(new AttackAction(human, card, human.getOpponent()));
		}
	}
}
