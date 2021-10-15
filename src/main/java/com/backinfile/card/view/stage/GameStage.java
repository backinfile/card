package com.backinfile.card.view.stage;

import java.util.LinkedList;

import com.backinfile.card.manager.Res;
import com.backinfile.card.view.group.boardView.BoardButtonsView;
import com.backinfile.card.view.group.boardView.LocalBoardView;
import com.backinfile.card.view.group.boardView.ShowCardListView;
import com.backinfile.card.view.group.boardView.ShowCardView;
import com.backinfile.card.view.viewActions.ViewAction;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {
	public LocalBoardView boardView;
	public ShowCardView showCardView;
	public BoardButtonsView buttonsView;
	public ShowCardListView showCardListView;

	private LinkedList<ViewAction> viewActionQueue = new LinkedList<>();
	private ViewAction curViewAction = null;

	public GameStage(Viewport viewport) {
		super(viewport);

		initView();
	}

	private void initView() {
		float offsetRate = Res.BoardOffsetRate;
		boardView = new LocalBoardView(this, getWidth() * (1 - offsetRate), getHeight() * (1 - offsetRate));
		boardView.setPosition(getWidth() * offsetRate / 2, getHeight() * offsetRate);
		addActor(boardView);

		showCardListView = new ShowCardListView(this, getWidth(), getHeight());
		addActor(showCardListView);

		showCardView = new ShowCardView(this, getWidth(), getHeight());
		addActor(showCardView);

		buttonsView = new BoardButtonsView(this, getWidth(), getHeight());
		addActor(buttonsView);

		boardView.hide();
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
//		Log.game.info("viewAction {} begin", curViewAction.getClass().getSimpleName());
	}

	public void clearAllViewAction() {
		curViewAction = null;
		viewActionQueue.clear();
	}
}
