package com.backinfile.card.view;

import com.backinfile.card.Res;
import com.backinfile.card.manager.CardManager;
import com.backinfile.card.view.stage.CardStage;
import com.backinfile.support.TimerQueue;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen extends ScreenAdapter {
	private boolean inited = false;
	private CardStage cardStage;
	private TimerQueue timerQueue;

	@Override
	public void show() {
		init();
	}

	private void init() {
		if (inited) {
			return;
		}
		inited = true;

		Res.init();
		CardManager.init();

		timerQueue = new TimerQueue();

		cardStage = new CardStage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Gdx.input.setInputProcessor(cardStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		timerQueue.update();

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
