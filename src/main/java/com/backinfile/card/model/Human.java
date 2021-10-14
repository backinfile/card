package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.backinfile.card.gen.GameMessageHandler;
import com.backinfile.card.gen.GameMessageHandler.DHumanInit;
import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.manager.CardManager;
import com.backinfile.card.manager.ConstGame;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board.BoardMode;
import com.backinfile.card.model.Card.CardType;
import com.backinfile.card.model.Skill.SkillDuration;
import com.backinfile.card.model.actions.BirdHarassAction;
import com.backinfile.card.model.actions.DiscardHandToMaxAction;
import com.backinfile.card.model.actions.DrawCardAction;
import com.backinfile.card.model.actions.DreamBuilderLandAction;
import com.backinfile.card.model.actions.GainAPAction;
import com.backinfile.card.model.actions.RestoreActionNumberAction;
import com.backinfile.card.model.actions.SaveThreatenAction;
import com.backinfile.card.model.cards.Chap2HeroCard.DreamBuilder;
import com.backinfile.card.model.cards.Chap2HeroCard.RedPhoenix;
import com.backinfile.card.model.cards.Chap2HeroCard.WhiteTiger;
import com.backinfile.card.model.cards.HeroCard;
import com.backinfile.card.model.cards.MonsterCard.Bird;
import com.backinfile.card.model.cards.MonsterCard.Cat;
import com.backinfile.card.model.skills.ActAsStoreSkill;
import com.backinfile.card.model.skills.DrawCardSkill;
import com.backinfile.card.model.skills.Pass2CardSkill;
import com.backinfile.card.model.skills.PlanSkill;
import com.backinfile.card.model.skills.ThreatenToAPSkill;
import com.backinfile.card.model.skills.TurnEndSkill;
import com.backinfile.card.server.humanOper.HumanOper;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.Log;

public class Human extends SkillCaster {
	// 固有属性
	public String token;
	public String playerName;

	// 外部属性
	public Board board;

	// Human等待发送到客户端的消息队列
	public LinkedList<DSyncBase> msgCacheQueue = new LinkedList<>();

	// 玩家操作相关
	public List<HumanOper> humanOpers = new ArrayList<>();
	public GameMessageHandler humanOperMessageHandler = new GameMessageHandler();

	// 游戏内相关
	public CardPile heroPile = new CardPile(ECardPileType.HeroPile);
	public CardPile handPile = new CardPile(ECardPileType.HandPile);
	public CardPile markPile = new CardPile(ECardPileType.MarkPile);
	public CardPile drawPile = new CardPile(ECardPileType.DrawPile);
	public CardPile discardPile = new CardPile(ECardPileType.DiscardPile);
	public CardPile trashPile = new CardPile(ECardPileType.TrashPile);
	public CardPile threatenPile = new CardPile(ECardPileType.ThreatenPile);
	public Map<Integer, CardSlot> cardSlotMap = new HashMap<>();
	public int actionPoint = 0;

	public int handPileMaxSize = ConstGame.HAND_PILE_MAX_SIZE;
	public CardPile drawnCardsInTurnStart = new CardPile(); // 回合开始时抽到的牌
	public int turnStartDrawNumber = 3;

	public void init(DHumanInit humanInit) {
		this.token = humanInit.getControllerToken();
		this.playerName = humanInit.getPlayerName();
		this.heroPile.add(CardManager.getCard(humanInit.getHeroCard(), token));
		for (var entry : humanInit.getPileList()) {
			var name = entry.getCard();
			var number = entry.getCount();
			for (int i = 0; i < number; i++) {
				this.drawPile.add(CardManager.getCard(name, token));
			}
		}
		this.drawPile.shuffle();

		// 初始化储备位
		for (int i = 1; i <= ConstGame.SlotPileNumber; i++) {
			var cardSlot = new CardSlot();
			cardSlot.asPlanSlot = i == ConstGame.SlotPileNumber;
			cardSlot.index = i;
			cardSlotMap.put(cardSlot.index, cardSlot);
		}

		// 自带的人物技能
		addSkill(new PlanSkill());
		addSkill(new DrawCardSkill());
		addSkill(new Pass2CardSkill());
		addSkill(new TurnEndSkill());

		if (board.modes.contains(BoardMode.Threaten)) {
			addSkill(new ThreatenToAPSkill());
		}
	}

