package com.backinfile.card.model.actions;

import com.backinfile.card.model.Board.BoardState;

//回合开始清理行动，这里不要再加行动了
public class TurnStartDoAction extends TriggerOnceAction {
	@Override
	public void run() {
		board.state = BoardState.InTurn;
	}
}
