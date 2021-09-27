package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.chapter2.MonsterCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard.MonsterSkillType;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SelectToRideAction extends WaitAction {
	public SelectToRideAction(Human human, Card rideCard) {
		this.human = human;
		this.card = rideCard;
	}

	@Override
	public void init() {
		CardPile toRide = human.getAllStoreInSlot(true, true, false, false).filter(this::ridable);
		if (toRide.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(toRide, actionString.tip, 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelected(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelected(Card store) {
		// 卸掉所有骑乘牌
		var discards = new CardPile();
		for (var cardSlot : human.cardSlotMap.values()) {
			discards.addAll(cardSlot.getPile(ESlotType.Ride));
		}
		if (!discards.isEmpty()) {
			addFirst(new DiscardCardAction(human, discards));
		}

		// 骑上这个
		board.removeCard(card);
		CardSlot slot = human.getCardSlotByCard(store);
		slot.getPile(ESlotType.Ride).add(card);
		board.modifyCard(card);

		// 如果骑上的是计划卡，将其转移到储备区
		slot.getPile(ESlotType.Store).addAll(slot.getPile(ESlotType.Plan));
		slot.getPile(ESlotType.Plan).clear();
	}

	private boolean ridable(Card card) {
		if (card instanceof MonsterCard) {
			if (((MonsterCard) card).isMonsterType(MonsterSkillType.Ride)) {
				return true;
			}
		}
		return false;
	}
}
