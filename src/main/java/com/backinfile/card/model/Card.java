package com.backinfile.card.model;


public abstract class Card extends SkillCaster {
	public final long id;
	public final String name;
	public CardType mainType = CardType.STORE;
	public CardSubType subType = CardSubType.NONE;
	public String mainImage;
	public String backImage;

	public long oriHumanId = 0; // 最初归属于谁

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

	public Card(int id, String name) {
		this.id = id;
		this.name = name;
	}

}
