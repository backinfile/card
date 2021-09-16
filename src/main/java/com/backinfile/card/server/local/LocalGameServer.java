package com.backinfile.card.server.local;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.DStartPileDataPair;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.cards.chapter2.Attack;
import com.backinfile.card.model.cards.chapter2.Beekeeper;
import com.backinfile.card.model.cards.chapter2.Dragon;
import com.backinfile.support.IAlive;
import com.backinfile.support.Time2;
import com.backinfile.support.func.Terminal;

public class LocalGameServer extends Terminal<MessageWarpper, MessageWarpper> implements IAlive {
	private Board board;
	private List<TargetInfo> targetInfos = new ArrayList<>();

	public LocalGameServer() {
	}

	public void init() {
		targetInfos.clear();
		board = new Board();
		board.init(getBoardInit());
	}

	private static DBoardInit getBoardInit() {
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
			aiInit.setControllerToken(LocalData.instance().token);
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

	@Override
	public void pulse() {
		if (board == null) {
			return;
		}
	}
}
