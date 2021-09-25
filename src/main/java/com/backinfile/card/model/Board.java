package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DBoardData;
import com.backinfile.card.gen.GameMessageHandler.DBoardInit;
import com.backinfile.card.gen.GameMessageHandler.DBoardSetup;
import com.backinfile.card.gen.GameMessageHandler.DCardInfo;
import com.backinfile.card.gen.GameMessageHandler.DCardInfoList;
import com.backinfile.card.gen.GameMessageHandler.DCardPileInfo;
import com.backinfile.card.gen.GameMessageHandler.DPileNumber;
import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.model.actions.ChangeBoardStateAction;
import com.backinfile.card.model.actions.DispatchAction;
import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.cards.StoreCard;
import com.backinfile.card.server.humanOper.InTurnActiveSkillOper;
import com.backinfile.support.IAlive;
import com.backinfile.support.Time2;
import com.backinfile.support.Utils;

public class Board implements IAlive {
	public List<Human> humans = new ArrayList<>();
	public Human curTurnHuman = null;
	private ActionQueue actionQueue;
	public int turnCount; // 公共轮次
	public int playerTurnCount; // 玩家轮次之和

	private Human startPlayer; // 先手玩家

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

	public final void start() {
		state = BoardState.GamePrepare;
		startPlayer = humans.get(Utils.nextInt(humans.size()));
	}

	public final void start(String startPlayerToken) {
		state = BoardState.GamePrepare;
		startPlayer = getHuman(startPlayerToken);
	}

	/**
	 * 先处理玩家操作，然后检查对局状态，然后逻辑更新 <br/>
	 * 最后，如果处于回合中的空闲状态，请求玩家启动技能
	 */
	@Override
	public void pulse() {
		// 处理玩家操作
		for (var human : humans) {
			if (!human.humanOpers.isEmpty()) {
				for (var humanOper : new ArrayList<>(human.humanOpers)) {
					if (humanOper.isDone()) {
						human.removeHumanOper(humanOper);
					}
				}
			}
		}

		// 有操作尚未完成，不进行游戏逻辑更新
		if (humans.stream().anyMatch(h -> !h.humanOpers.isEmpty())) {
			return;
		}

		// 游戏还没开始
		if (state == BoardState.None) {
			return;
		}
		// 更新对局状态
		if (state != lastState) {
			onStateChangeTo(state);
		}
		lastState = state;

		// 更新action
		actionQueue.pulse();

		// 回合中结算完成后，需要玩家主动出牌
		if (state == BoardState.InTurn && actionQueue.isEmpty()) {
			if (humans.stream().allMatch(h -> h.humanOpers.isEmpty())) {
				curTurnHuman.addHumanOper(new InTurnActiveSkillOper());
			}
		}
	}

