package com.backinfile.card.view.stage;

import com.backinfile.card.view.group.LocalBoardView;
import com.backinfile.card.view.group.TestView;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {

	public LocalBoardView boardView;
	public TestView testView;

	public GameStage(Viewport viewport) {
		super(viewport);

		initLogic();
		initView();
	}

	private void initLogic() {
	}

	private void initView() {
		boardView = new LocalBoardView(this, getWidth(), getHeight());
		testView = new TestView(this, getWidth(), getHeight());
		addActor(boardView);
		addActor(testView);
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
