package com.backinfile.card.view.group;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.manager.Res;
import com.backinfile.card.manager.LocalString.LocalUIString;
import com.backinfile.card.view.actor.BoardButton;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;

// 管理一个牌堆
public class PileView extends BaseView {
	private static final LocalUIString uiString = LocalString.getUIString("PileView");
	private PileButton pileButton; // 查看牌库按钮
	public ECardPileType pileType;
	public HumanPosition pilePosition;
	private Label numberLabel;

	private boolean pileRoated = false;

	public PileView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		pileButton = new PileButton();
		pileButton.setSize(CardSize.Normal.width, CardSize.Normal.height);
		addActor(pileButton);

		numberLabel = new Label("0", new LabelStyle(Res.DefaultFont, Color.WHITE));
		numberLabel.setAlignment(Align.center);
		addActor(numberLabel);

	}

	public PileView(GameStage gameStage, float width, float height, ECardPileType type, HumanPosition pilePosition) {
		this(gameStage, width, height);
		setPileType(type, pilePosition);
	}

	public void setPileType(ECardPileType type, HumanPosition pilePosition) {
		this.pileType = type;
		this.pilePosition = pilePosition;

		updateView();
	}

	public boolean isPileRoated() {
		return pileRoated;
	}

	public Vector2 getPilePosition() {
		return new Vector2(pileButton.getX(Align.center), pileButton.getY(Align.center));
	}

	public void setPileNumber(int number) {
		numberLabel.setText(String.valueOf(number));
		setVisible(number > 0);
	}

	/**
	 * 这个牌堆属于哪个角色
	 */
	public static enum HumanPosition {
		Self, Opponent,
	}

	public class PileButton extends BoardButton {
		@Override
		protected void onClick() {
			Log.game.info("open card pile view screen {},{}", pileType.name(), pilePosition.name());
		}
	}

	private void updateView() {
		var texture = Res.getTexture(uiString.images[pilePosition.ordinal()]);
		pileButton.setStyle(new ButtonStyle(texture, null, null));

		Vector2 position = CardGroupView.getCommonPilePosition(getWidth(), getHeight(), pileType, pilePosition);
		pileRoated = false;
		boolean up = false;

		switch (pileType) {
		case DrawPile:
		case DiscardPile: {
			pileRoated = true;
			break;
		}
		case ThreatenPile:
		case MarkPile:
			if (pilePosition == HumanPosition.Self) {
				up = true;
			}
			break;

		default:
			break;
		}
		pileButton.setPosition(position.x, position.y, Align.center);
		pileButton.setOrigin(Align.center);
		pileButton.setRotation(pileRoated ? 90 : 0);

		float hOffset = pileButton.getHeight() / 2;
		if (pileRoated) {
			hOffset = pileButton.getWidth() / 2;
		}
		if (up) {
			numberLabel.setPosition(position.x, position.y + hOffset, Align.center);
		} else {
			numberLabel.setPosition(position.x, position.y - hOffset, Align.center);
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			numberLabel.setVisible(true);
		} else {
			numberLabel.setVisible(false);
		}
	}
}
