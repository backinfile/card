package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalUIString;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class BoardBackgroundView extends BaseView {
	public Image background0;
	public Image background1;

	public BoardBackgroundView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		init();
	}

	private void init() {
		LocalUIString uiString = LocalString.getUIString("board_background");
		var texture = Res.getTexture(uiString.images[0]);
		var finalHeight = getHeight() / 2;
		var finalWidth = finalHeight * (texture.getMinWidth() / texture.getMinHeight());

		float darkRate = 0.3f;

		background0 = new Image(texture);
		background0.setSize(finalWidth, finalHeight);
		background0.setPosition(getWidth() / 2, getHeight() / 4, Align.center);
		background0.getColor().mul(darkRate);
		addActor(background0);

		background1 = new Image(texture);
		background1.setSize(finalWidth, finalHeight);
		background1.setOrigin(Align.center);
		background1.setPosition(getWidth() / 2, getHeight() * 3 / 4, Align.center);
		background1.getColor().mul(darkRate);
		background1.setRotation(180);
		addActor(background1);
	}

}
