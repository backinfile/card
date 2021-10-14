package com.backinfile.card.view.stage;

import com.backinfile.card.manager.CardManager;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.manager.Res;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen extends ScreenAdapter {
	public GameStage gameStage;
	public IntroStage introStage;

	private Stage viewStage;

	public GameScreen() {
		Res.init();
		CardManager.init();
		LocalData.load();

		introStage = new IntroStage(new ExtendViewport(Res.STAGE_WIDTH, Res.STAGE_HEIGHT));
		gameStage = new GameStage(new ExtendViewport(Res.STAGE_WIDTH, Res.STAGE_HEIGHT));

		startGame();
	}

	public void showIntro() {
		viewStage = introStage;
		gameStage.boardView.clearGame();
	}

	public void startGame() {
		viewStage = gameStage;
		gameStage.boardView.startGame();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (viewStage != null) {
			viewStage.act(delta);
			viewStage.draw();
		}
	}

	@Override
	public void dispose() {
		Res.dispose();
	}
}
