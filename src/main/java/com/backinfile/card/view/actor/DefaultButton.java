package com.backinfile.card.view.actor;

import com.backinfile.card.Res;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DefaultButton extends TextButton {
	public static final TextButtonStyle DEFAULT_STYLE = new TextButtonStyle(Res.TEX_LIGHT_GRAY, Res.TEX_GRAY, null,
			Res.DefaultFont);

	public DefaultButton() {
		this("");
	}

	public DefaultButton(String text) {
		super("", DEFAULT_STYLE);
	}
}
