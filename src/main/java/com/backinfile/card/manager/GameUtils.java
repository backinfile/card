package com.backinfile.card.manager;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.DStartPileDataPair;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.cards.Chap2ActionCard.Attack;
import com.backinfile.card.model.cards.Chap2ActionCard.Harass;
import com.backinfile.card.model.cards.Chap2ActionCard.Recall;
import com.backinfile.card.model.cards.Chap2ActionCard.Ride;
import com.backinfile.card.model.cards.Chap2HeroCard.Beekeeper;
import com.backinfile.card.model.cards.Chap2HeroCard.BlackTurtle;
import com.backinfile.card.model.cards.Chap2HeroCard.HeartFire;
import com.backinfile.card.model.cards.Chap2HeroCard.RedPhoenix;
import com.backinfile.card.model.cards.Chap2HeroCard.WhiteTiger;
import com.backinfile.card.model.cards.MonsterCard.Bee;
import com.backinfile.card.model.cards.MonsterCard.Bird;
import com.backinfile.card.model.cards.MonsterCard.Cat;
import com.backinfile.card.model.cards.MonsterCard.Dear;
import com.backinfile.card.model.cards.MonsterCard.Dragon;
import com.backinfile.card.model.cards.MonsterCard.Whale;
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
			humanInit.setHeroCard(WhiteTiger.class.getSimpleName());
			humanInit.setPileList(getStartPile());
			boardInit.addHumanInits(humanInit);
		}
		{
			DHumanInit aiInit = new DHumanInit();
			aiInit.setControllerToken(ConstGame.AI_TOKEN);
			aiInit.setPlayerName("AI01");
			aiInit.setHeroCard(HeartFire.class.getSimpleName());
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
			cards.setCard(Ride.class.getSimpleName());
			cards.setCount(6);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Harass.class.getSimpleName());
			cards.setCount(6);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Recall.class.getSimpleName());
			cards.setCount(6);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Dragon.class.getSimpleName());
			cards.setCount(4);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Bee.class.getSimpleName());
			cards.setCount(4);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Whale.class.getSimpleName());
			cards.setCount(4);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Dear.class.getSimpleName());
			cards.setCount(4);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Bird.class.getSimpleName());
			cards.setCount(4);
			startPile.add(cards);
		}
		{
			var cards = new DStartPileDataPair();
			cards.setCard(Cat.class.getSimpleName());
			cards.setCount(4);
			startPile.add(cards);
		}
		return startPile;
	}
}
