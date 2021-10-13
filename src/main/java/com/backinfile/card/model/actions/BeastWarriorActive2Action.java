package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.cards.StoreCard;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.card.server.humanOper.SelectOptionOper;

public class BeastWarriorActive2Action extends WaitAction {

	public BeastWarriorActive2Action(Human human) {
		this.human = human;
	}

	@Override
	public void init() {
		List<IOption> options = new ArrayList<>();
		for (var selectNumber : Arrays.asList(2, 4)) {
			for (var clazz : Arrays.asList(ActionCard.class, StoreCard.class)) {
				if (human.markPile.filter(clazz).size() >= selectNumber) {
					options.add(new DiscardOption(selectNumber, clazz));
				}
			}
		}
		options.add(new CancelOption());

		var humanOper = new SelectOptionOper(actionString.tips[3],
				options.stream().map(IOption::getText).collect(Collectors.toList()));
		humanOper.setDetachCallback(() -> {
			options.get(humanOper.getSelectedIndex()).excute();
		});
		human.addHumanOper(humanOper);
	}

	private static interface IOption {
		String getText();

		void excute();
	}

	private class DiscardOption implements IOption {
		private int selectNumber;
		private Class<? extends Card> clazz;

		public DiscardOption(int selectNumber, Class<? extends Card> clazz) {
			this.selectNumber = selectNumber;
			this.clazz = clazz;
		}

		@Override
		public String getText() {
			return selectNumber + actionString.tips[clazz == ActionCard.class ? 1 : 2];
		}

		@Override
		public void excute() {
			var humanOper = new SelectCardOper(human.markPile.filter(clazz), actionString.tips[3], selectNumber);
			humanOper.setDetachCallback(() -> {
				if (clazz == ActionCard.class) {
					addFirst(new GainAPAction(human, selectNumber));
					addFirst(new UnsealAction(human, selectNumber / 2));
				} else {
					for (int i = 0; i < selectNumber / 2; i++) {
						addFirst(new FlipStoreAction(human, human.getOpponent()));
					}
				}
				addFirst(new DiscardCardAction(human, humanOper.getSelectedPile()));
				setDone();
			});
			human.addHumanOper(humanOper);

		}
	}

	private class CancelOption implements IOption {
		@Override
		public String getText() {
			return actionString.tips[0];
		}

		@Override
		public void excute() {
			addFirst(new GainAPAction(human, 1));
			setDone();
		}

	}
}
