package com.backinfile.card.view.group;

import com.backinfile.card.view.actor.BoardButton;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.utils.Align;

// 测试用
public class TestView extends BaseView {

	public TestView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		BoardButton boardButton = new BoardButton();
		boardButton.setPosition(width / 2, height / 2, Align.center);
		boardButton.setSize(100, 40);
		addActor(boardButton);
	}

}
