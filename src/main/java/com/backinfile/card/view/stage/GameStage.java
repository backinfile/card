package com.backinfile.card.view.stage;

import java.util.LinkedList;

import com.backinfile.card.manager.Res;
import com.backinfile.card.view.group.boardView.BoardButtonsView;
import com.backinfile.card.view.group.boardView.LocalBoardView;
import com.backinfile.card.view.group.boardView.ShowCardView;
import com.backinfile.card.view.viewActions.ViewAction;
import com.backinfile.support.Log;
import com.backinfile.support.Time2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {
	public LocalBoardView boardView;
	public ShowCardView showCardView;
	public BoardButtonsView buttonsView;

	private LinkedList<ViewAction> viewActionQueue = new LinkedList<>();
	private ViewAction curViewAction = null;

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

		showCardView = new ShowCardView(this, getWidth(), getHeight());
		addActor(showCardView);

		buttonsView = new BoardButtonsView(this, getWidth(), getHeight());
		addActor(buttonsView);

		boardView.startGame();
	}

	@Override
	public void draw() {
		super.draw();

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		pulseViewActionQueue();
	}

	public void addViewAction(ViewAction viewAction) {
		viewActionQueue.add(viewAction);
	}

	private void pulseViewActionQueue() {
		if (curViewAction != null) {
			if (curViewAction.isDone()) {
				curViewAction.dispose();
				curViewAction = null;
			} else {
				curViewAction.pulse();
			}
			return;
		}
		if (viewActionQueue.isEmpty()) {
			return;
		}
		curViewAction = viewActionQueue.pollFirst();
		curViewAction.gameStage = this;
		curViewAction.init();
		curViewAction.begin();
		curViewAction.pulse();
		Log.game.info("viewAction {} begin", curViewAction.getClass().getSimpleName());
	}
}
