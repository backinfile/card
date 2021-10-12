package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.AttackAction;
import com.backinfile.card.model.actions.BirdRideOutTurnAction;
import com.backinfile.card.model.cards.MonsterCard.Bird;

public class BirdRideOutTurnSkill extends Skill {
	public BirdRideOutTurnSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Defend, SkillAura.Hero, 0);
	}

	@Override
	public boolean triggerable() {
		var bird = getRidedBird();
		if (bird == null) {
			return false;
		}

		if (!bird.getSkill(BirdRideSkill.class).testTriggerable(SkillTrigger.Active, SkillAura.AnyWhere, true)) {
			return false;
		}

		return true;
	}

	@Override
	public void apply() {
		AttackAction attackAction = param.get("attackAction");
		addFirst(new BirdRideOutTurnAction(human, getRidedBird(), attackAction));
	}

	private Card getRidedBird() {
		var birdStores = human.getAllStoreInSlot(true, true, false, false).filter(Bird.class);
		for (var bird : birdStores) {
			var slot = human.getCardSlotByCard(bird);
			if (!slot.getPile(ESlotType.Ride).isEmpty()) {
				return bird;
			}
		}
		return null;
	}
}
