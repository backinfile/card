package com.backinfile.card.model.skills;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.BeastTalkerActive2Action;
import com.backinfile.card.model.cards.Chap2ActionCard;
import com.backinfile.card.model.cards.MonsterCard;

public class BeastTalkerActive2Skill extends Skill {
	public BeastTalkerActive2Skill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 1);
	}

	@Override
	public boolean triggerable() {
		if (!human.getAllStoreCards(false, false, false, false, false).isEmpty()) {
			return false;
		}
		if (getViewableActionCard().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		addLast(new BeastTalkerActive2Action(human, getViewableActionCard()));
	}

	public CardPile getViewableActionCard() {
		CardPile pile = new CardPile();
		for (var card : human.handPile) {
			if (card instanceof Chap2ActionCard) {
				var type = ((Chap2ActionCard) card).getMonsterSkillType();
				if (!human.discardPile.filter(c -> c instanceof MonsterCard && ((MonsterCard) c).isMonsterType(type))
						.isEmpty()) {
					pile.add(card);
				}
			}
		}
		return pile;
	}
}
