package com.backinfile.card.view.actor;

import com.backinfile.card.manager.Res;
import com.backinfile.support.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class PositionLocator extends Image {
	public PositionLocator() {
		super(Res.TEX_GRAY);

		addListener(new InputListener() {
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				var mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
				var coor = getParent().screenToLocalCoordinates(mousePos);
				setPosition(coor.x, coor.y, Align.center);
				Log.game.info("locator rate = {},{}", mousePos.x / getStage().getWidth(),
						mousePos.y / getStage().getHeight());
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

		});
	}
}
