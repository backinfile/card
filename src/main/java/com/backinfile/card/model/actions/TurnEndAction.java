package com.backinfile.card.model.actions;

import com.backinfile.card.model.Human;
import com.backinfile.card.model.Board.BoardState;

// 主动回合结束事件
public class TurnEndAction extends TriggerOnceAction {
	public TurnEndAction(Human human) {
		this.human = human;
	}

	@Override
	public void run() {
		human.onTurnEnd();
		addLast(new ChangeBoardStateAction(BoardState.TurnEnd));
	}
}
