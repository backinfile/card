package com.backinfile.card.view.group.boardView;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.server.local.LocalGameClient;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.group.CardGroupView;
import com.backinfile.card.view.group.PileView;
import com.backinfile.card.view.group.TestView;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.stage.GameStage;

// 本地BoardView
public class LocalBoardView extends BaseView {

	public LocalGameClient gameClient;
	public CardGroupView cardGroupView;
	private BoardBackgroundView backgroundView;
	private BoardUIView boardUIView;
	public ShowCardView showCardView;
	public List<PileView> pileViews;

	private TestView testView;

	public LocalBoardView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);
		initView();
	}

	private void initView() {
		backgroundView = new BoardBackgroundView(gameStage, getWidth(), getHeight());
		addActor(backgroundView);

		{
			pileViews = new ArrayList<>();
			pileViews.add(new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DrawPile, PilePosition.Self));
			pileViews.add(
					new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DiscardPile, PilePosition.Self));
			pileViews.add(
					new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DrawPile, PilePosition.Opponent));
			pileViews.add(
					new PileView(gameStage, getWidth(), getHeight(), ECardPileType.DiscardPile, PilePosition.Opponent));
			for (var pile : pileViews) {
				addActor(pile);
			}
		}

		cardGroupView = new CardGroupView(gameStage, getWidth(), getHeight());
		addActor(cardGroupView);

		boardUIView = new BoardUIView(gameStage, getWidth(), getHeight());
		addActor(boardUIView);

		showCardView = new ShowCardView(gameStage, getWidth(), getHeight());
		addActor(showCardView);

		testView = new TestView(gameStage, getWidth(), getHeight());
//		addActor(testView);
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
