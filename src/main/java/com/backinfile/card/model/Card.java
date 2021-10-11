package com.backinfile.card.model;

import com.backinfile.card.manager.LocalString;
import com.backinfile.card.manager.LocalString.LocalCardString;
import com.backinfile.support.IdAllot;

/**
 * 卡牌基类 <br/>
 */
public abstract class Card extends SkillCaster {
	public long id;
	public LocalCardString cardString;
	public CardType mainType = CardType.NONE;
	public String oriHumanToken; // 最初归属于谁
	public int chapter = 1; // 卡牌所属章节

	public static enum CardType {
		HERO, // 角色卡
		STORE, // 储备卡
		ACTION, // 行动卡
		TOOL, // 辅助用卡，并不实际存在
		NONE
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

	// 并不完整，先注掉，以后有用再写
//	public Card copy() {
//		try {
//			// 复制的卡用新的id
//			Card copy = getClass().getConstructor().newInstance();
//			copy.cardString = this.cardString;
//			copy.mainType = this.mainType;
//			copy.subType = this.subType;
//			copy.oriHumanToken = this.oriHumanToken;
//			return copy;
//		} catch (Exception e) {
//			Log.game.error("error in copy card", e);
//			throw new SysException("error in copy card");
//		}
//	}

}
