package com.backinfile.card.view.group.boardView;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalUIString;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class BoardUIView extends BaseView {

	private Label opponentNameLabel;
	private Label selfNameLabel;
	private LocalUIString uiString;

	public BoardUIView(GameStage gameStage, float width, float height) {
		super(gameStage, 0, 0);

		uiString = LocalString.getUIString("boardUIView");
		LabelStyle labelStyle = new LabelStyle(Res.DefaultFont, Color.WHITE);

		{
			opponentNameLabel = new Label(uiString.strs[0], labelStyle);
			opponentNameLabel.setAlignment(Align.left);
			opponentNameLabel.setPosition(width * 0.02f, height * 0.95f, Align.left);
			addActor(opponentNameLabel);
		}
		{
			selfNameLabel = new Label(uiString.strs[1], labelStyle);
			selfNameLabel.setAlignment(Align.left);
			selfNameLabel.setPosition(width * 0.02f, height * 0.05f, Align.left);
			addActor(selfNameLabel);
		}
	}

	public void setData(String name, String opponentName) {
		selfNameLabel.setText(name);
		opponentNameLabel.setText(opponentName);
	}
}
