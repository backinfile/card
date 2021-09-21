package com.backinfile.card.view.actor;

import com.backinfile.card.manager.Res;
import com.backinfile.support.func.Action0;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ActionButton extends TextButton {
	private Action0 callback = null;
	private GlyphLayout layout;

	public ActionButton() {
		super("[TEXT]", new TextButtonStyle(Res.TEX_GRAY, Res.TEX_DARK_GRAY, null, Res.DefaultFont));

		layout = new GlyphLayout();

		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (callback != null) {
					callback.invoke();
				}
			}
		});
	}

	private float xCache;
	private float yCache;
	private int alignCache;

	@Override
	public void setPosition(float x, float y, int alignment) {
		super.setPosition(x, y, alignment);
		xCache = x;
		yCache = y;
		alignCache = alignment;
	}

	public void set(String text, Action0 callback) {
		setText(text);
		this.callback = callback;

		layout.setText(getLabel().getStyle().font, text);
		setSize(layout.width * 1.1f, layout.height * 2f);
		super.setPosition(xCache, yCache, alignCache);
	}

}