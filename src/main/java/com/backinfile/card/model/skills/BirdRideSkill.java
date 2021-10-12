package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToMarkStoreAction;
import com.backinfile.card.model.cards.Chap2HeroCard.WindSeeker;
import com.backinfile.card.model.cards.MonsterCard;
import com.backinfile.card.model.cards.MonsterCard.MonsterSkillType;
import com.backinfile.card.model.cards.StoreCard;

public class BirdRideSkill extends Skill {
	public BirdRideSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Slot, 0, 1);
	}

	@Override
	public boolean triggerable() {
		CardSlot slot = human.getCardSlotByCard(card);
		if (slot == null) {
			return false;
		}
		if (!slot.getPile(ESlotType.Store).contains(card)) {
			return false;
		}
		if (slot.getPile(ESlotType.Ride).isEmpty()) {
			return false;
		}

		// 追风者技能
		if (human.isHero(WindSeeker.class)) {
			return !human.handPile.filter(StoreCard.class).isEmpty();
		}

		return !human.handPile.filter(c -> {
			if (c instanceof MonsterCard) {
				return ((MonsterCard) c).isMonsterType(MonsterSkillType.Harass);
			}
			return false;
		}).isEmpty();
	}

	@Override
	public void apply() {
		var selectFrom = human.handPile.filter(c -> {
			if (c instanceof MonsterCard) {
				return ((MonsterCard) c).isMonsterType(MonsterSkillType.Harass);
			}
			return false;
		});

		// 追风者技能
		if (human.isHero(WindSeeker.class)) {
			selectFrom = human.handPile.filter(StoreCard.class);
			human.board.gameLog(human, EGameLogType.Action, skillString.tips[0]);
		}

		addLast(new SelectToMarkStoreAction(human, selectFrom));
	}

}
