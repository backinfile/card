package com.backinfile.card.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.DStartPileDataPair;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.Chap2ActionCard;
import com.backinfile.card.model.cards.Chap2HeroCard;
import com.backinfile.card.model.cards.MonsterCard;
import com.backinfile.support.Time2;

public class GameUtils {
	public static boolean isAI(Human human) {
		return human.token.equals(ConstGame.AI_TOKEN);
	}

	public static DBoardInit getDefaultBoardInit() {
		DBoardInit boardInit = new DBoardInit();
		boardInit.setSeed(Time2.getCurMillis());
		{
			DHumanInit humanInit = new DHumanInit();
			humanInit.setControllerToken(LocalData.instance().token);
			humanInit.setPlayerName(LocalData.instance().name);
			humanInit.setHeroCard(LocalData.instance().startHeroCard);
			humanInit.setPileList(getLocalStartPile());
			boardInit.addHumanInits(humanInit);
		}
		{
			DHumanInit aiInit = new DHumanInit();
			aiInit.setControllerToken(ConstGame.AI_TOKEN);
			aiInit.setPlayerName(LocalString.getUIString("AILevel").strs[LocalData.instance().AILevel]);
			aiInit.setHeroCard(CardManager.getRandomCard(c -> c instanceof Chap2HeroCard));
			aiInit.setPileList(getStartPile());
			boardInit.addHumanInits(aiInit);
		}
		return boardInit;
	}

	public static HashMap<String, Integer> getDefaultStartPile() {
		HashMap<String, Integer> pile = new HashMap<>();
		for (var card : CardManager.getAllOriCards().values()) {
			if (card instanceof Chap2ActionCard) {
				pile.put(card.getClass().getSimpleName(), 6);
			} else if (card instanceof MonsterCard) {
				pile.put(card.getClass().getSimpleName(), 4);
			}
		}
		return pile;
	}

	private static List<DStartPileDataPair> getLocalStartPile() {
		List<DStartPileDataPair> startPile = new ArrayList<>();
		for (var entry : LocalData.instance().startPile.entrySet()) {
			var cards = new DStartPileDataPair();
			cards.setCard(entry.getKey());
			cards.setCount(entry.getValue());
			startPile.add(cards);
		}
		return startPile;

	}

	private static List<DStartPileDataPair> getStartPile() {
		List<DStartPileDataPair> startPile = new ArrayList<>();
		for (var card : CardManager.getAllOriCards().values()) {
			if (card instanceof Chap2ActionCard) {
				var cards = new DStartPileDataPair();
				cards.setCard(card.getClass().getSimpleName());
				cards.setCount(6);
				startPile.add(cards);
			} else if (card instanceof MonsterCard) {
				var cards = new DStartPileDataPair();
				cards.setCard(card.getClass().getSimpleName());
				cards.setCount(4);
				startPile.add(cards);
			}
		}
		return startPile;
	}
}
