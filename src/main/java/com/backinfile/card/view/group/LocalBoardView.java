package com.backinfile.card.view.group;

import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.server.local.LocalGameClient;
import com.backinfile.card.view.stage.GameStage;

// 本地BoardView
public class LocalBoardView extends BaseView {

	public LocalGameClient gameClient;
	public CardGroupView cardGroupView;

	public LocalBoardView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		initView();
	}

	private void initView() {
		cardGroupView = new CardGroupView(gameStage, getWidth(), getHeight());
	}

	public void startGame() {
		show();
		gameClient = new LocalGameClient(gameStage);
		gameClient.startGame(GameUtils.getDefaultBoardInit());
	}

}
