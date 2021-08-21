package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.backinfile.card.view.actor.DefaultButton;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class UseCardSkillView extends BaseView {
	private Image maskImage;
	private Image mainCardImage;
	private Table buttonTable;

	public UseCardSkillView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		mainCardImage = new Image();
		maskImage = new Image(Res.TEX_HALF_BLACK);
		maskImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		buttonTable = new Table();
		buttonTable.setPosition(getWidth() * 2 / 3, 0);
		buttonTable.setSize(getWidth() / 3, getHeight());
		DefaultButton button = new DefaultButton();
		button.setText("abcd");
		buttonTable.add(button).expand().fill();

		addActor(maskImage);
		addActor(mainCardImage);
		addActor(buttonTable);

		hide();
	}

	public void show(LocalImagePathString imagePathString) {
		mainCardImage.setDrawable(Res.getTexture(imagePathString));
		mainCardImage.setSize(Res.CARD_WIDTH_L, Res.CARD_HEIGHT_L);
		mainCardImage.setPosition(getWidth() / 3 - mainCardImage.getWidth() / 2,
				getHeight() / 2 - mainCardImage.getHeight() / 2);
		this.setVisible(true);
	}

}
