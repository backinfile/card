package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.TriggerOnceAction;
import com.backinfile.card.model.cards.chapter2.MonsterCard.Bee;

public class BeeAttackSkill extends Skill {
	public BeeAttackSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.ReplaceRelease, SkillAura.AnyWhere, 0);
		marks.add(SkillMark.Release);
	}

	@Override
	public void apply() {
		addFirst(new TriggerOnceAction() {
			@Override
			public void run() {
				var stores = human.getAllStoreCards(true, true, false, false, false).filter(c -> c instanceof Bee);
				for (var slot : human.getOpponent().cardSlotMap.values()) {
					stores.addAll(slot.getPile(ESlotType.Harass).filter(c -> c instanceof Bee));
				}
				stores.add(card);
				for (var card : stores.reverse()) {
					board.removeCard(card);
					addFirst(new AttackAction(human, card, human.getOpponent()));
				}
			}
		});
	}
}
