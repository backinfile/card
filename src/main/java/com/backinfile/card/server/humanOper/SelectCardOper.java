package com.backinfile.card.server.humanOper;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCards;
import com.backinfile.card.gen.GameMessageHandler.DCombination;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.model.CardPile;
import com.backinfile.support.Utils;

/**
 * 从 {@code #targetCardPile} 中选择n张不重复的牌
 */
public class SelectCardOper extends HumanOper {
	private CardPile targetCardPile = new CardPile();
	private int minNumber;
	private int maxNumber;
	private String tip = "";
	private List<DCombination> combinations = new ArrayList<>();

	private CardPile selectedPile = new CardPile(); // 已选择的牌

	public SelectCardOper(CardPile targetCardPile, String tip, int minNumber, int maxNumber,
			List<DCombination> combinations) {
		this.targetCardPile.addAll(targetCardPile);
		this.tip = tip;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.combinations.addAll(combinations);

		if (!combinations.isEmpty()) {
			this.targetCardPile = this.targetCardPile
					.filter(c -> combinations.stream().anyMatch(com -> com.getIdListList().contains(c.id)));
		}
	}

	public SelectCardOper(CardPile targetCardPile, String tip, int minNumber, int maxNumber) {
		this(targetCardPile, tip, minNumber, maxNumber, new ArrayList<>());
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
		if (!combinations.isEmpty()) {
			var idList = combinations.get(Utils.nextInt(combinations.size())).getIdListList();
			selectedPile.addAll(targetCardPile.filter(c -> idList.contains(c.id)));
		} else {
			selectedPile.addAll(targetCardPile.pollRandom(maxNumber));
		}
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
		msg.setCombinationsList(combinations);
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