	/**
	 * 这个函数执行时，保证当前没有action正在执行中，可以直接设置一些值
	 */
	public final void onGameStart() {
		Log.game.info("player {} gameStart", token);
	}

	/**
	 * 这个函数执行时，保证当前没有action正在执行中，可以直接设置一些值
	 */
	public final void onTurnStart() {
		// 回合开始清理技能
		removeSkillIf(s -> s.duration == SkillDuration.OwnerStartTurn);
		for (var card : getAllCards()) {
			card.removeSkillIf(s -> s.duration == SkillDuration.OwnerStartTurn);
		}

		// 重置技能计数
		for (var skill : getSkillList()) {
			skill.triggerTimesLimit = skill.triggerTimesLimitSetValue;
		}
		for (var card : getAllCards()) {
			for (var skill : card.getSkillList()) {
				skill.triggerTimesLimit = skill.triggerTimesLimitSetValue;
			}
		}

		// 计算手牌数目
		if (board.turnCount == 1 && this == board.starter) {
			// 先手第一回合1个行动，少抽1，并且不放置威慑
			addLast(new RestoreActionNumberAction(this, 1));
			if (board.modes.contains(BoardMode.Threaten)) {
				turnStartDrawNumber = 2;
			} else {
				turnStartDrawNumber = 1;
			}
		} else {
			addLast(new RestoreActionNumberAction(this, 2));
			if (board.modes.contains(BoardMode.Threaten)) {
				turnStartDrawNumber = 3;
			} else {
				turnStartDrawNumber = 2;
			}
		}
		// 如果被梦主的梦妖骚扰了
		if (getOpponent().isHero(DreamBuilder.class) && !getAllHarassCard().filter(Cat.class).isEmpty()) {
			addLast(new DreamBuilderLandAction(getOpponent()));
		}
		addLast(new DrawCardAction(this, () -> turnStartDrawNumber));

		if (GameUtils.isAI(this) && LocalData.instance().AILevel == 3) {
			addLast(new GainAPAction(this, 1));
			addLast(new DrawCardAction(this, 2));
		}

		Log.game.info("player {} turnStart", token);
		board.gameLog(this, EGameLogType.Turn, board.uiString.strs[4]);
	}

	public final void enterTurn() {
		// 如果被追风鸟骚扰
		for (var slot : this.cardSlotMap.values()) {
			if (!slot.getPile(ESlotType.Harass).isEmpty()) {
				var bird = slot.getPile(ESlotType.Harass).getAny();
				if (bird instanceof Bird) {
					addLast(new BirdHarassAction(getOpponent()));
					break;
				}
			}
		}
	}

	/**
	 * 这个函数执行时，保证当前没有action正在执行中，可以直接设置一些值
	 */
	public final void onTurnEnd() {
		// 回合结束清理buff
		removeSkillIf(s -> s.duration == SkillDuration.OwnerEndTurn);
		for (var card : getAllCards()) {
			card.removeSkillIf(s -> s.duration == SkillDuration.OwnerEndTurn);
		}

		// 将所有储备准备完成
		for (var slot : cardSlotMap.values()) {
			slot.ready = true;
		}
		board.modifyCard(getAllCardInSlot());

		// 先手第一回合不放置威慑
		if (board.turnCount == 1 && this == board.starter) {

		} else {
			if (board.modes.contains(BoardMode.Threaten)) {
				addLast(new SaveThreatenAction(this));
			}
		}

		// 弃掉多余的手牌
		addLast(new DiscardHandToMaxAction(this));

		Log.game.info("player {} turnEnd", token);
		board.gameLog(this, EGameLogType.Turn, board.uiString.strs[5]);
	}

