package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.model.cards.MonsterCard.Cat;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SelectToHarassAction extends WaitAction {
	private CardPile selectFrom = new CardPile();

	public SelectToHarassAction(Human human) {
		this.human = human;
	}

	public SelectToHarassAction(Human human, CardPile selectFrom) {
		this.human = human;
		this.selectFrom.addAll(selectFrom);
	}

	@Override
	public void init() {
		var sealCards = human.getOpponent().getAllSealCard(true);

		// 优先从selectFrom中选择，如果没有设置，则选择储备
		CardPile pile = new CardPile();
		if (!selectFrom.isEmpty()) {
			pile.addAll(selectFrom);
		} else {
			if (!sealCards.isEmpty()) {
				pile.addAll(human.getAllStoreCards(true, true, false, false, false));
			} else {
				pile.addAll(human.getAllStoreCards(true, true, false, false, false).filter(c -> c instanceof Cat));
			}
		}

		var humanOper = new SelectCardOper(pile, actionString.tips[0], 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onSelectStore(humanOper.getSelectedPile().getAny());
			} else {
				setDone();
			}
		});
		human.addHumanOper(humanOper);
	}

	// 选择一个储备进行骚扰
	private void onSelectStore(Card store) {
		// 有技能可以触发
		var skill = store.getSkill(s -> s.testTriggerable(SkillTrigger.ReplaceHarass, SkillAura.AnyWhere));
		if (skill != null) {
			board.applySkill(skill);
			setDone();
			return;
		}

		addFirst(new HarassAction(human, store));
		setDone();
	}
}
