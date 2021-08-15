package com.backinfile.card.view.actor;

import com.backinfile.card.Res;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class UIView extends Group {

	private Label label;

	public UIView(float width, float height) {

		setSize(width, height);

		LabelStyle labelStyle = new LabelStyle(Res.DefaultFont, Color.WHITE);
		label = new Label("[label]", labelStyle);
		label.setAlignment(Align.center);
		label.setPosition(getWidth(), getHeight(), Align.center);
		addActor(label);
	}
}
