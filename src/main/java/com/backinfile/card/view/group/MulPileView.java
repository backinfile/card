package com.backinfile.card.view.group;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.stage.GameStage;

public class MulPileView extends BaseView {
	public List<PileView> pileViews;

	public MulPileView(GameStage gameStage, float width, float height) {
		super(gameStage, 0, 0);

		{
			pileViews = new ArrayList<>();
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DrawPile, PilePosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DiscardPile, PilePosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.MarkPile, PilePosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.ThreatenPile, PilePosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DrawPile, PilePosition.Opponent));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DiscardPile, PilePosition.Opponent));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.MarkPile, PilePosition.Opponent));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.ThreatenPile, PilePosition.Opponent));

			for (var pile : pileViews) {
				addActor(pile);
			}
		}
	}
}
