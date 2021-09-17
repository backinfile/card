package com.backinfile.card.manager;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.DStartPileDataPair;
import com.backinfile.card.gen.GameMessageHandler.DTargetInfo;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.gen.GameMessageHandler.ETargetType;
import com.backinfile.card.model.cards.chapter2.Attack;
import com.backinfile.card.model.cards.chapter2.Beekeeper;
import com.backinfile.card.model.cards.chapter2.Dragon;
import com.backinfile.support.Time2;

public class GameUtils {
	public static DTargetInfo newTargetInfo(ETargetType type, String tip) {
		return newTargetInfo(type, 0, -1, tip);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, int number, String tip) {
		return newTargetInfo(type, ETargetSlotAimType.None, number, tip);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, int minNumber, int maxNumber, String tip) {
		DTargetInfo newTargetInfo = newTargetInfo(type, ETargetSlotAimType.Occupy, 1, tip);
		newTargetInfo.setMinNumber(minNumber);
		newTargetInfo.setMaxNumber(maxNumber);
		return newTargetInfo;
	}

	public static DTargetInfo newTargetInfo(ETargetType type, ETargetSlotAimType slotAimType, int number, String tip) {
		return newTargetInfo(type, slotAimType, number, tip, false, false, false, false);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, int number, String tip, boolean onlyReady,
			boolean exceptPlan) {
		return newTargetInfo(type, ETargetSlotAimType.None, number, tip, onlyReady, exceptPlan, false, false);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, ETargetSlotAimType slotAimType, int number, String tip,
			boolean onlyReady, boolean exceptPlan) {
		return newTargetInfo(type, slotAimType, number, tip, onlyReady, exceptPlan, false, false);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, ETargetSlotAimType slotAimType, int number, String tip,
			boolean onlyReady, boolean exceptPlan, boolean opponent, boolean exceptHand) {
		DTargetInfo targetInfo = new DTargetInfo();
		targetInfo.setType(type);
		targetInfo.setSlotAimType(slotAimType);
		targetInfo.setTip(tip);
		targetInfo.setMinNumber(number);
		targetInfo.setMaxNumber(number);
		targetInfo.setOnlyReady(onlyReady);
		targetInfo.setExceptPlan(exceptPlan);
		targetInfo.setOpponent(opponent);
		targetInfo.setExceptHand(exceptHand);
		return targetInfo;
	}

	public static void setNumber(DTargetInfo targetInfo, int minNumber, int maxNumber) {
		targetInfo.setMinNumber(minNumber);
		targetInfo.setMaxNumber(maxNumber);
	}

	public static DBoardInit getDefaultBoardInit() {
		DBoardInit boardInit = new DBoardInit();
		boardInit.setSeed(Time2.getCurMillis());
		{
			DHumanInit humanInit = new DHumanInit();
			humanInit.setControllerToken(LocalData.instance().token);
			humanInit.setHeroCard(Beekeeper.class.getSimpleName());
			humanInit.setPileList(getStartPile());
			boardInit.addHumanInits(humanInit);
		}
		{
			DHumanInit aiInit = new DHumanInit();
			aiInit.setControllerToken(ConstGame.AI_TOKEN);
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
