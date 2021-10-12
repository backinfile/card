package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.model.Human;

public class DragonKnightStoreDragonAction extends TriggerOnceAction {
	public DragonKnightStoreDragonAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		board.gameLog(human, EGameLogType.Skill, actionString.tips[0]);
		for (var slot : human.getOpponent().cardSlotMap.values()) {
			if (!slot.getPile(ESlotType.Store).isEmpty()) {
				slot.ready = false;
			}
		}
		board.modifyCard(human.getOpponent().getAllCardInSlot());
		addFirst(new GainAPAction(human, 1));
	}

}
