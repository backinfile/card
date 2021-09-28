package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillDuration;
import com.backinfile.card.model.skills.ActAsStoreSkill;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SelectToMarkStoreAction extends WaitAction {
	private CardPile cardPile = new CardPile();

	public SelectToMarkStoreAction(Human human, CardPile cardPile) {
		this.human = human;
		this.cardPile.addAll(cardPile);
	}

	@Override
	public void init() {
		cardPile = cardPile.filter(c -> c.getSkill(ActAsStoreSkill.class) == null);
		if (cardPile.isEmpty()) {
			setDone();
			return;
		}

		var humanOper = new SelectCardOper(cardPile, actionString.tip, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelect(humanOper.getSelectedPile().getAny());
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelect(Card card) {
		board.addSkillInCombat(human, card, new ActAsStoreSkill(SkillDuration.OwnerStartTurn, SkillAura.Hand));
	}
}
