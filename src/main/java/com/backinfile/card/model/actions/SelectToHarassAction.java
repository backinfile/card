package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.model.cards.chapter2.MonsterCard.Cat;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SelectToHarassAction extends WaitAction {
	public SelectToHarassAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		var sealCards = human.getOpponent().getAllSealCard(true);

		if (!sealCards.isEmpty()) {
			var humanOper = new SelectCardOper(human.getAllStoreCards(true, true, false, false, false),
					actionString.tips[0], 1);
			humanOper.setDetachCallback(() -> {
				if (!humanOper.getSelectedPile().isEmpty()) {
					onSelectStore(humanOper.getSelectedPile().getAny());
				} else {
					setDone();
				}
			});
			human.addHumanOper(humanOper);
		} else {
			var humanOper = new SelectCardOper(
					human.getAllStoreCards(true, true, false, false, false).filter(c -> c instanceof Cat),
					actionString.tips[0], 1);
			humanOper.setDetachCallback(() -> {
				if (!humanOper.getSelectedPile().isEmpty()) {
					onSelectStore(humanOper.getSelectedPile().getAny());
				} else {
					setDone();
				}
			});
			human.addHumanOper(humanOper);
		}
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
