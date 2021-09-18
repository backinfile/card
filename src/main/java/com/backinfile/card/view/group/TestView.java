package com.backinfile.card.view.group;

import com.backinfile.card.model.LocalString;
import com.backinfile.card.view.actor.BoardButton;
import com.backinfile.card.view.actor.PositionLocator;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.utils.Align;

// 测试用
public class TestView extends BaseView {

	public TestView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		BoardButton boardButton = new BoardButton();
		boardButton.setSize(100, 40);
		boardButton.setPosition(width / 2, height / 2, Align.center);
//		boardButton.setVisible(false);
		addActor(boardButton);

		PositionLocator locator = new PositionLocator();
		locator.setSize(16, 16);
		locator.setPosition(100, height / 2, Align.center);
		addActor(locator);

		CardView cardView = new CardView();
		cardView.setCardString(LocalString.getCardString("attack"));
		cardView.setFlipOver(true);
		cardView.setVisible(false);
//		addActor(cardView);
	}

}
