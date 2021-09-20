package com.backinfile.card.view.actor;

import com.backinfile.card.manager.Res;
import com.backinfile.support.func.Action0;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ActionButton extends TextButton {
	private Action0 callback = null;

	public ActionButton() {
		super("[TEXT]", new TextButtonStyle(Res.TEX_HALF_BLACK, null, null, Res.DefaultFont));

		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (callback != null) {
					callback.invoke();
				}
			}
		});
	}

	public void set(String text, Action0 callback) {
		setText(text);
		this.callback = callback;
	}

}