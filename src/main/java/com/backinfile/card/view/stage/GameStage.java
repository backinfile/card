package com.backinfile.card.view.stage;

import com.backinfile.card.manager.Res;
import com.backinfile.card.view.group.LocalBoardView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {
	public LocalBoardView boardView;

	public GameStage(Viewport viewport) {
		super(viewport);

		initView();
		initLogic();
	}

	private void initLogic() {

		addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if (keycode == Keys.ESCAPE) {
					Gdx.app.exit();
					return true;
				}
				return super.keyUp(event, keycode);
			}
		});
	}

	private void initView() {
		float offsetRate = Res.BoardOffsetRate;
		boardView = new LocalBoardView(this, getWidth() * (1 - offsetRate), getHeight() * (1 - offsetRate));
		boardView.setPosition(getWidth() * offsetRate / 2, getHeight() * offsetRate);
		addActor(boardView);

		boardView.startGame();
	}

	@Override
	public void draw() {
		super.draw();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

}
