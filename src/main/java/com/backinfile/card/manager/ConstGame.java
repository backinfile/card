package com.backinfile.card.manager;

import com.backinfile.support.Time2;

// 一些游戏中用的常量
public class ConstGame {

	// AI相关
	public static final String AI_TOKEN = "ai";
	public static long AI_WAIT_TIME = Time2.SEC;
	public static boolean AI_DO_NOTHING = false;
	public static boolean AI_DO_STORE = false;

	public static final int SlotPileNumber = 5; // 默认的储备位个数

	public static boolean THREATEN_OPEN = false;

	public static boolean CARD_LIST_VIEW = false;
}
