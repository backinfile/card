package com.backinfile.card.server.local;

import java.util.LinkedList;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.TargetInfo;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.IAlive;
import com.backinfile.support.func.Terminal;

public class LocalGameServer extends Terminal<MessageWarpper, MessageWarpper> implements IAlive {
	public Board board;
	private GameMessageHandler gameMessageHandler;

	// 当前正在执行操作的Human
	private LinkedList<HumanOperCache> waitingHumanOper = new LinkedList<>();

	public LocalGameServer() {
	}

	public void init() {
		board = new Board();
		gameMessageHandler = new GameMessageHandler();
		gameMessageHandler.addListener(new LocalGameServerMessageHandler(this, board));
	}

	public void startGame(DBoardInit boardInit) {
		board.init(boardInit);
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
