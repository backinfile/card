package com.backinfile.card.model.actions;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class SelectToAttackAction extends WaitAction {
	private Human targetHuman;
	private boolean withAttackEffect = false;

	public SelectToAttackAction(Human human) {
		this.human = human;
		this.targetHuman = human.getOpponent();
	}

	public SelectToAttackAction(Human human, boolean withAttackEffect) {
		this.human = human;
		this.targetHuman = human.getOpponent();
		this.withAttackEffect = withAttackEffect;
	}

	@Override
	public void init() {
		CardPile selectFrom = human.getAllStoreCards(true, true, false, false, false);
		if (withAttackEffect) {
			CardPile effectSelectFrom = new CardPile();
			for (var card : selectFrom) {
				var skill = card.getSkill(s -> s.trigger == SkillTrigger.ReplaceRelease);
				if (skill != null && skill.testTriggerable(SkillTrigger.ReplaceRelease, SkillAura.AnyWhere)) {
					effectSelectFrom.add(card);
				}
			}
			if (!effectSelectFrom.isEmpty()) {
				selectFrom.clear();
				selectFrom.addAll(effectSelectFrom);
			}
		}

		var humanOper = new SelectCardOper(selectFrom, Utils.format(actionString.tips[withAttackEffect ? 1 : 0], 1), 0,
				1);
		humanOper.setDetachCallback(() -> {
			for (var card : humanOper.getSelectedPile().reverse()) {
				board.removeCard(card);
				addFirst(new AttackAction(human, card, targetHuman, withAttackEffect));
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}
}
