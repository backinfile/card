package com.backinfile.card.view.group;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.server.local.LocalGameClient;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.scenes.scene2d.Group;

// 本地BoardView
public class LocalBoardView extends BaseView {

	public LocalGameClient gameClient;
	public CardGroupView cardGroupView;
	private BoardBackgroundView backgroundView;
	private Group pileViewGroup;

	public LocalBoardView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		initView();
	}

	private void initView() {
		backgroundView = new BoardBackgroundView(gameStage, getWidth(), getHeight());
		addActor(backgroundView);

		{
			pileViewGroup = new Group();
			addActor(pileViewGroup);
			pileViewGroup.addActor(
					new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DrawPile, PilePosition.Self));
			pileViewGroup.addActor(
					new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DiscardPile, PilePosition.Self));
			pileViewGroup.addActor(
					new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DrawPile, PilePosition.Opponent));
			pileViewGroup.addActor(
					new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DiscardPile, PilePosition.Opponent));
		}

		cardGroupView = new CardGroupView(gameStage, getWidth(), getHeight());
//		addActor(cardGroupView);
	}

	public void startGame() {
		show();
		gameClient = new LocalGameClient(gameStage);
		gameClient.startGame(GameUtils.getDefaultBoardInit());
	}

}
