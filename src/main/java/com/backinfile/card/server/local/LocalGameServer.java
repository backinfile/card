package com.backinfile.card.server.local;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.DStartPileDataPair;
import com.backinfile.card.gen.GameMessageHandler.SCSelectCards;
import com.backinfile.card.manager.ConstGame;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.card.model.cards.chapter2.Attack;
import com.backinfile.card.model.cards.chapter2.Beekeeper;
import com.backinfile.card.model.cards.chapter2.Dragon;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.IAlive;
import com.backinfile.support.Time2;
import com.backinfile.support.func.Terminal;

public class LocalGameServer extends Terminal<MessageWarpper, MessageWarpper> implements IAlive {
	public Board board;

	// 当前正在执行操作的Human
	private LinkedList<HumanOperCache> waitingHumanOper = new LinkedList<>();

	public LocalGameServer() {
	}

	public void init() {
		waitingHumanOper.clear();
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

	@Override
	public void pulse() {
		if (board == null) {
			return;
		}

		// 等待玩家执行操作
		if (!waitingHumanOper.isEmpty()) {
			return;
		}

		// 等待玩家执行操作
		if (board.isWaitingHumanOper()) {
			for (var human : board.humans) {
				if (human.targetInfo.needSelectTarget()) {
					onTargetInfoAttach(human);
				}
			}
			return;
		}

		// 正常游戏循环
		board.pulse();
	}

	// 玩家需要执行一个操作， 推送消息给玩家
	private void onTargetInfoAttach(Human human) {
		var humanOperCache = new HumanOperCache(human);
		waitingHumanOper.add(humanOperCache);
		TargetInfo targetInfo = human.targetInfo;
		if (targetInfo.isSelectCardType()) {
			// 推送卡牌选择消息
			var info = targetInfo.stepSelectCard(humanOperCache.selected);
			sendMessage(human, info.toMsg());
			return;
		}
		switch (targetInfo.targetInfo.getType()) {
		case None:
		case Confirm:
		case EmptySlot:
			break;
		case DiscardPile:
		case DrawPile:
		case HandPile:
		case Store:
		case StoreInSlot:
		default:
			break;
		}
	}

	private void sendMessage(Human human, DSyncBase msg) {
		outputMsg(MessageWarpper.pack(human, msg));
	}
}
