package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.Chap2HeroCard.BlackTurtle;
import com.backinfile.card.model.cards.Chap2HeroCard.HeartFire;
import com.backinfile.card.model.cards.MonsterCard.Dear;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.support.Utils;

public class RemoveOpponentStoreOrMarkAction extends WaitAction {
	private boolean returnToHand;

	public RemoveOpponentStoreOrMarkAction(Human human, boolean returnToHand) {
		this.human = human;
		this.returnToHand = returnToHand;
	}

	public RemoveOpponentStoreOrMarkAction(Human human) {
		this(human, false);
	}

	@Override
	public void init() {
		toSelectTarget();
	}

	private void toSelectTarget() {
		CardPile selectFrom = new CardPile();
		selectFrom.addAll(human.getOpponent().markPile);
		selectFrom.addAll(human.getOpponent().getAllStoreCards(false, false, false, true, true));

		if (selectFrom.isEmpty()) {
			setDone();
			return;
		}

		var humanOper = new SelectCardOper(selectFrom, actionString.tips[returnToHand ? 1 : 0], 0, 1);
		humanOper.setDetachCallback(() -> {
			if (humanOper.getSelectedPile().isEmpty()) {
				setDone();
				return;
			}
			var card = humanOper.getSelectedPile().getAny();
			onDearChangeTarget(card);
		});
		human.addHumanOper(humanOper);
	}

	private void onDearChangeTarget(Card targetCard) {
		var opponent = human.getOpponent();
		var dears = human.getOpponent().getAllStoreCards(true, true, false, false, false).filter(Dear.class);
		dears.remove(targetCard);

		var humanOper = new SelectCardOper(dears,
				Utils.format(actionString.tips[returnToHand ? 3 : 2], targetCard.cardString.name), 0, 1);
		humanOper.setDetachCallback(() -> {
			if (!humanOper.getSelectedPile().isEmpty()) {
				onFinalSelect(humanOper.getSelectedPile().getAny());

				// 焚心之火技能
				if (opponent.isHero(HeartFire.class)) {
					addLast(new HeartFirePassive1Action(opponent));
				}
			} else {
				onFinalSelect(targetCard);
			}
		});
		opponent.addHumanOper(humanOper);
	}

	private void onFinalSelect(Card card) {
		// 玄武使者技能
		var opponent = human.getOpponent();
		if (opponent.isHero(BlackTurtle.class) && !opponent.markPile.isEmpty()) {
			board.gameLog(opponent, EGameLogType.Skill, actionString.tips[4]);
			var humanOper = new SelectCardOper(opponent.markPile,
					Utils.format(actionString.tips[5], card.cardString.name), 0, 1);
			humanOper.setDetachCallback(() -> {
				if (humanOper.getSelectedPile().isEmpty()) {
					toRemove(card);
				} else {
					addFirst(new DiscardCardAction(human, humanOper.getSelectedPile().getAny()));
					setDone();
				}
			});
			opponent.addHumanOper(humanOper);
		} else {
			toRemove(card);
		}
	}

	private void toRemove(Card card) {
		board.removeCard(card);

		addFirst(new RefreshSlotAction());
		if (returnToHand) {
			var human = board.getHuman(card.oriHumanToken);
			human.handPile.add(card);
			board.modifyCard(human.handPile);
		} else {
			addFirst(new DiscardCardAction(human, card));
		}
		setDone();
	}
}
