package com.backinfile.card.view.actor;

import com.backinfile.card.manager.Res;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

// 棋盘上固定的可交互按钮
public class BoardButton extends Button {
	public BoardButton() {
		super(Res.TEX_GRAY, Res.TEX_DARK_GRAY);
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				onClick();
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				checkMouseState();
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				checkMouseState();
			}
		});
	}

	private void checkMouseState() {
		if (isOver()) {
			Gdx.graphics.setSystemCursor(SystemCursor.Hand);
		} else {
			Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
		}
	}

	protected void onClick() {
	}
}
