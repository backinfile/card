package com.backinfile.card.view.stage;

import java.util.Arrays;

import com.backinfile.card.manager.CardManager;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.manager.Res;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class GameScreen extends ScreenAdapter {
	public static GameScreen Instance;

	public GameStage gameStage;
	public IntroStage introStage;

	private Stage viewStage;

	public GameScreen() {
		Instance = this;
		Res.init();
		CardManager.init();
		LocalData.load();

		introStage = new IntroStage(new ScalingViewport(Scaling.none, Res.STAGE_WIDTH, Res.STAGE_HEIGHT));
		gameStage = new GameStage(new ScalingViewport(Scaling.none, Res.STAGE_WIDTH, Res.STAGE_HEIGHT));

		for (var stage : Arrays.asList(introStage, gameStage)) {
			stage.addListener(new InputListener() {
				@Override
				public boolean keyUp(InputEvent event, int keycode) {
					switch (keycode) {
					case Keys.ESCAPE:
						Gdx.app.exit();
						return true;
					case Keys.R:
						showIntro();
						return true;
					default:
						break;
					}
					return super.keyUp(event, keycode);
				}
			});
		}

		startGame();
		showIntro();
	}

	public void showIntro() {
		viewStage = introStage;
		gameStage.boardView.clearGame();
		Gdx.input.setInputProcessor(viewStage);
	}

	public void startGame() {
		viewStage = gameStage;
		gameStage.boardView.startGame();
		Gdx.input.setInputProcessor(viewStage);
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
