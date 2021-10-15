package com.backinfile.card.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.backinfile.card.Settings;
import com.backinfile.card.model.Card;
import com.backinfile.support.Log;
import com.backinfile.support.SysException;
import com.backinfile.support.Utils;
import com.backinfile.support.reflection.ReflectionUtils;
import com.backinfile.support.reflection.Timing;

// 默认使用卡牌的类的simpleName作为唯一标识
public class CardManager {
	private static Map<String, Class<?>> allCards = new HashMap<>();
	private static Map<String, Card> allOriCards = new HashMap<>();

	@Timing("init card")
	public static void init() {
		for (var clazz : ReflectionUtils.getClassesExtendsClass(Settings.PACKAGE_NAME, Card.class)) {
			var sn = clazz.getSimpleName();
			allCards.put(sn, clazz);
			try {
				allOriCards.put(clazz.getSimpleName(), (Card) clazz.getConstructor().newInstance());
			} catch (Exception e) {
				throw new SysException("error in create Card:" + clazz.getSimpleName() + " " + e.getMessage());
			}
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

	public static Map<String, Card> getAllOriCards() {
		return allOriCards;
	}

	public static String getRandomCard(Predicate<Card> predicate) {
		var cards = allOriCards.values().stream().filter(predicate).collect(Collectors.toList());
		return cards.get(Utils.nextInt(cards.size())).getClass().getSimpleName();
	}

	public static List<Card> getSpecOriCards(Predicate<Card> predicate) {
		return allOriCards.values().stream().filter(predicate).collect(Collectors.toList());
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
