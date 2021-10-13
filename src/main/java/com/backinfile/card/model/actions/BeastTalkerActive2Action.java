package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.Chap2ActionCard;
import com.backinfile.card.model.cards.MonsterCard;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class BeastTalkerActive2Action extends WaitAction {
	private CardPile viewable = new CardPile();

	public BeastTalkerActive2Action(Human human, CardPile viewable) {
		this.human = human;
		this.viewable.addAll(viewable);
	}

	@Override
	public void init() {
		var humanOper = new SelectCardOper(viewable, actionString.tips[0], 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onShow(humanOper.getSelectedPile().getAny());
			} else {
				setDone();
			}
		});
		human.addHumanOper(humanOper);
	}

	private void onShow(Card card) {
		board.gameLog(human, EGameLogType.Action, actionString.tips[1], card.cardString.name);
		if (card instanceof Chap2ActionCard) {
			var type = ((Chap2ActionCard) card).getMonsterSkillType();
			var pile = human.discardPile.filter(c -> c instanceof MonsterCard && ((MonsterCard) c).isMonsterType(type));
			var humanOper = new SelectCardOper(pile, actionString.tips[2], 1);
			humanOper.setDetachCallback(() -> {
				if (!humanOper.getSelectedPile().isEmpty()) {
					addFirst(new StoreCardAction(human, humanOper.getSelectedPile().getAny(), true));
				}
				setDone();
			});
			human.addHumanOper(humanOper);
		} else {
			setDone();
		}
	}
}
