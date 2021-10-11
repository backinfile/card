package com.backinfile.card.model.cards;

import java.util.HashSet;
import java.util.Set;

import com.backinfile.card.model.skills.BeeAttackSkill;
import com.backinfile.card.model.skills.BirdHarassSkill;
import com.backinfile.card.model.skills.BirdRideSkill;
import com.backinfile.card.model.skills.CatHarassSkill;
import com.backinfile.card.model.skills.CatRecallSkill;
import com.backinfile.card.model.skills.DearAttackSkill;
import com.backinfile.card.model.skills.DearAutoReleaseSkill;
import com.backinfile.card.model.skills.DearRecallSkill;
import com.backinfile.card.model.skills.DragonAttackSkill;
import com.backinfile.card.model.skills.DragonRideSkill;
import com.backinfile.card.model.skills.WhaleRecallSkill;
import com.backinfile.card.model.skills.WhaleRideSkill;

// 第二章特有的储备卡
public class MonsterCard extends StoreCard {
	protected Set<MonsterSkillType> monsterSkillTypes = new HashSet<>();

	public MonsterCard() {
		this.chapter = 2;
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

	public static class Whale extends MonsterCard {
		public Whale() {
			monsterSkillTypes.add(MonsterSkillType.Recall);
			monsterSkillTypes.add(MonsterSkillType.Ride);
			addSkill(new WhaleRecallSkill());
			addSkill(new WhaleRideSkill());
		}
	}

	public static class Dear extends MonsterCard {
		public Dear() {
			monsterSkillTypes.add(MonsterSkillType.Attack);
			monsterSkillTypes.add(MonsterSkillType.Recall);
			addSkill(new DearAttackSkill());
			addSkill(new DearRecallSkill());
			addSkill(new DearAutoReleaseSkill());
		}
	}

	public static class Bird extends MonsterCard {
		public Bird() {
			monsterSkillTypes.add(MonsterSkillType.Harass);
			monsterSkillTypes.add(MonsterSkillType.Ride);
			addSkill(new BirdHarassSkill());
			addSkill(new BirdRideSkill());
		}
	}

	public static class Dragon extends MonsterCard {
		public Dragon() {
			monsterSkillTypes.add(MonsterSkillType.Attack);
			monsterSkillTypes.add(MonsterSkillType.Ride);

			addSkill(new DragonAttackSkill());
			addSkill(new DragonRideSkill());
		}
	}

	public static class Cat extends MonsterCard {
		public Cat() {
			monsterSkillTypes.add(MonsterSkillType.Harass);
			monsterSkillTypes.add(MonsterSkillType.Recall);
			addSkill(new CatHarassSkill());
			addSkill(new CatRecallSkill());
		}
	}

	public static class Bee extends MonsterCard {
		public Bee() {
			monsterSkillTypes.add(MonsterSkillType.Attack);
			monsterSkillTypes.add(MonsterSkillType.Harass);
			addSkill(new BeeAttackSkill());
		}
	}
}
