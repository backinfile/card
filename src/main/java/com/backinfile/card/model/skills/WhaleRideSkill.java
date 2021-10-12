package com.backinfile.card.model.skills;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.SelectToDiscardHandAction;
import com.backinfile.card.model.actions.TakeSpecCardInHandAction;
import com.backinfile.card.model.actions.UnsealAction;
import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.cards.Chap2ActionCard.Ride;
import com.backinfile.card.model.cards.Chap2HeroCard.SeaTalker;
import com.backinfile.card.model.cards.MonsterCard;
import com.backinfile.card.model.cards.MonsterCard.MonsterSkillType;

public class WhaleRideSkill extends Skill {
	public WhaleRideSkill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Slot, 1, 1);
		marks.add(SkillMark.Unseal);
	}

	// 在储备位上，坐骑状态下有东西才可以使用
	@Override
	public boolean triggerable() {
		CardSlot slot = human.getCardSlotByCard(card);
		if (slot == null) {
			return false;
		}
		if (slot.getPile(ESlotType.Store).contains(card)) {
			if (slot.getPile(ESlotType.Ride).isEmpty()) {
				return false;
			}
			if (!human.handPile.filter(c -> c instanceof ActionCard).isEmpty()) {
				if (!human.getAllUnSealableCard().isEmpty()) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void apply() {
		addLast(new SelectToDiscardHandAction(human, 1, c -> c instanceof ActionCard));
		addLast(new UnsealAction(human, 1));

		if (human.isHero(SeaTalker.class)) {
			addLast(new TakeSpecCardInHandAction(human, this::toSelect, 0, 1));
		}
	}

	private CardPile toSelect() {
		return human.discardPile.filter(c -> {
			if (c instanceof Ride) {
				return true;
			}
			if (c instanceof MonsterCard) {
				if (((MonsterCard) c).isMonsterType(MonsterSkillType.Ride)) {
					return true;
				}
			}
			return false;
		});
	}
}
