package com.backinfile.card.model.actions;

import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.card.server.humanOper.SelectEmptySlotOper;
import com.backinfile.support.Log;

public class AttackAction extends WaitAction {
	private Human targetHuman;
	private boolean withAttackEffect = false;
	private AttackResult attackResult = AttackResult.None;

	public AttackAction(Human human, Card card, Human targetHuman) {
		this.human = human;
		this.card = card;
		this.targetHuman = targetHuman;
	}

	public AttackAction(Human human, Card card, Human targetHuman, boolean withAttackEffect) {
		this.human = human;
		this.card = card;
		this.targetHuman = targetHuman;
		this.withAttackEffect = withAttackEffect;
	}

	public static enum AttackResult {
		None, Occupy, Break,
	}

	@Override
	public void init() {
		if (withAttackEffect) { // 有特效释放
			var skill = card.getSkill(s -> {
				if (s.trigger == SkillTrigger.ReplaceRelease) {
					s.setContext(board, human, card);
					if (s.triggerCostAP <= human.actionPoint) {
						if (s.triggerable()) {
							return true;
						}
					}
				}
				return false;
			});
			if (skill != null) {
				board.applySkill(skill);
				setDone();
				return;
			}
		}

		var emptySlots = targetHuman.getEmptySlots(true);
		if (!emptySlots.isEmpty()) {
			// 有空位，直接侵占上去
			var humanOper = new SelectEmptySlotOper(emptySlots.stream().map(s -> s.index).collect(Collectors.toList()),
					ETargetSlotAimType.Occupy, targetHuman != human, actionString.tips[0]);
			humanOper.setDetachCallback(() -> {
				onOccupy(humanOper.getSelected());
			});
			human.addHumanOper(humanOper);
			return;
		}
		// 没有空位，选择一项击破
		var toBreak = targetHuman.getAllStoreInSlot(false, false, true, true);
		if (toBreak.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(toBreak, actionString.tips[1], 1);
		humanOper.setDetachCallback(() -> {
			onBreak(humanOper.getSelectedPile().getAny());
		});
		targetHuman.addHumanOper(humanOper);
	}

	private void onOccupy(int index) {
		this.attackResult = AttackResult.Occupy;
		removeStore(card);
		var cardSlot = targetHuman.cardSlotMap.get(index);
		cardSlot.getPile(ESlotType.Seal).add(card);
		board.modifyCard(card);
		setDone();
		Log.game.info("侵占成功");
	}

	private void onBreak(Card breakCard) {
		this.attackResult = AttackResult.Break;
		CardPile discards = new CardPile();
		discards.addAll(targetHuman.getCardSlotByCard(breakCard).getAllCards());
		discards.add(card);
		addFirst(new DiscardCardAction(targetHuman, discards));
		addFirst(new DrawCardAction(human, 1));
		setDone();
		Log.game.info("击破");
	}

	public AttackResult getAttackResult() {
		return attackResult;
	}

	private void removeStore(Card store) {
		CardSlot slot = human.getCardSlotByCard(store);
		if (slot != null) {
			CardPile allCards = slot.getAllCards();
			allCards.remove(store);
			addFirst(new DiscardCardAction(targetHuman, allCards));
		} else {
			board.removeCard(store);
		}
	}
}
