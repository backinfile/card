package com.backinfile.card.server;

import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Board;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.boards.StandaloneBoard;
import com.backinfile.card.server.proto.DBoardInit;
import com.backinfile.card.server.proto.DHumanOper;
import com.backinfile.card.server.proto.DRoom;
import com.backinfile.support.IAlive;
import com.backinfile.support.IdAllot;
import com.backinfile.support.MsgConsumer;

public abstract class GameClient extends MsgConsumer<DHumanOper> implements IAlive {
	public static GameClient Instance;
	public Board board;
	public Human human;
	public DRoom room;
	public ClietState clietState = ClietState.Normal;

	public static enum ClietState {
		Normal, Room, Game, Select, // 执行targetInfo
		Action, // 执行一项行动
	}

	public GameClient() {
		IdAllot.reset();
	}

	public void startGame(DBoardInit boardInit) {
		board = new StandaloneBoard();
		board.init(boardInit);
		human = board.getHuman(LocalData.instance().token);
		clietState = ClietState.Game;
	}

	@Override
	public void pulse() {
		board.requireFlushCardView = false;
		switch (clietState) {
		case Normal:
			break;
		case Room: {

			break;
		}
		case Game: {
			board.pulse();
			if (human != null) {
				if (human.targetInfo != null && !human.targetInfo.isSelected()) {
					clietState = ClietState.Select;
				} else if (board.getActionQueue().isEmpty() && board.curTurnHuman == human) {
					clietState = ClietState.Action;
				}
			}
			break;
		}
		case Select: {
			if (human.targetInfo.isSelected()) {
				clietState = ClietState.Game;
			}
			break;
		}
		case Action: {

			break;
		}
		}

		if (board.requireFlushCardView) {
			// TODO
		}
	}
}
