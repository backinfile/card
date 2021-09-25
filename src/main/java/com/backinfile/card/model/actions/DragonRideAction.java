package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard.MonsterSkillType;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class DragonRideAction extends WaitAction {

	public DragonRideAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {

		// 先选择行动牌
		var humanOper = new SelectCardOper(human.handPile.getFiltered(c -> c instanceof ActionCard),
				actionString.tips[0], 1);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getSelectedPile().isEmpty()) {
				setDone();
			} else {
				onSelectActionCard(humanOper.getSelectedPile().getAny());
			}
		});
		human.addHumanOper(humanOper);
	}

	private void onSelectActionCard(Card actionCard) {
		CardPile attackMonsters = human.handPile
				.getFiltered(c -> c instanceof MonsterCard && ((MonsterCard) c).isMonsterType(MonsterSkillType.Attack));
		var humanOper = new SelectCardOper(attackMonsters, actionString.tips[1], 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelect(actionCard, humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelect(Card actionCard, Card storeCard) {
		AttackAction attackAction = new AttackAction(human, storeCard, human.getOpponent());
		addFirst(new ArrangePileAction(human));
		addFirst(new GainAPAction(human, () -> {
			switch (attackAction.getAttackResult()) {
			case Break:
				return 1;
			case Occupy:
				return 2;
			default:
				return 0;
			}
		}));
		addFirst(attackAction);
		addFirst(new DiscardCardAction(human, actionCard));
	}
}
