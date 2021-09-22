package com.backinfile.card.server.humanOper;

import com.backinfile.card.gen.GameMessageHandler.CSSelectCard;
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
	private String cancelTip = "";

	private CardPile selectedPile = new CardPile(); // 已选择的牌

	public SelectCardOper(CardPile targetCardPile, String tip, String cancelTip, int minNumber, int maxNumber) {
		this.targetCardPile.addAll(targetCardPile);
		this.tip = tip;
		this.cancelTip = cancelTip;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	public SelectCardOper(CardPile targetCardPile, String tip, int number) {
		this(targetCardPile, tip, "", number, number);
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
		// 选择数目足够，结束
		if (selectedPile.size() >= maxNumber) {
			setDone();
			return;
		}
		// 计算需要进行选择的牌
		var toSelect = new CardPile(targetCardPile);
		toSelect.removeAll(selectedPile);
		if (toSelect.isEmpty()) { // 没有可选的了，结束
			setDone();
			return;
		}

		// 推送消息
		SCSelectCards msg = new SCSelectCards();
		msg.setCardIdsList(toSelect.getCardIdList());
		msg.setTip(tip);
		msg.setCancelTip(cancelTip);
		msg.setCancel(selectedPile.size() >= minNumber);
		human.sendMessage(msg);
	}

	@Override
	public void onMessage(CSSelectCard data) {
		long cardId = data.getCardId();
		if (cardId <= 0 && selectedPile.size() >= minNumber) {
			// 取消选择
			setDone();
		} else if (targetCardPile.contains(cardId)) {
			var card = targetCardPile.getCard(cardId);
			selectedPile.add(card);
			checkSelectOver();
		}
	}

	public CardPile getSelectedPile() {
		return selectedPile;
	}

}
