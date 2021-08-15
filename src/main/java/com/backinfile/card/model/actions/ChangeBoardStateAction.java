package com.backinfile.card.model.actions;

import com.backinfile.card.model.Board.BoardState;

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
