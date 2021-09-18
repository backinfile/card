package com.backinfile.card.model.actions;

import com.backinfile.card.model.Board.BoardState;

// 改变Board状态的Action,这里不应该加其他东西
public class ChangeBoardStateAction extends TriggerOnceAction {
	private BoardState state;

	public ChangeBoardStateAction(BoardState state) {
		this.state = state;
	}

	@Override
	public void run() {
		board.state = state;
	}

}
