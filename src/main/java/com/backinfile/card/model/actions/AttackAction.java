package com.backinfile.card.model.actions;

import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.card.server.humanOper.SelectEmptySlotOper;

public class AttackAction extends WaitAction {
	private Human targetHuman;
	private boolean withAttackEffect = false;

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

	@Override
	public void init() {
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
		var toBreak = targetHuman.getAllStoreInSlot(false, true);
		if (toBreak.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(toBreak, actionString.tips[1], 1);
		humanOper.setDetachCallback(() -> {
			onBreak(humanOper.getSelectedPile().getAny());
		});
		human.addHumanOper(humanOper);
	}

	private void onOccupy(int index) {
		board.removeCard(card);
		var cardSlot = targetHuman.cardSlotMap.get(index);
		cardSlot.getPile(ESlotType.Seal).add(card);
		board.modifyCard(card);
		setDone();
	}

	private void onBreak(Card breakCard) {
		addLast(new DiscardCardAction(targetHuman, breakCard));
		addLast(new DiscardCardAction(human, card));
		addLast(new DrawCardAction(human, 1));
		setDone();
	}
}