	public boolean removeCard(Card card) {
		if (markPile.remove(card)) {
			return true;
		}
		if (drawPile.remove(card)) {
			return true;
		}
		if (discardPile.remove(card)) {
			return true;
		}
		if (trashPile.remove(card)) {
			return true;
		}
		if (handPile.remove(card)) {
			return true;
		}
		if (threatenPile.remove(card)) {
			return true;
		}
		if (heroPile.remove(card)) {
			return true;
		}
		for (var slot : cardSlotMap.values()) {
			if (slot.removeCard(card)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取所有卡牌
	 */
	public CardPile getAllCards() {
		CardPile cardPile = new CardPile();
		cardPile.addAll(heroPile);
		cardPile.addAll(markPile);
		cardPile.addAll(drawPile);
		cardPile.addAll(discardPile);
		cardPile.addAll(trashPile);
		cardPile.addAll(handPile);
		cardPile.addAll(threatenPile);
		for (var slot : cardSlotMap.values()) {
			cardPile.addAll(slot.getAllCards());
		}
		return cardPile;
	}

	/**
	 * 获取除了储备位之外的牌
	 */
	public List<CardPile> getNormalPiles() {
		var list = new ArrayList<CardPile>();
		list.add(heroPile);
		list.add(markPile);
		list.add(drawPile);
		list.add(discardPile);
		list.add(trashPile);
		list.add(handPile);
		list.add(threatenPile);
		return list;
	}

	public CardPile getAllCardInSlot() {
		CardPile cardPile = new CardPile();
		for (var slot : cardSlotMap.values()) {
			cardPile.addAll(slot.getAllCards());
		}
		return cardPile;
	}

	/**
	 * 获取所有空的slot
	 */
	public List<CardSlot> getEmptySlots(boolean exceptPlanSlot) {
		List<CardSlot> cardSlots = new ArrayList<>();
		for (var cardSlot : cardSlotMap.values()) {
			if (cardSlot.asPlanSlot && exceptPlanSlot) {
				continue;
			}
			if (cardSlot.getAllCards().isEmpty()) {
				cardSlots.add(cardSlot);
			}
		}
		return cardSlots;
	}

	/**
	 * 获取所有具有储备的slot
	 * 
	 * @param needReady       仅取储备完成的
	 * @param exceptPlanSlot  排除计划区
	 * @param opponentVisible 仅取对手可见的
	 * @return
	 */
//	public List<CardSlot> getStoreSlots(boolean needReady, boolean exceptPlanSlot, boolean opponentVisible) {
//		List<CardSlot> cardSlots = new ArrayList<>();
//		for (var cardSlot : cardSlotMap.values()) {
//			if (needReady && !cardSlot.ready) {
//				continue;
//			}
//			if (exceptPlanSlot && cardSlot.asPlanSlot) {
//				continue;
//			}
//			if (!cardSlot.getPile(ESlotType.Store).isEmpty()) {
//				cardSlots.add(cardSlot);
//				continue;
//			}
//			if (!opponentVisible && !cardSlot.getPile(ESlotType.Plan).isEmpty()) {
//				cardSlots.add(cardSlot);
//			}
//		}
//		return cardSlots;
//	}

	public CardSlot getPlanSlot() {
		for (var slot : cardSlotMap.values()) {
			if (slot.asPlanSlot) {
				return slot;
			}
		}
		return null;
	}

	public Card getCard(long id) {
		for (var card : getAllCards()) {
			if (card.id == id) {
				return card;
			}
		}
		return null;
	}

	/**
	 * 获取所有封禁牌
	 * 
	 * @param notHarassed 仅限没有被骚扰的
	 * @return
	 */
	public CardPile getAllSealCard(boolean notHarassed) {
		CardPile cardPile = new CardPile();
		for (var slot : cardSlotMap.values()) {
			if (notHarassed && !slot.getPile(ESlotType.Harass).isEmpty()) {
				continue;
			}
			cardPile.addAll(slot.getPile(ESlotType.Seal));
		}
		return cardPile;
	}

	public CardPile getAllHarassCard() {
		CardPile cardPile = new CardPile();
		for (var slot : cardSlotMap.values()) {
			cardPile.addAll(slot.getPile(ESlotType.Harass));
		}
		// 抽牌堆里也有
		cardPile.addAll(drawPile.filter(c -> !c.oriHumanToken.equals(token)));
		return cardPile;
	}

	public CardPile getAllUnSealableCard() {
		CardPile cardPile = getAllSealCard(true);
		cardPile.addAll(getAllHarassCard());
		return cardPile;
	}

	/**
	 * 获取所有可以利用的储备
	 * 
	 * @param forceStore          必须为储备牌，不能是行动牌
	 * @param onlyReady           必须是已经准备好的牌
	 * @param exceptPlan          排除计划区中的牌
	 * @param exceptHand          排除手牌
	 * @param onlyOpponentVisible 仅取对手可见的
	 * @return
	 */
	public CardPile getAllStoreCards(boolean forceStore, boolean onlyReady, boolean exceptPlan, boolean exceptHand,
			boolean onlyOpponentVisible) {
		CardPile cardPile = new CardPile();

		// 储备位上的储备牌
		cardPile.addAll(getAllStoreInSlot(forceStore, onlyReady, exceptPlan, onlyOpponentVisible));

		if (!exceptHand && !onlyOpponentVisible) {
			// 手牌中
			for (var card : handPile) {
				if (card.getSkill(ActAsStoreSkill.class) != null) {
					cardPile.add(card);
				}
			}
		}
		// 朱雀使者技能
		if (isHero(RedPhoenix.class)) {
			cardPile.addAll(markPile);
		}
		// 白虎使者技能
		if (isHero(WhiteTiger.class)) {
			cardPile.addAll(markPile);
		}
		return cardPile;
	}

	/**
	 * 获取所有储备位上的储备
	 */
	public CardPile getAllStoreInSlot(boolean forceStore, boolean onlyReady, boolean exceptPlan,
			boolean onlyOpponentVisible) {
		CardPile cardPile = new CardPile();
		for (var slot : cardSlotMap.values()) {
			if (onlyReady && !slot.ready) {
				continue;
			}
			if (exceptPlan && slot.asPlanSlot) {
				continue;
			}
			cardPile.addAll(slot.getPile(ESlotType.Store));
			if (!onlyOpponentVisible) {
				cardPile.addAll(slot.getPile(ESlotType.Plan));
			}
		}
		if (forceStore) {
			cardPile.removeAll(card -> card.mainType != CardType.STORE);
		}
		return cardPile;
	}

	public CardSlot getCardSlotByCard(Card card) {
		for (var slot : cardSlotMap.values()) {
			if (slot.getAllCards().contains(card)) {
				return slot;
			}
		}
		return null;
	}

	public boolean isHero(Class<? extends HeroCard> clazz) {
		for (var card : heroPile) {
			if (clazz.isInstance(card)) {
				return true;
			}
		}
		return false;
	}

	public Skill getHeroSkill(Class<? extends Skill> clazz) {
		for (var card : heroPile) {
			var skill = card.getSkill(clazz);
			if (skill != null) {
				return skill;
			}
		}
		return null;
	}

	public Card getHeroCard() {
		return heroPile.get(0);
	}

	// 获取对手
	public final Human getOpponent() {
		return board.getOpponent(this);
	}

	public final void addLast(Action action) {
		board.getActionQueue().addLast(action);
	}

	public final void addFirst(Action action) {
		board.getActionQueue().addFirst(action);
	}

	public final void sendMessage(DSyncBase msg) {
		msgCacheQueue.add(msg);
	}

	public final void addHumanOper(HumanOper humanOper) {
		humanOper.human = this;
		humanOperMessageHandler.addListener(humanOper);
		humanOpers.add(humanOper);
		humanOper.onAttach();
		Log.game.info("{} onAttach for {}", humanOper.getClass().getSimpleName(), token);
	}

	public final void removeHumanOper(HumanOper humanOper) {
		humanOper.onDetach();
		humanOperMessageHandler.removeListener(humanOper);
		humanOpers.remove(humanOper);
		Log.game.info("{} onDetach for {}", humanOper.getClass().getSimpleName(), token);
	}
}
