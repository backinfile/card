package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.DragonRideAction;
import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard.MonsterSkillType;

public class DragonRideSkill extends Skill {
	public DragonRideSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Slot, 1, 1);
	}

	@Override
	public boolean triggerable() {
		CardSlot slot = human.getCardSlotByCard(card);
		if (slot.getPile(ESlotType.Store).contains(card)) {
			if (slot.getPile(ESlotType.Ride).isEmpty()) {
				return false;
			}
			if (!human.handPile.filter(c -> c instanceof ActionCard).isEmpty()) {
				CardPile attackMonsters = human.handPile.filter(
						c -> c instanceof MonsterCard && ((MonsterCard) c).isMonsterType(MonsterSkillType.Attack));
				if (!attackMonsters.isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void apply() {
		addLast(new DragonRideAction(human));
	}

}
