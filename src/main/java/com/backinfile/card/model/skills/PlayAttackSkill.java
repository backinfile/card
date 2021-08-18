package com.backinfile.card.model.skills;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.TargetInfo.TargetType;
import com.backinfile.card.model.actions.AttackAction;

// 释放储备skill, 卡牌本体弃置
public class PlayAttackSkill extends Skill {
	public PlayAttackSkill(int number) {
		passive = false;
		targetInfo = new TargetInfo(TargetType.Store);
		targetInfo.number = number;
		targetInfo.optional = false;
	}

	@Override
	public void apply() {
		Card card = (Card) caster;
		board.removeCard(card);
		for (var selectedCard : human.selectedPile) {
			addLast(new AttackAction(human, selectedCard, board.getOpponent(human)));
		}
		human.selectedPile.clear();
	}
}
