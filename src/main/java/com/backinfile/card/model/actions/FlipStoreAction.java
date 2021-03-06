package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.Chap2HeroCard.HeartFire;
import com.backinfile.card.model.cards.Chap2HeroCard.RedPhoenix;
import com.backinfile.card.model.cards.MonsterCard.Dear;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class FlipStoreAction extends WaitAction {
	private Human targetHuman;

	public FlipStoreAction(Human human) {
		this.human = human;
		this.targetHuman = human.getOpponent();
	}

	@Override
	public void init() {
		var stores = targetHuman.getAllStoreInSlot(false, false, false, true);
		if (stores.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(stores, actionString.tip, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelectFlipCard(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelectFlipCard(Card flipCard) {
		var dears = new CardPile();
		for (var slot : human.getOpponent().cardSlotMap.values()) {
			if (slot.ready) {
				dears.addAll(slot.getCardInPile(ESlotType.Store, ESlotType.Plan).filter(c -> c instanceof Dear));
			}
		}
		dears.remove(flipCard);

		var humanOper = new SelectCardOper(dears, Utils.format(actionString.tips[0], flipCard.cardString.name), 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onReplaceFlipCard(humanOper.getSelectedPile().getAny());

				// 焚心之火技能
				if (human.getOpponent().isHero(HeartFire.class)) {
					addLast(new HeartFirePassive1Action(human.getOpponent()));
				}
			} else {
				onReplaceFlipCard(flipCard);
			}
		});
		human.getOpponent().addHumanOper(humanOper);
	}

	private void onReplaceFlipCard(Card flipCard) {

		if (human.isHero(RedPhoenix.class)) {
			addFirst(new RedPhoenixSealOpponentAction(human));
		}

		addFirst(new RefreshSlotAction());

		var slot = targetHuman.getCardSlotByCard(flipCard);
		board.removeCard(flipCard);
		slot.getPile(ESlotType.Seal).add(flipCard);
		board.modifyCard(flipCard);
	}
}
