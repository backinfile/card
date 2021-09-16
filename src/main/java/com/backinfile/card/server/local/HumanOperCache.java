package com.backinfile.card.server.local;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;

// 玩家操作暂存
class HumanOperCache {
	public Human human;
	public TargetInfo targetInfo;
	public CardPile selected = new CardPile();

	public HumanOperCache(Human human) {
		this.human = human;
		this.targetInfo = human.targetInfo;
	}
}