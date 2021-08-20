package com.backinfile.card.view.actor;

import com.backinfile.card.manager.Res;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DefaultButton extends TextButton {

	public DefaultButton() {
		this("");
	}

	public DefaultButton(String text) {
		super("", new TextButtonStyle(Res.TEX_LIGHT_GRAY, Res.TEX_GRAY, null, Res.DefaultFont));
	}

	@Override
	public void setText(String text) {
		super.setText(text);
	}
}
