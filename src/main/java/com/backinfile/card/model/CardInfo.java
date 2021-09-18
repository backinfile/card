package com.backinfile.card.model;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.gen.GameMessageHandler.DCardPileInfo;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.view.group.PileView.PilePosition;

public class CardInfo {
	public final DCardInfo info;
	public final DCardPileInfo pileInfo;

	public CardInfo(DCardInfo cardInfo) {
		this.info = cardInfo;
		this.pileInfo = cardInfo.getPileInfo();
	}

	public PilePosition getPilePosition() {
		if (LocalData.instance().token.equals(info.getPileInfo().getPlayerToken())) {
			return PilePosition.Self;
		}
		return PilePosition.Opponent;
	}

}
