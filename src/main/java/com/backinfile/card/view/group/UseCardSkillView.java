package com.backinfile.card.view.group;

import com.backinfile.card.Res;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class UseCardSkillView extends BaseView {
	private Image maskImage;
	private Image mainCardImage;

	public UseCardSkillView(float width, float height) {
		super(width, height);

		mainCardImage = new Image();
		maskImage = new Image(Res.TEX_HALF_BLACK);
		maskImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		addActor(maskImage);
		addActor(mainCardImage);

		hide();
	}

	public void show(LocalImagePathString imagePathString) {
		mainCardImage.setDrawable(Res.getTexture(imagePathString));
		mainCardImage.setSize(Res.CARD_WIDTH_L, Res.CARD_HEIGHT_L);
		mainCardImage.setPosition(getWidth() / 3 - mainCardImage.getWidth() / 2,
				getHeight() / 2 - mainCardImage.getHeight() / 2);
		this.setVisible(true);
	}

}
