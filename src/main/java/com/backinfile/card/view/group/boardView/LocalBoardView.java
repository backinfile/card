package com.backinfile.card.view.group.boardView;

import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.server.local.LocalGameClient;
import com.backinfile.card.view.actor.PositionLocator;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.group.CardGroupView;
import com.backinfile.card.view.group.MulPileView;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.utils.Align;

// 本地BoardView
public class LocalBoardView extends BaseView {

	private BoardBackgroundView backgroundView;
	public LocalGameClient gameClient;
	public CardGroupView cardGroupView;
	public BoardUIView boardUIView;
	public MulPileView mulPileView;
	public BoardLogScrollView boardLogScrollView;

	public LocalBoardView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		initView();
	}

	private void initView() {
		backgroundView = new BoardBackgroundView(gameStage, getWidth(), getHeight());
		addActor(backgroundView);

		mulPileView = new MulPileView(gameStage, getWidth(), getHeight());
		addActor(mulPileView);

		cardGroupView = new CardGroupView(gameStage, getWidth(), getHeight());
		addActor(cardGroupView);

		{
			PositionLocator locator = new PositionLocator();
			locator.setSize(16, 16);
			locator.setPosition(100, getHeight() / 2, Align.center);
			addActor(locator);
		}

		boardLogScrollView = new BoardLogScrollView(gameStage, getWidth(), getHeight());
		addActor(boardLogScrollView);

		boardUIView = new BoardUIView(gameStage, getWidth(), getHeight());
		addActor(boardUIView);

	}

	public void startGame() {
		show();
		gameClient = new LocalGameClient(gameStage);
		gameClient.startGame(GameUtils.getDefaultBoardInit());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		gameClient.pulse();
	}

}
