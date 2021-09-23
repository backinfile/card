package com.backinfile.card.model.cards.chapter2;

import java.util.HashSet;
import java.util.Set;

import com.backinfile.card.model.LocalString.LocalCardString;
import com.backinfile.card.model.cards.StoreCard;

// 第二章特有的储备卡
public class MonsterCard extends StoreCard {
	protected Set<MonsterSkillType> monsterSkillTypes = new HashSet<>();

	public MonsterCard() {
		super();
	}

	public MonsterCard(LocalCardString cardString) {
		super(cardString);
	}

	public boolean isMonsterType(MonsterSkillType type) {
		return monsterSkillTypes.contains(type);
	}

	public Set<MonsterSkillType> getMonsterSkillTypes() {
		return monsterSkillTypes;
	}

	public static enum MonsterSkillType {
		Attack, // 攻击
		Ride, // 骑乘
		Recall, // 召回
		Harass, // 骚扰
	}
}
