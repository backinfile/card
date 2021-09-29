package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.model.actions.AttackAction.AttackResult;
import com.backinfile.card.model.cards.StoreCard;
import com.backinfile.card.model.cards.chapter2.MonsterCard.Cat;
import com.backinfile.card.model.cards.chapter2.Recall;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Log;

public class RecallAction extends WaitAction {
	private AttackAction attackAction;

	public RecallAction(Human human, AttackAction attackAction) {
		this.human = human;
		this.attackAction = attackAction;
	}

	@Override
	public void init() {
		var recallActionCards = human.handPile.filter(c -> c instanceof Recall);
		var humanOper = new SelectCardOper(recallActionCards, actionString.tips[0], 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				this.card = humanOper.getSelectedPile().getAny();
				onSelectRecallCard();
			} else {
				addFirst(attackAction);
			}
			setDone();
		});
		human.addHumanOper(humanOper);
	}

	private void onSelectRecallCard() {
		CardPile recallFrom = new CardPile();
		// 储备
		recallFrom.addAll(human.getAllStoreCards(true, true, false, false, false));
		// 骚扰
		for (var slot : human.getOpponent().cardSlotMap.values()) {
			recallFrom.addAll(slot.getPile(ESlotType.Harass));
		}
		// 对手弃牌堆中
		recallFrom.addAll(human.getOpponent().drawPile
				.filter(c -> c instanceof Cat && c.oriHumanToken.equals(human.token)));

		if (recallFrom.isEmpty()) {
			addFirst(attackAction);
			setDone();
			return;
		}

		var humanOper = new SelectCardOper(recallFrom, actionString.tip, 1);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getSelectedPile().isEmpty()) {
				addFirst(attackAction);
				setDone();
				return;
			}
			onSelectRecallStore(humanOper.getSelectedPile().getAny());
		});
		human.addHumanOper(humanOper);
	}

	private void onSelectRecallStore(Card recallCard) {
		// 触发recall技能
		for (var skill : recallCard.getSkillList()) {
			if (skill.testTriggerable(SkillTrigger.Recall, SkillAura.AnyWhere)) {
				board.applySkill(skill);
			}
		}

		// 视为对手击破
		attackAction.setAttackResult(AttackResult.Break);
		addFirst(new BreakFinishAction(human.getOpponent()));
		addFirst(new ArrangePileAction(human));
		addFirst(new DiscardCardAction(human, card, recallCard, attackAction.card));

		Log.game.info("AttackAction被RecallAction终止");
		setDone();
	}
}
