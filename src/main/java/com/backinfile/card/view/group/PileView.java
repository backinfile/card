package com.backinfile.card.view.group;

import com.backinfile.card.gen.GameMessageHandler.ECardPileType;
import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalUIString;
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
	private PileButton pileButton; // 查看牌库按钮
	private LocalUIString uiString;
	public ECardPileType pileType;
	public PilePosition pilePosition;
	private Label numberLabel;

	private boolean pileRoated = false;

	public PileView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		pileButton = new PileButton();
		pileButton.setSize(CardSize.Normal.width, CardSize.Normal.height);
		addActor(pileButton);

		numberLabel = new Label("0", new LabelStyle(Res.DefaultFont, Color.WHITE));
		addActor(numberLabel);

	}

	public PileView(GameStage gameStage, float width, float height, ECardPileType type, PilePosition pilePosition) {
		this(gameStage, width, height);
		setPileType(type, pilePosition);
	}

	public void setPileType(ECardPileType type, PilePosition pilePosition) {
		this.uiString = LocalString.getUIString("Pile" + type.name());
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
	}

	/**
	 * 这个牌堆属于哪个角色
	 */
	public static enum PilePosition {
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

		Vector2 position = new Vector2(getX(), getY());
		pileRoated = false;

		switch (pileType) {
		case DiscardPile: {
			if (pilePosition == PilePosition.Self) {
				position.set(getWidth() * 0.776f, getHeight() * 0.225f);
			} else {
				position.set(getWidth() * 0.22f, getHeight() * 0.77f);
			}
			pileRoated = true;
			break;
		}
		case DrawPile: {
			if (pilePosition == PilePosition.Self) {
				position.set(getWidth() * 0.225f, getHeight() * 0.225f);
			} else {
				position.set(getWidth() * 0.77f, getHeight() * 0.77f);
			}
			pileRoated = true;
			break;
		}
		case HandPile:
			break;
		case HeroPile:
			break;
		case MarkPile:
			break;
		case None:
			break;
		case SlotPile:
			break;
		case TrashPile:
			break;
		default:
			break;
		}
		pileButton.setPosition(position.x, position.y, Align.center);
		pileButton.setOrigin(Align.center);
		pileButton.setRotation(pileRoated ? 90 : 0);

		numberLabel.setPosition(position.x, position.y - pileButton.getWidth() / 2, Align.top);
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
