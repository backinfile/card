package com.backinfile.card.manager;

import com.backinfile.support.Time2;

// 一些游戏中用的常量
public class ConstGame {

	// AI相关
	public static final String AI_TOKEN = "ai";
	public static long AI_WAIT_TIME = Time2.SEC * 1;

	public static final int SlotPileNumber = 5; // 默认的储备位个数
	public static final int HAND_PILE_MAX_SIZE = 8; // 默认的手牌上限

	public static boolean THREATEN_OPEN = true;
	public static int THREATHEN_WIN_NUMBER = 12;

	public static boolean CARD_LIST_VIEW = false;
}
