package com.backinfile.card.view.group.boardView;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.group.CardSize;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

// 显示一张卡的大图
public class ShowCardView extends BaseView {
	private Image maskImage;
	private Image mainCardImage;

	public ShowCardView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		mainCardImage = new Image();
		maskImage = new Image(Res.TEX_BLACK);
		maskImage.getColor().a = 0.5f;
		maskImage.setSize(getWidth(), getHeight());

		addActor(maskImage);
		addActor(mainCardImage);

		addListener(new ClickListener(-1) {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setVisible(false);
			}
		});

		setVisible(false);
	}

	public void show(LocalImagePathString imagePathString) {
		mainCardImage.setDrawable(Res.getTexture(imagePathString));
		mainCardImage.setSize(CardSize.LargeLarge.width, CardSize.LargeLarge.height);
		mainCardImage.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
		this.setVisible(true);
	}
}
