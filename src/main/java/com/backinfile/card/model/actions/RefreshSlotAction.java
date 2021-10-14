package com.backinfile.card.model.actions;

import java.util.HashSet;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.CardPile;
import com.backinfile.card.model.CardSlot;
import com.backinfile.card.model.cards.Chap2HeroCard.WhiteTiger;
import com.backinfile.card.server.humanOper.SelectCardOper;

// 使用了储备后，需要手动刷新一下储备位
public class RefreshSlotAction extends WaitAction {

	private HashSet<CardSlot> ignored = new HashSet<>();

	@Override
	public void init() {
		checkWhiteTigerSkill();
	}

	private void checkWhiteTigerSkill() {
		// 检查白虎使者技能
		for (var human : board.humans) {
			if (!human.isHero(WhiteTiger.class) || human.markPile.isEmpty()) {
				continue;
			}
			for (var slot : human.cardSlotMap.values()) {
				if (ignored.contains(slot)) {
					continue;
				}
				if (!slot.getPile(ESlotType.Ride).isEmpty() && slot.getPile(ESlotType.Store).isEmpty()) {
					ignored.add(slot);
					board.gameLog(human, EGameLogType.Skill, actionString.tips[1]);
					var humanOper = new SelectCardOper(human.markPile, actionString.tips[0], 0, 1);
					humanOper.setDetachCallback(() -> {
						if (!humanOper.getSelectedPile().isEmpty()) {
							addFirst(
									new WhiteTigerRefreshSlotAction(human, slot, humanOper.getSelectedPile().getAny()));
							setDone();
						} else {
							discardAll();
						}
					});
					human.addHumanOper(humanOper);
					return;
				}
			}
		}

		// 没有触发技能
		discardAll();
	}

	private void discardAll() {
		// 如果没有储备牌，则弃置骑乘牌
		CardPile toDiscard = new CardPile();
		for (var human : board.humans) {
			for (var slot : human.cardSlotMap.values()) {
				if (slot.getPile(ESlotType.Store).isEmpty()) {
					toDiscard.addAll(slot.getPile(ESlotType.Ride));
					this.human = human;
				}
			}
		}
		if (!toDiscard.isEmpty()) {
			addFirst(new DiscardCardAction(human, toDiscard));
		}
		setDone();
	}

}
