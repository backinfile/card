package com.backinfile.card.manager;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.DStartPileDataPair;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.chapter2.Attack;
import com.backinfile.card.model.cards.chapter2.Beekeeper;
import com.backinfile.card.model.cards.chapter2.Dragon;
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
			humanInit.setHeroCard(Beekeeper.class.getSimpleName());
			humanInit.setPileList(getStartPile());
			boardInit.addHumanInits(humanInit);
		}
		{
			DHumanInit aiInit = new DHumanInit();
			aiInit.setControllerToken(ConstGame.AI_TOKEN);
			aiInit.setPlayerName("AI01");
			aiInit.setHeroCard(Beekeeper.class.getSimpleName());
			aiInit.setPileList(getStartPile());
			boardInit.addHumanInits(aiInit);
		}
		return boardInit;
	}

	private static List<DStartPileDataPair> getStartPile() {
		List<DStartPileDataPair> startPile = new ArrayList<>();
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Attack.class.getSimpleName());
			cards.setCount(6);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Dragon.class.getSimpleName());
			cards.setCount(6);
			startPile.add(cards);
		}
		return startPile;
	}
}
