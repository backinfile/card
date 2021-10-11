package com.backinfile.card.server.humanOper;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCards;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.model.CardPile;

/**
 * 从 {@code #targetCardPile} 中选择n张不重复的牌
 */
public class SelectCardOper extends HumanOper {
	private CardPile targetCardPile = new CardPile();
	private int minNumber;
	private int maxNumber;
	private String tip = "";

	private CardPile selectedPile = new CardPile(); // 已选择的牌

	public SelectCardOper(CardPile targetCardPile, String tip, int minNumber, int maxNumber) {
		this.targetCardPile.addAll(targetCardPile);
		this.tip = tip;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	public SelectCardOper(CardPile targetCardPile, String tip, int number) {
		this(targetCardPile, tip, number, number);
	}

	@Override
	public void onHumanAttach() {
		checkSelectOver();
	}

	@Override
	public void onAIAttach() {
		selectedPile.addAll(targetCardPile.pollRandom(maxNumber));
		setDone();
	}

	private void checkSelectOver() {
		if (isDone()) {
			return;
		}
		if (targetCardPile.isEmpty()) {
			setDone();
			return;
		}

		minNumber = Math.min(targetCardPile.size(), minNumber);

		// 推送消息
		SCSelectCards msg = new SCSelectCards();
		msg.setCardIdsList(targetCardPile.getCardIdList());
		msg.setTip(tip);
		msg.setMinNumber(minNumber);
		msg.setMaxNumber(maxNumber);
		human.sendMessage(msg);
	}

	@Override
	public void onMessage(CSSelectCards data) {
		for (var cardId : data.getCardIdsList()) {
			selectedPile.add(targetCardPile.getCard(cardId));
		}
		setDone();
	}

	public CardPile getSelectedPile() {
		return selectedPile;
	}

}
