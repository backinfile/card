package com.backinfile.card.manager;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.Settings;
import com.backinfile.card.model.Card;
import com.backinfile.support.Log;
import com.backinfile.support.SysException;
import com.backinfile.support.reflection.ReflectionUtils;
import com.backinfile.support.reflection.Timing;

// 默认使用卡牌的类的simpleName作为唯一标识
public class CardManager {
	private static Map<String, Class<?>> allCards = new HashMap<>();

	@Timing("init card")
	public static void init() {
		for (var clazz : ReflectionUtils.getClassesExtendsClass(Settings.PackageName, Card.class)) {
			allCards.put(clazz.getSimpleName(), clazz);
		}
		Log.game.info("find {} cards", allCards.size());
	}

	/**
	 * 获取一张新的卡牌
	 */
	public static Card getCard(String sn, String oriHumanToken) {
		try {
			Card card = (Card) allCards.get(sn).getConstructor().newInstance();
			card.oriHumanToken = oriHumanToken;
			return card;
		} catch (Exception e) {
			throw new SysException("error in create Card:" + sn + " " + e.getMessage());
		}
	}

	// 获取卡牌标识
	public static String getCardSn(Class<? extends Card> clazz) {
		return clazz.getSimpleName();
	}

	// 获取卡牌标识
	public static String getCardSn(Card card) {
		return card.getClass().getSimpleName();
	}
}
