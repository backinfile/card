package com.backinfile.card.model;

import java.util.HashSet;
import java.util.Set;

import com.backinfile.card.gen.GameMessageHandler.DSkillInfo;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.manager.LocalString.LocalSkillString;
import com.backinfile.card.model.cards.Chap2HeroCard.Beekeeper;
import com.backinfile.card.model.cards.MonsterCard.Bee;
import com.backinfile.support.IdAllot;
import com.backinfile.support.Param;

// 技能 主动触发，或在Action中有交互
public abstract class Skill {
	// 自身属性
	public long id;
	protected LocalSkillString skillString;

	// 执行上下文
	public Board board;
	public Human human;
	public Card card;
	public Param param;
	public Set<SkillMark> marks = new HashSet<>();

	// 触发时机控制
	public SkillTrigger trigger = SkillTrigger.Active; // 触发方式
	public SkillAura aura = SkillAura.Slot; // 可触发区域
	public int triggerCostAP = 0; // 使用时需要消耗的行动点
	public int triggerTimesLimit = -1; // 可触发次数，当恰好==0时不能触发, 回合结束时重置
	public int triggerTimesLimitSetValue = -1; // 可触发次数的设置值

	// 移除时机控制
	public SkillDuration duration = SkillDuration.Combat; // 失效方式

	public static enum SkillDuration {
		Fixed, // 本身固有属性，不会被自动清除
		Combat, // 本局对战中一直存在
		OwnerStartTurn, // skill拥有者回合开始清除
		OwnerEndTurn, // skill拥有者回合结束清除
		Use, // 触发后移除
	}

	// 触发方式
	public static enum SkillTrigger {
		Active, // 主动激活
		ActAsStore, // 可作为readyStore
		ReplaceRelease, // 当卡牌本身有特效释放时，替换其技能
		ReplaceHarass, // 当卡牌本身执行骚扰特效时，替换其技能
		Defend, // 被攻击之前触发
		Recall, // 被召回后触发
		HeroRecall, // 当有储备被召回后，英雄触发
		Passive, // 自定义被动触发
	}

	// 生效地点
	public static enum SkillAura {
		AnyWhere, // 任何地方
		Slot, // 储备位上
		Hand, // 手牌
		Hero, // 英雄牌上
	}

	public static enum SkillMark {
		Release, // 含有释放效果
		Unseal, // 含有解封效果
	}

	public Skill() {
		this(null);
	}

	public Skill(LocalSkillString skillString) {
		this.id = IdAllot.applyId();
		this.skillString = skillString;
		if (this.skillString == null) {
			this.skillString = LocalString.getSkillString(getClass().getSimpleName());
		}
	}

	public void setContext(Board board, Human human, Card card) {
		this.board = board;
		this.human = human;
		this.card = card;
	}

	public void setParam(Object... params) {
		this.param = new Param(params);
	}

	public SkillCaster getSkillOwner() {
		if (card != null) {
			return card;
		}
		return human;
	}

	public void setTriggerType(SkillDuration duration, SkillTrigger trigger, SkillAura aura, int cost, int limit) {
		this.duration = duration;
		this.trigger = trigger;
		this.aura = aura;
		this.triggerCostAP = cost;
		this.triggerTimesLimit = limit;
		this.triggerTimesLimitSetValue = limit;
	}

	public void setTriggerType(SkillDuration duration, SkillTrigger trigger, SkillAura aura, int cost) {
		setTriggerType(duration, trigger, aura, cost, -1);
	}

	public boolean testTriggerable(SkillTrigger trigger, SkillAura aura) {
		return testTriggerable(trigger, aura, false);
	}

	public boolean testTriggerable(SkillTrigger trigger, SkillAura aura, boolean noCost) {
		if (this.triggerTimesLimit == 0) {
			return false;
		}
		if (aura == SkillAura.AnyWhere || this.aura == SkillAura.AnyWhere || this.aura == aura) {
			if (this.trigger == trigger) {
				if (noCost || getRealCostAP() <= human.actionPoint) {
					if (triggerable()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getRealCostAP() {
		var realCost = this.triggerCostAP;
		if (marks.contains(SkillMark.Release)) {
			if (!human.getAllHarassCard().filter(Bee.class).isEmpty()) {
				realCost++;
			}
		} else if (marks.contains(SkillMark.Unseal)) {
			if (human.getOpponent().isHero(Beekeeper.class) && !human.getAllHarassCard().filter(Bee.class).isEmpty()) {
				realCost++;
			}
		}
		return realCost;
	}

	public boolean triggerable() {
		return true;
	}

	public abstract void apply();

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}

	public String getDisplayName() {
		return skillString.name;
	}

	/**
	 * 获取技能使用描述
	 */
	public String getTip() {
		var curCost = getRealCostAP();
		if (curCost > 0) {
			return "[" + curCost + "] " + skillString.tip;
		}
		return skillString.tip;
	}

	public DSkillInfo toMsg() {
		DSkillInfo skillInfo = new DSkillInfo();
		if (card != null) {
			skillInfo.setOwnerId(card.id);
		} else {
			skillInfo.setOwnerId(-1);
		}
		skillInfo.setSkillId(id);
		skillInfo.setSn(getClass().getSimpleName());
		skillInfo.setTip(getTip());
		return skillInfo;
	}

	public static class EmptySkill extends Skill {
		public static final EmptySkill Instance = new EmptySkill();

		@Override
		public void apply() {
		}
	}
}
