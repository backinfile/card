package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.ArrangePileAction;
import com.backinfile.card.model.actions.DiscardCardAction;
import com.backinfile.card.model.actions.SaveMarkAction;
import com.backinfile.card.model.actions.SelectToAttackAction;
import com.backinfile.card.model.cards.MonsterCard;
import com.backinfile.card.model.cards.Chap2HeroCard.CyanDragon;
import com.backinfile.card.model.cards.MonsterCard.MonsterSkillType;

public class ReleaseAttackStoreSkill extends Skill {
	public ReleaseAttackStoreSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hand, 1);
		marks.add(SkillMark.Release);
	}

	@Override
	public boolean triggerable() {
		var allStoreCards = human.getAllStoreCards(true, true, false, false, false);
		if (allStoreCards
				.filter(c -> c instanceof MonsterCard && ((MonsterCard) c).isMonsterType(MonsterSkillType.Attack))
				.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply() {
		addLast(new DiscardCardAction(human, card));
		addLast(new ArrangePileAction(human));
		addLast(new SelectToAttackAction(human, true));

		if (human.isHero(CyanDragon.class)) {
			addLast(new SaveMarkAction(human, () -> human.handPile));
			board.gameLog(human, EGameLogType.Skill, skillString.tips[0]);
		}
	}
}
