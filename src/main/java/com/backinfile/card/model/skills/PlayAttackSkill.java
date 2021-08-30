package com.backinfile.card.model.skills;

import com.backinfile.card.model.Skill;

// 释放储备skill, 卡牌本体弃置
public class PlayAttackSkill extends Skill {
	public PlayAttackSkill(int number) {
		targetInfo = new TargetInfo(TargetType.Store, number);
	}

	@Override
	public void apply() {
		// TODO
//		Card card = (Card) caster;
//		board.removeCard(card);
//		for (var selectedCard : human.targetInfo.getSelected()) {
//			addLast(new AttackAction(human, selectedCard, board.getOpponent(human)));
//		}
//		human.clearTargetInfo();
	}
}
