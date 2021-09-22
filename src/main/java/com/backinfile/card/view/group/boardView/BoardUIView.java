package com.backinfile.card.view.group.boardView;

import java.text.MessageFormat;

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
	private Label actionPointLabel;
	private Label tipLabel;
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
		{
			tipLabel = new Label("", labelStyle);
			tipLabel.setAlignment(Align.center);
			tipLabel.setPosition(width * 0.5f, height * 0.24f, Align.center);
			addActor(selfNameLabel);
		}
		{
			actionPointLabel = new Label(uiString.strs[3], labelStyle);
			actionPointLabel.setAlignment(Align.left);
			actionPointLabel.setPosition(width * 0.874f, height * 0.34f, Align.left);
			addActor(actionPointLabel);
		}
	}

	public void setData(String name, String opponentName) {
		selfNameLabel.setText(name);
		opponentNameLabel.setText(opponentName);
	}

	public void setTipText(String tip) {
		tipLabel.setText(tip);
	}

	public void setActionPoint(int actionPoint) {
		actionPointLabel.setText(MessageFormat.format(uiString.strs[3], actionPoint));
	}
}
