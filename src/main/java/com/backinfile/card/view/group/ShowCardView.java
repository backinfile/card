package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class ShowCardView extends BaseView {
	private Image maskImage;
	private Image mainCardImage;

	public ShowCardView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		mainCardImage = new Image();
		maskImage = new Image(Res.TEX_HALF_BLACK);
		maskImage.setSize(width, height);

		addActor(maskImage);
		addActor(mainCardImage);

		hide();
	}

	public void show(CardInfo cardInfo) {
		var cardString = LocalString.getCardString(cardInfo.info.getSn());
		show(cardString.frontImages[cardInfo.getPilePosition().ordinal()]);
	}

	public void show(LocalImagePathString imagePathString) {
		mainCardImage.setDrawable(Res.getTexture(imagePathString));
		mainCardImage.setSize(Res.CARD_HEIGHT_L, Res.CARD_HEIGHT_L);
		mainCardImage.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
		this.setVisible(true);
	}
}
