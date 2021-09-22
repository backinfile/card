package com.backinfile.card.server.humanOper;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.model.Human;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;

public class SelectCommonPileCardHumanOper implements IHumanOper {
	private Human human;
	private ECardPileType cardPileType;
	private int minNumber;
	private int maxNumber;

	public SelectCommonPileCardHumanOper(Human human, ECardPileType cardPileType, int minNumber, int maxNumber) {
		this.human = human;
		this.cardPileType = cardPileType;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
	}

	@Override
	public void onAttach() {
	}

	@Override
	public boolean onMessage(String token, DSyncBase msg) {
		return false;
	}

	@Override
	public boolean isDone() {
		return false;
	}

}
