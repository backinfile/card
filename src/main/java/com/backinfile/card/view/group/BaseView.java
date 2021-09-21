package com.backinfile.card.view.group;

import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class BaseView extends Group {
	protected GameStage gameStage;

	public BaseView(GameStage gameStage, float width, float height) {
		this.gameStage = gameStage;
		setSize(width, height);
	}

	public void show() {
		setVisible(true);
	}

	public void hide() {
		setVisible(false);
	}
}
