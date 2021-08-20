package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class UIView extends BaseView {

	private Label label;

	public UIView(float width, float height) {
		super(width, height);

		LabelStyle labelStyle = new LabelStyle(Res.DefaultFont, Color.WHITE);
		label = new Label("[label]", labelStyle);
		label.setAlignment(Align.center);
		label.setPosition(getWidth() / 2f, getHeight() / 2f, Align.center);
		addActor(label);
	}
}
