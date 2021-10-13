package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.Chap2HeroCard.HeartFire;
import com.backinfile.card.model.cards.MonsterCard.Dear;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class BreakAction extends WaitAction {
	private Card breakCard;

	public BreakAction(Human human, Card card, Card breakCard) {
		this.human = human;
		this.card = card;
		this.breakCard = breakCard;
	}

	@Override
	public void init() {
		var dears = new CardPile();
		for (var slot : human.getOpponent().cardSlotMap.values()) {
			if (slot.ready) {
				dears.addAll(slot.getCardInPile(ESlotType.Store, ESlotType.Plan).filter(c -> c instanceof Dear));
			}
		}
		dears.remove(breakCard);

		var humanOper = new SelectCardOper(dears, Utils.format(actionString.tip, breakCard.cardString.name), 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onBreak(humanOper.getSelectedPile().getAny());

				// 焚心之火技能
				if (human.getOpponent().isHero(HeartFire.class)) {
					addLast(new HeartFirePassive1Action(human.getOpponent()));
				}
			} else {
				onBreak(breakCard);
			}
		});
		human.getOpponent().addHumanOper(humanOper);
	}

	private void onBreak(Card selectedCard) {
		addFirst(new BreakFinishAction(human));
		addFirst(new DiscardCardAction(human, card, selectedCard));
		setDone();
	}
}
