package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

// 显示一张卡的大图
public class ShowCardView extends BaseView {
	private Image maskImage;
	private Image mainCardImage;

	public ShowCardView(GameStage gameStage, float width, float height) {
		super(gameStage, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		mainCardImage = new Image();
		maskImage = new Image(Res.TEX_HALF_BLACK);
		maskImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		maskImage.setPosition(0, 0, Align.bottomLeft);

		addActor(maskImage);
		addActor(mainCardImage);

		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Log.game.info("cardView click");
				hide();
			}
		});

		hide();
	}

	public void show(LocalImagePathString imagePathString) {
		mainCardImage.setDrawable(Res.getTexture(imagePathString));
		mainCardImage.setSize(Res.CARD_WIDTH_L, Res.CARD_HEIGHT_L);
		mainCardImage.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
		this.setVisible(true);
	}
}
