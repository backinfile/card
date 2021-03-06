package com.backinfile.card.view.group.boardView;

import com.backinfile.card.manager.LocalString;
import com.backinfile.card.manager.Res;
import com.backinfile.card.manager.LocalString.LocalUIString;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

public class BoardUIView extends BaseView {

	private Label opponentNameLabel;
	private Label selfNameLabel;
	private Label tipLabel;
	private LocalUIString uiString;

	public BoardUIView(GameStage gameStage, float width, float height) {
		super(gameStage, 0, 0);

		uiString = LocalString.getUIString("boardUIView");
		LabelStyle labelStyle = new LabelStyle(Res.DefaultFont, Color.WHITE);

		{
			opponentNameLabel = new Label(uiString.strs[0], labelStyle);
			opponentNameLabel.setAlignment(Align.center);
			opponentNameLabel.setPosition(width * 0.056f, height * 0.95f, Align.center);
			addActor(opponentNameLabel);
		}
		{
			selfNameLabel = new Label(uiString.strs[1], labelStyle);
			selfNameLabel.setAlignment(Align.center);
			selfNameLabel.setPosition(width * 0.056f, height * 0.05f, Align.center);
			addActor(selfNameLabel);
		}
		{
			tipLabel = new Label("", labelStyle);
			tipLabel.setAlignment(Align.center);
			tipLabel.setPosition(width * 0.5f, height * 0.276f, Align.center);
			addActor(tipLabel);
		}
	}

	public void setData(String name, String opponentName) {
		selfNameLabel.setText(name);
		opponentNameLabel.setText(opponentName);
	}

	public void setTipText(String tip) {
		tipLabel.setText(tip);
	}
}
