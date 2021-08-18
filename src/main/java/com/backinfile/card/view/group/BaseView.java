package com.backinfile.card.view.group;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class BaseView extends Group {

	public BaseView(float width, float height) {
		setSize(width, height);
	}

	public final void show() {
		setVisible(true);
	}

	public final void hide() {
		setVisible(false);
	}
}
