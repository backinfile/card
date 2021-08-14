package com.backinfile.card.model;

import com.backinfile.card.support.Param;

public class TargetInfo {

	public static final TargetInfo EMPTY = new TargetInfo(TargetType.None);
	public TargetInfo.TargetType targetType = TargetType.None;
	public int number = 1; // 需求数量
	public Param param = new Param(); // 额外参数
	public boolean optional = false; // 是否可跳过
	public String tip = "select a card"; // 选取提示

	public TargetInfo(TargetInfo.TargetType targetType) {
		this.targetType = targetType;
	}

	public static enum TargetType {
		None, // 不能选目标
		Hand, // 自己手牌里选
		Store, // 储备卡
		ReadyStore, // 储备完成的储备
	}
}