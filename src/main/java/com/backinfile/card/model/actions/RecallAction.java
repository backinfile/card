package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.model.cards.StoreCard;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Log;

public class RecallAction extends WaitAction {
	private AttackAction attackAction;

	public RecallAction(Human human, Card card, AttackAction attackAction) {
		this.human = human;
		this.card = card;
		this.attackAction = attackAction;
	}

	@Override
	public void init() {
		CardPile recallFrom = new CardPile();
		// 储备
		recallFrom.addAll(human.getAllStoreCards(true, true, false, false, false));
		// 骚扰
		for (var slot : human.getOpponent().cardSlotMap.values()) {
			recallFrom.addAll(slot.getPile(ESlotType.Harass));
		}
		// 对手弃牌堆中
		recallFrom.addAll(human.getOpponent().discardPile
				.filter(c -> c instanceof StoreCard && c.oriHumanToken.equals(human.token)));

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
			onRecall(humanOper.getSelectedPile().getAny());
		});
		human.addHumanOper(humanOper);
	}

	private void onRecall(Card recallCard) {
		// 触发recall技能
		var recallSkill = recallCard.getSkill(skill -> skill.testTriggerable(SkillTrigger.Recall, SkillAura.AnyWhere));
		if (recallSkill != null) {
			board.applySkill(recallSkill);
		}

		// 视为对手击破
		addFirst(new BreakFinishAction(human.getOpponent()));
		addFirst(new DiscardCardAction(human, card, recallCard, attackAction.card));

		Log.game.info("AttackAction被RecallAction终止");
		setDone();
	}
}
