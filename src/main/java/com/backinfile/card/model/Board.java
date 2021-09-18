package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardData;
import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DBoardSetup;
import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.gen.GameMessageHandler.DCardInfoList;
import com.backinfile.card.gen.GameMessageHandler.DCardPileInfo;
import com.backinfile.card.model.actions.ChangeBoardStateAction;
import com.backinfile.card.model.actions.DispatchAction;
import com.backinfile.support.IAlive;
import com.backinfile.support.Time2;
import com.backinfile.support.Utils;

public class Board implements IAlive {
	public List<Human> humans = new ArrayList<>();
	public Human curTurnHuman = null;
	private ActionQueue actionQueue;
	public int turnCount; // 公共轮次
	public int playerTurnCount; // 玩家轮次之和

	public BoardState state = BoardState.None;
	public BoardState lastState = BoardState.None;

	public static enum BoardState {
		None, // 未开始
		GamePrepare, // 进入准备阶段
		TurnStart, // 进入回合开始阶段
		InTurn, // 回合进行中
		TurnEnd, // 回合结束阶段
	}

	public void init(DBoardInit boardInit) {
		actionQueue = new ActionQueue(this);
		actionQueue.init();

		// 初始化随机种子
		if (boardInit.getSeed() > 0) {
			Utils.setRndSeed(boardInit.getSeed());
		} else {
			Utils.setRndSeed(Time2.getCurMillis());
		}

		// 初始化每个玩家及其牌库
		for (var dhuman : boardInit.getHumanInitsList()) {
			Human human = new Human();
			human.board = this;
			human.init(dhuman);
			humans.add(human);
		}
	}

	public void start() {
		state = BoardState.GamePrepare;
	}

	public void pulseLoop() {
		while (!isWaitingHumanOper()) {
			pulse();
		}
	}

	@Override
	public void pulse() {
		if (state == BoardState.None) {
			return;
		}
		if (state != lastState) {
			onStateChangeTo(state);
		}
		lastState = state;
		actionQueue.pulse();
	}

	private void onStateChangeTo(BoardState state) {
		switch (state) {
		case GamePrepare: { // 调度阶段
			turnCount = 0;
			playerTurnCount = 0;
			for (var human : humans) {
				human.onGameStart();
			}
			actionQueue.addLast(new DispatchAction(humans));
			actionQueue.addLast(new ChangeBoardStateAction(BoardState.TurnStart));
			break;
		}
		case TurnStart: {
			// 增加轮次计数
			if (playerTurnCount % humans.size() == 0) {
				turnCount++;
			}
			// 回合开始，找到当前回合玩家
			if (curTurnHuman != null) {
				int curIndex = humans.indexOf(curTurnHuman);
				curTurnHuman = humans.get((curIndex + 1) % humans.size());
			} else {
				int rnd = Utils.nextInt(0, humans.size());
				curTurnHuman = humans.get(rnd);
			}
			// 回合开始
			playerTurnCount++;
			curTurnHuman.onTurnStart();
			actionQueue.addLast(new ChangeBoardStateAction(BoardState.InTurn));
			break;
		}
		case InTurn: {
			break;
		}
		case TurnEnd: {
			if (curTurnHuman != null) {
				curTurnHuman.onTurnEnd();
			}
			actionQueue.addLast(new ChangeBoardStateAction(BoardState.TurnStart));
			break;
		}
		default:
			break;
		}
	}

	public boolean removeCard(Card card) {
		for (var human : humans) {
			if (human.removeCard(card)) {
				return true;
			}
		}
		return false;
	}

	public ActionQueue getActionQueue() {
		return actionQueue;
	}

	public boolean isWaitingHumanOper() {
		// 有人在执行Action, 等待做出选择
		for (var human : humans) {
			if (human.targetInfo.needSelectTarget()) {
				return true;
			}
		}
		// 回合中，等待执行行动
		if (state == BoardState.InTurn && actionQueue.isEmpty()) {
			return true;
		}
		return false;
	}

	public Human getHuman(String token) {
		for (var human : humans) {
			if (human.token.equals(token)) {
				return human;
			}
		}
		return null;
	}

	public Human getCurTurnHuman() {
		return curTurnHuman;
	}

	public Human getOpponent(Human human) {
		int index = humans.indexOf(human);
		return humans.get((index + 1) % humans.size());
	}

