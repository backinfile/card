package com.backinfile.card.model.actions;

import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.Chap2HeroCard.DragonKnight;
import com.backinfile.card.model.cards.MonsterCard.Dragon;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.card.server.humanOper.SelectEmptySlotOper;

public class StoreCardAction extends WaitAction {
	private boolean fast;
	private boolean cancel;
	private int cancelReturnAp;

	public StoreCardAction(Human human, Card card, boolean fast, boolean cancel, int cancelReturnAp) {
		this.human = human;
		this.card = card;
		this.fast = fast;
		this.cancel = cancel;
		this.cancelReturnAp = cancelReturnAp;
	}

	public StoreCardAction(Human human, Card card, boolean fast) {
		this(human, card, fast, false, 0);
	}

	public StoreCardAction(Human human, Card card) {
		this(human, card, false);
	}

	@Override
	public void init() {
		var emptySlots = human.getEmptySlots(false);
		if (!emptySlots.isEmpty()) {
			var humanOper = new SelectEmptySlotOper(emptySlots.stream().map(s -> s.index).collect(Collectors.toList()),
					ETargetSlotAimType.Store, false, actionString.tips[0], true);
			humanOper.setDetachCallback(() -> {
				if (humanOper.getSelected() < 0) {
					onCancel();
				} else {
					selectIndex(humanOper.getSelected());
				}
			});
			human.addHumanOper(humanOper);
		} else {
			CardPile toReplace = new CardPile();
			for (var slot : human.cardSlotMap.values()) {
				toReplace.addAll(slot.getPile(ESlotType.Store));
				toReplace.addAll(slot.getPile(ESlotType.Plan));
			}
			if (toReplace.isEmpty()) {
				setDone();
				return;
			}
			var humanOper = new SelectCardOper(toReplace, actionString.tips[1], cancel ? 0 : 1, 1);
			humanOper.setDetachCallback(() -> {
				if (humanOper.getSelectedPile().isEmpty()) {
					onCancel();
				} else {
					replaceCard(humanOper.getSelectedPile().get(0));
				}
			});
			human.addHumanOper(humanOper);
		}
	}

	private void storeBefore() {
		if (card instanceof Dragon && human.isHero(DragonKnight.class)) {
			addLast(new DragonKnightStoreDragonAction(human));
		}
	}

	private void replaceCard(Card toReplace) {
		storeBefore();

		board.removeCard(card);
		var slot = human.getCardSlotByCard(toReplace);
		slot.remove(toReplace);
		slot.getPile(ESlotType.Store).add(card);
		slot.ready = fast;
		addLast(new DiscardCardAction(human, toReplace));
		board.modifyCard(toReplace, card);
		setDone();
	}

	private void selectIndex(int index) {
		storeBefore();

		board.removeCard(card);
		var slot = human.cardSlotMap.get(index);
		slot.getPile(ESlotType.Store).add(card);
		slot.ready = fast;
		board.modifyCard(card);
		setDone();
	}

	private void onCancel() {
		if (cancelReturnAp > 0) {
			addLast(new GainAPAction(human, cancelReturnAp));
		}
		setDone();
	}

	@Override
	public void pulse() {
	}
}
