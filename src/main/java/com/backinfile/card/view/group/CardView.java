package com.backinfile.card.view.group;

import com.backinfile.card.model.CardInfo;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CardView extends Group {

	private CardInfo cardInfo;
	private int zIndex = 0;
	private Image mainImage;
	private Image borderImage;
	private Image maskImage;

	public CardView() {
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
}