	/**
	 * 处理玩家发过来的操作消息
	 */
	public final void onMessage(String token, String content) {
		var human = getHuman(token);
		if (human != null) {
			human.humanOperMessageHandler.onMessage(content);
		}
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
			if (curTurnHuman == null) {
				curTurnHuman = startPlayer;
			} else {
				var index = humans.indexOf(curTurnHuman) + 1;
				curTurnHuman = humans.get(index % humans.size());
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

	public CardSlot getCardSlotByCard(Card card) {
		for (var human : humans) {
			var slot = human.getCardSlotByCard(card);
			if (slot != null) {
				return slot;
			}
		}
		return null;
	}

	// 获取所有可以触发的技能
	public List<Skill> getActivableSkills(String token) {
		List<Skill> activableSkills = new ArrayList<>();
		var human = getHuman(token);
		if (human == null) {
			return activableSkills;
		}
		// 玩家身上的技能
		for (var skill : human.getSkillList()) {
			if (skill.triggerTimesLimit == 0) {
				continue;
			}
			if (skill.trigger == SkillTrigger.Active) {
				if (skill.triggerCostAP <= human.actionPoint) {
					skill.setContext(this, human, null);
					if (skill.triggerable()) {
						activableSkills.add(skill);
					}
				}
			}
		}
		// 手牌上的技能
		for (var card : human.handPile) {
			for (var skill : card.getSkillList()) {
				if (skill.triggerTimesLimit == 0) {
					continue;
				}
				if (skill.trigger == SkillTrigger.Active) {
					if (skill.triggerCostAP <= human.actionPoint) {
						if (skill.aura == SkillAura.Hand) {
							skill.setContext(this, human, card);
							if (skill.triggerable()) {
								activableSkills.add(skill);
							}
						}
					}
				}
			}
		}
		// 储备位上的技能
		for (var slot : human.cardSlotMap.values()) {
			for (var card : slot.getAllCards()) {
				for (var skill : card.getSkillList()) {
					if (skill.triggerTimesLimit == 0) {
						continue;
					}
					if (skill.trigger == SkillTrigger.Active) {
						if (skill.triggerCostAP <= human.actionPoint) {
							if (skill.aura == SkillAura.Slot) {
								skill.setContext(this, human, card);
								if (skill.triggerable()) {
									activableSkills.add(skill);
								}
							}
						}
					}
				}
			}
		}

		// 计划区里的行动牌当作手牌来看, 不消耗行动点
		for (var slot : human.cardSlotMap.values()) {
			if (slot.asPlanSlot && slot.ready) {
				for (var card : slot.getPile(ESlotType.Plan).getFiltered(c -> c instanceof ActionCard)) {
					for (var skill : card.getSkillList()) {
						if (skill.triggerTimesLimit == 0) {
							continue;
						}
						if (skill.trigger == SkillTrigger.Active) {
							if (skill.aura == SkillAura.Hand) {
								skill.setContext(this, human, card);
								if (skill.triggerable()) {
									activableSkills.add(skill);
								}
							}
						}
					}
				}
			}
		}

		return activableSkills;
	}

	public Skill getSkillById(long id) {
		for (var human : humans) {
			var humanSkill = human.getSkill(id);
			if (humanSkill != null) {
				humanSkill.setContext(this, human, null);
				return humanSkill;
			}
			for (var card : human.getAllCards()) {
				var skill = card.getSkill(id);
				if (skill != null) {
					skill.setContext(this, human, card);
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
						pileInfo.setSlotIndex(cardSlot.index);
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
		DCardInfoList cardInfoList = new DCardInfoList();
		cardInfoList.addAllCards(getAllCardInfo());
		cardInfoList.setData(getBoardData(token));
		boardSetup.setCardInfos(cardInfoList);
		return boardSetup;
	}

	public DBoardData getBoardData(String token) {
		DBoardData boardData = new DBoardData();
		{
			var human = getHuman(token);
			if (human != null) {
				boardData.setOpponentPlayerName(human.getOpponent().playerName);
			}
		}
		if (curTurnHuman != null) {
			boardData.setCurTurnPlayerName(curTurnHuman.playerName);
			boardData.setActionPoint(curTurnHuman.actionPoint);
		}
		if (actionQueue.curAction != null && actionQueue.curAction.human != null) {
			boardData.setCurActionPlayerName(actionQueue.curAction.human.playerName);
		}
		{
			// 通知各个牌库中牌的数目
			for (var human : humans) {
				for (var cardPile : human.getNormalPiles()) {
					DPileNumber pileNumber = new DPileNumber();
					pileNumber.setOpponent(!human.token.equals(token));
					pileNumber.setPileType(cardPile.getPileType());
					pileNumber.setNumber(cardPile.size());
					boardData.addPileNumbers(pileNumber);
				}
			}
		}
		return boardData;
	}

	// 推送棋盘数据变更消息
	public void modifyBoardData() {
		for (var human : humans) {
			human.sendMessage(getBoardData(human.token));
		}
	}

	// 推送卡牌变更消息
	public final void modifyCard(Card... cards) {
		modifyCard(new CardPile(cards));
	}

	// 推送卡牌变更消息
	public final void modifyCard(Card card, CardPile cardPile) {
		CardPile modifyPile = new CardPile();
		modifyPile.add(card);
		modifyPile.addAll(cardPile);
		modifyCard(modifyPile);
	}

	// 推送卡牌变更消息
	public final void modifyCard(CardPile... cardPiles) {
		CardPile modify = new CardPile();
		for (var pile : cardPiles) {
			modify.addAll(pile);
		}
		modifyCard(modify);
	}

	// 推送卡牌变更消息
	public final void modifyCard(CardPile cardPile) {
		if (cardPile.isEmpty()) {
			return;
		}
		DCardInfoList cardInfoList = new DCardInfoList();
		cardInfoList.addAllCards(getAllCardInfo(cardPile));
		for (var human : humans) {
			cardInfoList.setData(getBoardData(human.token));
			human.sendMessage(cardInfoList.copy());
		}
	}

	// 使用技能
	public final void applySkill(Skill skill) {
		// 消耗行动点 计划卡不用消耗
		if (skill.triggerCostAP > 0) {
			boolean isPlanCard = false;
			if (skill.card != null) {
				var slot = getCardSlotByCard(skill.card);
				if (slot != null) {
					if (slot.getPile(ESlotType.Plan).contains(skill.card)) {
						isPlanCard = true;
					}
				}
			}
			if (!isPlanCard) {
				skill.human.actionPoint = Math.max(0, skill.human.actionPoint - skill.triggerCostAP);
				modifyBoardData();
			}
		}
		// 消耗执行次数
		if (skill.triggerTimesLimit > 0) {
			skill.triggerTimesLimit--;
		}
		// 执行skill
		skill.apply();
	}
}
