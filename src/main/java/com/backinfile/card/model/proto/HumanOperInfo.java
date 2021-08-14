package com.backinfile.card.model.proto;

public class HumanOperInfo {

	public static enum Type {
		EndTurn, // 回合结束
		GiveUp, // 放弃游戏
		PlayCard, // 打出手牌
		SelectTarget, // 选择卡牌 对应human的targetInfo
		ActiveSkill, // 激活技能
	}
}
