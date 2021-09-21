package com.backinfile.card.server.local;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.TargetInfo;

// 玩家操作暂存
class HumanOperCache {
	public HumanOperType type;

	public Human human;
	public TargetInfo targetInfo;
	public CardPile selected = new CardPile();

	public List<Skill> skills = new ArrayList<>();

	public HumanOperCache(Human human) {
		this.human = human;
		this.targetInfo = human.targetInfo;
		type = HumanOperType.Target;
	}

	public HumanOperCache(Human human, List<Skill> skills) {
		this.human = human;
		this.skills.addAll(skills);
		type = HumanOperType.Skill;
	}

	public enum HumanOperType {
		Target, Skill,
	}
}