	public Card getCard(long id) {
		for (var human : humans) {
			var card = human.getCard(id);
			if (card != null) {
				return card;
			}
		}
		return null;
	}

	public Skill getSkillById(long id) {
		for (var human : humans) {
			var humanSkill = human.getSkill(id);
			if (humanSkill != null) {
				return humanSkill;
			}
			for (var card : human.getAllCards()) {
				var skill = card.getSkill(id);
				if (skill != null) {
					return skill;
				}
			}
		}
		return null;
	}

	public final void addLast(Action action) {
		actionQueue.addLast(action);
	}

	public final void addFirst(Action action) {
		actionQueue.addFirst(action);
	}

	public final List<DCardInfo> getAllCardInfo() {
		return getAllCardInfo(new CardPile());
	}

	public final List<DCardInfo> getAllCardInfo(CardPile inCardList) {
		var cardInfos = new ArrayList<DCardInfo>();
		for (var human : humans) {
			for (var cardPile : human.getNormalPiles()) {
				for (var tuple : cardPile.cardsWithIndex()) {
					var card = tuple.value2;
					var index = tuple.value1;
					if (!inCardList.isEmpty() && !inCardList.contains(card)) {
						continue;
					}
					var cardInfo = new DCardInfo();
					cardInfo.setId(card.id);
					cardInfo.setSn(card.cardString.sn);
					DCardPileInfo pileInfo = new DCardPileInfo();
					cardInfo.setPileInfo(pileInfo);
					pileInfo.setPlayerToken(human.token);
					pileInfo.setPileType(cardPile.getPileType());
					pileInfo.setPileSize(cardPile.size());
					pileInfo.setPileIndex(index);
					cardInfos.add(cardInfo);
				}
			}
			for (var cardSlot : human.cardSlotMap.values()) {
				for (var entry : cardSlot.slotPileMap.entrySet()) {
					var slotType = entry.getKey();
					var pile = entry.getValue();
					for (var tuple : pile.cardsWithIndex()) {
						var card = tuple.value2;
						var index = tuple.value1;
						if (!inCardList.isEmpty() && !inCardList.contains(card)) {
							continue;
						}
						var cardInfo = new DCardInfo();
						cardInfo.setId(card.id);
						cardInfo.setSn(card.cardString.sn);
						DCardPileInfo pileInfo = new DCardPileInfo();
						cardInfo.setPileInfo(pileInfo);
						pileInfo.setPlayerToken(human.token);
						pileInfo.setPileType(pile.getPileType());
						pileInfo.setSlotType(slotType);
						pileInfo.setPileSize(pile.size());
						pileInfo.setPileIndex(index);
						pileInfo.setAsPlanSlot(cardSlot.asPlanSlot);
						pileInfo.setReady(cardSlot.ready);
						cardInfos.add(cardInfo);
					}
				}
			}
		}
		return cardInfos;
	}

	public DBoardSetup getBoardSetup(String token) {
		DBoardSetup boardSetup = new DBoardSetup();
		boardSetup.setData(getBoardData(token));
		DCardInfoList cardInfoList = new DCardInfoList();
		cardInfoList.addAllCards(getAllCardInfo());
		boardSetup.setCardInfos(cardInfoList);
		return boardSetup;
	}

	public DBoardData getBoardData(String token) {
		DBoardData boardData = new DBoardData();
		var human = getHuman(token);
		if (human != null) {
			boardData.setOpponentPlayerName(human.getOpponent().playerName);
		}
		if (curTurnHuman != null) {
			boardData.setCurTurnPlayerName(curTurnHuman.playerName);
			boardData.setActionPoint(curTurnHuman.actionPoint);
		}
		if (actionQueue.curAction != null && actionQueue.curAction.human != null) {
			boardData.setCurActionPlayerName(actionQueue.curAction.human.playerName);
		}
		return boardData;
	}

	public final void modifyCard(Card... cards) {
		modifyCard(new CardPile(cards));
	}

	public final void modifyCard(CardPile cardPile) {
		if (cardPile.isEmpty()) {
			return;
		}
		DCardInfoList cardInfoList = new DCardInfoList();
		cardInfoList.addAllCards(getAllCardInfo(cardPile));
		for (var human : humans) {
			human.msgCacheQueue.add(cardInfoList);
		}
	}
}
