package com.backinfile.card.model;

import java.util.Objects;

public abstract class Card extends SkillCaster {
	public final long id;
	public final String name;
	public CardType mainType = CardType.STORE;
	public CardSubType subType = CardSubType.NONE;

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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Card) {
			return ((Card) obj).id == this.id;
		}
		return false;
	}

}
