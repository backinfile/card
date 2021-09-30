package com.backinfile.card.model;

import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.gen.GameMessageHandler.DCardPileInfo;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.view.group.PileView.HumanPosition;

public class CardInfo {
	public final DCardInfo info;
	public final DCardPileInfo pileInfo;

	public CardInfo(DCardInfo cardInfo) {
		this.info = cardInfo;
		this.pileInfo = cardInfo.getPileInfo();
	}

	/**
	 * 获取所处的位置
	 */
	public HumanPosition getPilePosition() {
		if (LocalData.instance().token.equals(info.getPileInfo().getPlayerToken())) {
			return HumanPosition.Self;
		}
		return HumanPosition.Opponent;
	}

	/**
	 * 获取卡牌归属者
	 */
	public HumanPosition getOwnerPosition() {
		if (LocalData.instance().token.equals(info.getOwnerToken())) {
			return HumanPosition.Self;
		}
		return HumanPosition.Opponent;
	}

	public long getId() {
		return info.getId();
	}

}
