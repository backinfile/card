package com.backinfile.card.model.actions;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.server.humanOper.SelectCardOper;

public class SelectToHarassAction extends WaitAction {
	public SelectToHarassAction(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
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
	}

	// 选择一个储备进行骚扰
	private void onSelectStore(Card store) {
		// 有技能可以触发
		var skill = store.getSkill(s -> {
			if (s.trigger == SkillTrigger.ReplaceHarass) {
				if (s.triggerCostAP <= human.actionPoint) {
					if (s.triggerable()) {
						return true;
					}
				}
			}
			return false;
		});
		if (skill != null) {
			board.applySkill(skill);
			setDone();
			return;
		}

		addFirst(new HarassAction(human, store));
		setDone();
	}
}
