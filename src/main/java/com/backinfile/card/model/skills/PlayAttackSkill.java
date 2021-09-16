package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ETargetType;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;

// 释放储备skill, 卡牌本体弃置
public class PlayAttackSkill extends Skill {
	public PlayAttackSkill() {
		targetInfo.setTargetInfo(GameUtils.newTargetInfo(ETargetType.Store, 1, skillString.tip));
	}

	@Override
	public void apply() {
		board.removeCard(card);
		for (var selectedCard : human.targetInfo.getTargetSelected()) {
			addLast(new AttackAction(human, selectedCard, board.getOpponent(human)));
		}
		human.targetInfo.clear();
	}
}
