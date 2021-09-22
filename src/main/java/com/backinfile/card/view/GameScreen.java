package com.backinfile.card.view;

import com.backinfile.card.manager.CardManager;
import com.backinfile.card.manager.Res;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.timer.TimerQueue;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class GameScreen extends ScreenAdapter {
	private boolean inited = false;
	private GameStage cardStage;
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
		cardStage = new GameStage(new ScalingViewport(Scaling.none, Res.STAGE_WIDTH, Res.STAGE_HEIGHT));
		Gdx.input.setInputProcessor(cardStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
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
