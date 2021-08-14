package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.model.actions.IOperable;

public abstract class Skill {
	// 自身属性
	public long id;
	public String name;
	public boolean passive = true; // 默认是被动技能

	// 联动效果
	public List<IOperable> operables = new ArrayList<>();

	// 执行上下文
	public Board board;
	public Human human;
	public SkillCaster caster;

	// 触发时效控制
	public SkillDuration duration = SkillDuration.Combat;
	public int triggerTimesLimit = -1; // 当恰好==0时清除
	public int triggerTimesPerTurnLimit = -1; // 当恰好==0时清除

	public static enum SkillDuration {
		Fixed, // 本身固有属性，不会被自动清除
		Combat, // 本局对战中一直存在
		OwnerStartTurn, // skill拥有者回合开始清除
		OwnerEndTurn, // skill拥有者回合结束清除
	}

	public Skill(long id, String name) {
		this.id = id;
		this.name = name;
	}

	// 若是主动技能，检查是否可以启用
	public boolean canActive() {
		return false;
	}

	public void apply() {
	}

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}
}
