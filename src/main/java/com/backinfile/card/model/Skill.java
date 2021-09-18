package com.backinfile.card.model;

import com.backinfile.card.model.LocalString.LocalSkillString;
import com.backinfile.support.IdAllot;

// 技能 主动触发，或在Action中有交互
public abstract class Skill {
	// 自身属性
	public long id;
	protected LocalSkillString skillString;

	// 执行上下文
	public Board board;
	public Human human;
	public Card card;

	// 触发时效控制
	public TargetInfo targetInfo = new TargetInfo(null); // 触发条件
	public SkillDuration duration = SkillDuration.Combat; // 失效方式
	public SkillTrigger trigger = SkillTrigger.Passive; // 触发方式
	public SkillAura aura = SkillAura.Combat; // 可触发区域
	public int triggerCostAP = 0; // 使用时需要消耗的行动点
	public int triggerTimesLimit = -1; // 可触发次数，当恰好==0时清除

	public static enum SkillDuration {
		Fixed, // 本身固有属性，不会被自动清除
		Combat, // 本局对战中一直存在
		OwnerStartTurn, // skill拥有者回合开始清除
		OwnerEndTurn, // skill拥有者回合结束清除
	}

	// 触发方式
	public static enum SkillTrigger {
		Active, // 主动激活
		Passive, // 强制型被动
		OptionalPassive, // 可选被动
	}

	// 生效地点
	public static enum SkillAura {
		AnyWhere, // 任何地方
		Combat, // 场上(非手牌)
		Hand, // 手牌
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
		targetInfo.human = human;
	}

	public void setTriggerType(SkillDuration duration, SkillTrigger trigger, SkillAura aura, int cost, int limit) {
		this.duration = duration;
		this.trigger = trigger;
		this.aura = aura;
		this.triggerCostAP = cost;
		this.triggerTimesLimit = limit;
	}

	public void setTriggerType(SkillDuration duration, SkillTrigger trigger, SkillAura aura, int cost) {
		setTriggerType(duration, trigger, aura, cost, -1);
	}

	// 检查是否可以启用
	public boolean isTargetFit() {
		return targetInfo.test();
	}

	public void apply() {
	}

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}

	public String getDisplayName() {
		return skillString.name;
	}

	public static class EmptySkill extends Skill {
		public static final EmptySkill Instance = new EmptySkill();
	}
}
