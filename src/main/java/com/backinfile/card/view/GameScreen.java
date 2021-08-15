package com.backinfile.card.view;

import com.backinfile.card.Res;
import com.backinfile.card.support.ReflectionUtils;
import com.backinfile.card.view.stage.CardStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen extends ScreenAdapter {
	private CardStage cardStage;

	@Override
	public void show() {
		ReflectionUtils.initTimingMethod();
		Res.init();

		cardStage = new CardStage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
	}

	@Override
	public void render(float delta) {
		cardStage.act();
		cardStage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Res.dispose();
	}
}
