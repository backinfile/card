package com.backinfile.card.model.cards;

import com.backinfile.card.model.Card;
import com.backinfile.card.model.LocalString.LocalCardString;

public abstract class HeroCard extends Card {

	public HeroCard(LocalCardString cardString) {
		super(cardString);

		mainType = CardType.HERO;

//		addSkill(new StoreSelfSkill()); TODO // 基础技能：抽1 过牌2 回合结束
	}

	public HeroCard() {
		this(null);
	}
}
