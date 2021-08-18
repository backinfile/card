package com.backinfile.card.manager;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.Settings;
import com.backinfile.card.model.Card;
import com.backinfile.support.Log;
import com.backinfile.support.ReflectionUtils;
import com.backinfile.support.Timing;

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
}
