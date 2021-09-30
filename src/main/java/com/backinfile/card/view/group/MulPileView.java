package com.backinfile.card.view.group;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DPileNumber;
import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.view.group.PileView.HumanPosition;
import com.backinfile.card.view.stage.GameStage;

public class MulPileView extends BaseView {
	public List<PileView> pileViews;

	public MulPileView(GameStage gameStage, float width, float height) {
		super(gameStage, 0, 0);

		{
			pileViews = new ArrayList<>();
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DrawPile, HumanPosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DiscardPile, HumanPosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.MarkPile, HumanPosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.ThreatenPile, HumanPosition.Self));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DrawPile, HumanPosition.Opponent));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.DiscardPile, HumanPosition.Opponent));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.MarkPile, HumanPosition.Opponent));
			pileViews.add(new PileView(gameStage, width, height, ECardPileType.ThreatenPile, HumanPosition.Opponent));

			for (var pile : pileViews) {
				addActor(pile);
			}
		}
	}

	public void setPileNumber(List<DPileNumber> pileNumbers) {
		for (var pileNumber : pileNumbers) {
			for (var pileView : pileViews) {
				if (pileNumber.getPileType() == pileView.pileType) {
					HumanPosition pilePosition = pileNumber.getOpponent() ? HumanPosition.Opponent : HumanPosition.Self;
					if (pilePosition == pileView.pilePosition) {
						pileView.setPileNumber(pileNumber.getNumber());
					}
				}
			}
		}
	}
}
