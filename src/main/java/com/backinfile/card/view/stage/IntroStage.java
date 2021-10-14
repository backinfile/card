package com.backinfile.card.view.stage;

import com.backinfile.card.manager.Res;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.Viewport;

public class IntroStage extends Stage {

	public IntroStage(Viewport viewport) {
		super(viewport);

		init();
	}

	private void init() {
		Label label = new Label("hhh", new LabelStyle(Res.DefaultFont, Color.WHITE));
		addActor(label);
	}
}
