package com.backinfile.card.manager;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.Settings;
import com.backinfile.card.model.Card;
import com.backinfile.support.Log;
import com.backinfile.support.reflection.ReflectionUtils;
import com.backinfile.support.reflection.Timing;

// 默认使用卡牌的类的simpleName作为唯一标识
public class CardManager {
	private static Map<String, Card> allCards = new HashMap<>();

	@Timing("init card")
	public static void init() {
		Log.game.info("------------- init card start -------------");
		for (var clazz : ReflectionUtils.getClassesExtendsClass(Settings.PackageName, Card.class)) {
			try {
				Card card = (Card) clazz.getConstructor().newInstance();
				allCards.put(clazz.getSimpleName(), card);
			} catch (Exception e) {
				Log.res.warn("create card {} failed, {}", clazz.getSimpleName(), e.getMessage());
			}
		}
		Log.game.info("------------- init card over -------------");
	}

	/**
	 * 获取一张新的卡牌
	 */
	public static Card getCard(String sn, String oriHumanToken) {
		Card copy = allCards.get(sn).copy();
		copy.oriHumanToken = oriHumanToken;
		return copy;
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
