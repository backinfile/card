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
import com.backinfile.card.model.skills.OutTurnSkillCancelSkill;
import com.backinfile.card.model.skills.Pass2CardSkill;
import com.backinfile.card.model.skills.PlanSkill;
import com.backinfile.card.model.skills.ThreatenToAPSkill;
import com.backinfile.card.model.skills.TurnEndSkill;
import com.backinfile.card.server.humanOper.HumanOper;
import com.backinfile.dSync.model.DSyncBaseHandler.DSyncBase;
import com.backinfile.support.Log;

public class Human extends SkillCaster {
	// ????????????
	public String token;
	public String playerName;

	// ????????????
	public Board board;

	// Human???????????????????????????????????????
	public LinkedList<DSyncBase> msgCacheQueue = new LinkedList<>();

	// ??????????????????
	public List<HumanOper> humanOpers = new ArrayList<>();
	public GameMessageHandler humanOperMessageHandler = new GameMessageHandler();

	// ???????????????
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
	public CardPile drawnCardsInTurnStart = new CardPile(); // ???????????????????????????
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

		// ??????????????????
		for (int i = 1; i <= ConstGame.SlotPileNumber; i++) {
			var cardSlot = new CardSlot();
			cardSlot.asPlanSlot = i == ConstGame.SlotPileNumber;
			cardSlot.index = i;
			cardSlotMap.put(cardSlot.index, cardSlot);
		}

		// ?????????????????????
		addSkill(new PlanSkill());
		addSkill(new DrawCardSkill());
		addSkill(new Pass2CardSkill());
		addSkill(new TurnEndSkill());
		addSkill(new OutTurnSkillCancelSkill());

		if (board.modes.contains(BoardMode.Threaten)) {
			addSkill(new ThreatenToAPSkill());
		}
	}

	/**
	 * ??????????????????????????????????????????action?????????????????????????????????????????????
	 */
	public final void onGameStart() {
		Log.game.info("player {} gameStart", token);
	}

	/**
	 * ??????????????????????????????????????????action?????????????????????????????????????????????
	 */
	public final void onTurnStart() {
		// ????????????????????????
		removeSkillIf(s -> s.duration == SkillDuration.OwnerStartTurn);
		for (var card : getAllCards()) {
			card.removeSkillIf(s -> s.duration == SkillDuration.OwnerStartTurn);
		}

		// ??????????????????
		for (var skill : getSkillList()) {
			skill.triggerTimesLimit = skill.triggerTimesLimitSetValue;
		}
		for (var card : getAllCards()) {
			for (var skill : card.getSkillList()) {
				skill.triggerTimesLimit = skill.triggerTimesLimitSetValue;
			}
		}

		// ??????????????????
		if (board.turnCount == 1 && this == board.starter) {
			// ??????????????????1??????????????????1????????????????????????
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
		// ?????????????????????????????????
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
		// ????????????????????????
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
	 * ??????????????????????????????????????????action?????????????????????????????????????????????
	 */
	public final void onTurnEnd() {
		// ??????????????????buff
		removeSkillIf(s -> s.duration == SkillDuration.OwnerEndTurn);
		for (var card : getAllCards()) {
			card.removeSkillIf(s -> s.duration == SkillDuration.OwnerEndTurn);
		}

		actionPoint = 0; // ???????????????

		// ???????????????????????????
		for (var slot : cardSlotMap.values()) {
			slot.ready = true;
		}
		board.modifyCard(getAllCardInSlot());

		// ?????????????????????????????????
		if (board.turnCount == 1 && this == board.starter) {

		} else {
			if (board.modes.contains(BoardMode.Threaten)) {
				addLast(new SaveThreatenAction(this));
			}
		}

		// ?????????????????????
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
	 * ??????????????????
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
	 * ?????????????????????????????????
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
	 * ??????????????????slot
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
	 * ???????????????????????????slot
	 * 
	 * @param needReady       ?????????????????????
	 * @param exceptPlanSlot  ???????????????
	 * @param opponentVisible ?????????????????????
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
	 * ?????????????????????
	 * 
	 * @param notHarassed ????????????????????????
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
		// ??????????????????
		cardPile.addAll(drawPile.filter(c -> !c.oriHumanToken.equals(token)));
		return cardPile;
	}

	public CardPile getAllUnSealableCard() {
		CardPile cardPile = getAllSealCard(true);
		cardPile.addAll(getAllHarassCard());
		return cardPile;
	}

	/**
	 * ?????????????????????????????????
	 * 
	 * @param forceStore          ???????????????????????????????????????
	 * @param onlyReady           ??????????????????????????????
	 * @param exceptPlan          ????????????????????????
	 * @param exceptHand          ????????????
	 * @param onlyOpponentVisible ?????????????????????
	 * @return
	 */
	public CardPile getAllStoreCards(boolean forceStore, boolean onlyReady, boolean exceptPlan, boolean exceptHand,
			boolean onlyOpponentVisible) {
		CardPile cardPile = new CardPile();

		// ????????????????????????
		cardPile.addAll(getAllStoreInSlot(forceStore, onlyReady, exceptPlan, onlyOpponentVisible));

		if (!exceptHand && !onlyOpponentVisible) {
			// ?????????
			for (var card : handPile) {
				if (card.getSkill(ActAsStoreSkill.class) != null) {
					cardPile.add(card);
				}
			}
		}
		// ??????????????????
		if (isHero(RedPhoenix.class)) {
			cardPile.addAll(markPile);
		}
		// ??????????????????
		if (isHero(WhiteTiger.class)) {
			cardPile.addAll(markPile);
		}
		return cardPile;
	}

	/**
	 * ?????????????????????????????????
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

	// ????????????
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
