package com.backinfile.card.model;

import com.backinfile.card.model.LocalString.LocalCardString;
import com.backinfile.support.IdAllot;
import com.backinfile.support.Log;
import com.backinfile.support.SysException;

public abstract class Card extends SkillCaster {
	public long id;
	public LocalCardString cardString;
	public CardType mainType = CardType.NONE;
	public CardSubType subType = CardSubType.NONE;
	public String oriHumanToken; // 最初归属于谁

	public static enum CardType {
		HERO, // 角色卡
		STORE, // 储备卡
		ACTION, // 行动卡
		NONE
	}

	public static enum CardSubType {
		NONE, // 空
		ACTION_MAGIC, // 第一章魔法卡
		ACTION_ORDER, // 指令
	}

	public Card(LocalCardString cardString) {
		this.id = IdAllot.applyId();
		this.cardString = cardString;
		if (this.cardString == null) {
			this.cardString = LocalString.getCardString(getClass().getSimpleName());
		}
	}

	public Card() {
		this(null);
	}

	public Card copy() {
		try {
			// 复制的卡用新的id
			Card copy = getClass().getConstructor().newInstance();
			copy.cardString = this.cardString;
			copy.mainType = this.mainType;
			copy.subType = this.subType;
			copy.oriHumanToken = this.oriHumanToken;
			onCopy(copy);
			return copy;
		} catch (Exception e) {
			Log.game.error("error in copy card", e);
			throw new SysException("error in copy card");
		}
	}

	public void onCopy(Card copyCard) {
	}

}
