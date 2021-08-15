package com.backinfile.card.view.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ShowCardView extends Group {
	private Image mainCard;

	public ShowCardView(float width, float height) {
		setSize(width, height);

		mainCard = new Image();
	}
}
