package com.backinfile.card.view.group;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalUIString;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class PileView extends BaseView {
	private boolean self;
	private ECardPileType pileType;
	private Button pileButton;
	private Label label;
	private LocalUIString localUIString;

	public PileView(GameStage gameStage, float stageWidth, float stageHeight, ECardPileType pileType, boolean self) {
		super(gameStage, Res.PILE_ICON_WIDTH, Res.PILE_ICON_HEIGHT);

		this.pileType = pileType;
		this.self = self;

		localUIString = LocalString.getUIString("pileView");

		var pos = getRightPosition(stageWidth, stageHeight);
		setPosition(pos.x, pos.y, Align.center);

		{
			var texture = getTexture();
			ButtonStyle style = new ButtonStyle(texture, null, null);
			pileButton = new Button(style);
			pileButton.setSize(Res.CARD_WIDTH, Res.CARD_HEIGHT);
			pileButton.setRotation(90);
//			pileButton.layout();
		}

		{
			LabelStyle style = new LabelStyle(Res.DefaultFont, Color.WHITE);
			label = new Label(pileType.name(), style);
		}

		addActor(pileButton);
		addActor(label);
	}

	private TextureRegionDrawable getTexture() {
		TextureRegionDrawable texture = null;
		switch (pileType) {
		case DiscardPile:
			texture = Res.getTexture(localUIString.images[0]);
		case DrawPile:
			texture = Res.getTexture(localUIString.images[1]);
		default:
			Log.view.error("miss pile {} texture config", pileType);
			break;
		}
		return texture;
	}

	public ECardPileType getPileType() {
		return pileType;
	}

	private Vector2 getRightPosition(float stageWidth, float stageHeight) {
		Vector2 pos = new Vector2();
		switch (pileType) {
		case DiscardPile:
			pos.x = stageWidth * 3 / 4;
			pos.y = stageHeight / 4;
			break;
		case DrawPile:
			pos.x = stageWidth / 4;
			pos.y = stageHeight / 4;
			break;
		default:
			Log.view.error("miss pile {} pos config", pileType);
			break;
		}
		if (self) {
			pos.y += Res.CARD_STAGE_H_OFFSET;
		} else {
			pos.y = stageHeight - pos.y + Res.CARD_STAGE_H_OFFSET;
		}
		return pos;
	}

}
