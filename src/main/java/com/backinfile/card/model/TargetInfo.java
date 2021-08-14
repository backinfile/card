package com.backinfile.card.model;

import com.backinfile.card.support.Param;

public class TargetInfo {

	public static final TargetInfo EMPTY = new TargetInfo(TargetType.None);
	public TargetInfo.TargetType targetType = TargetType.None;
	public int number = 1; // 需求数量 -1表示不限制数量
	public boolean optional = false; // 是否可选任意张
	public String tip = "select a card"; // 选取提示
	public Param param = new Param(); // 额外参数

	public TargetInfo(TargetInfo.TargetType targetType) {
		this.targetType = targetType;
	}

	public TargetInfo(TargetType targetType, int number, boolean optional) {
		this.targetType = targetType;
		this.number = number;
		this.optional = optional;
	}

	public TargetInfo(TargetType targetType, int number, boolean optional, String tip) {
		this.targetType = targetType;
		this.number = number;
		this.optional = optional;
		this.tip = tip;
	}

	public static enum TargetType {
		None, // 不能选目标
		Confirm, // 确认与否
		Hand, // 自己手牌里选
		Store, // 储备卡
		ReadyStore, // 储备完成的储备
	}
}