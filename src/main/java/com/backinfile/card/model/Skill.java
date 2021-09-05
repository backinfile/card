package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessage.DTargetInfo;
import com.backinfile.card.model.LocalString.LocalSkillString;
import com.backinfile.card.model.actions.IOperable;
import com.backinfile.support.IdAllot;

// 技能 主动触发，或在Action中有交互
public abstract class Skill {
	// 自身属性
	public long id;
	protected LocalSkillString skillString;
	public TargetInfo targetInfo = new TargetInfo(null); // 触发条件
	public int activeCostActionPoint = 1; // 主动激活消耗行动力

	// 联动效果
	public List<IOperable> operables = new ArrayList<>();

	// 执行上下文
	public Board board;
	public Human human;
	public Card card;

	// 触发时效控制
	public SkillDuration duration = SkillDuration.Combat;
	public SkillTrigger trigger = SkillTrigger.Passive;
	public SkillAura aura = SkillAura.Combat;
	public int triggerTimesLimit = -1; // 当恰好==0时清除
	public int triggerTimesPerTurnLimit = -1; // 当恰好==0时清除

	public static enum SkillDuration {
		Fixed, // 本身固有属性，不会被自动清除
		Combat, // 本局对战中一直存在
		OwnerStartTurn, // skill拥有者回合开始清除
		OwnerEndTurn, // skill拥有者回合结束清除
	}

	public static enum SkillTrigger {
		Active, // 主动激活
		Passive, // 被动
		OptionalPassive, // 可选被动
	}

	public static enum SkillAura {
		Always, // 任何地方
		Combat, // 场上
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

	// 检查是否可以启用
	public boolean canActive() {
		if (targetInfo == null) {
			return true;
		}
		targetInfo.human = this.human;
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
