package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString.LocalCardString;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

// 显示一张卡牌
public class CardView extends Group {

	private Image mainImage;

	private LocalCardString cardString;
	private boolean flipOver = false;
	private int position = 0;

	public CardView() {
		mainImage = new Image();
		addActor(mainImage);
		setSize(CardSize.Normal);
	}

	public void setCardString(LocalCardString cardString) {
		this.cardString = cardString;
		this.position = 0;
		updateView();
	}

	public void setCardString(LocalCardString cardString, int position) {
		this.cardString = cardString;
		this.position = position;
		updateView();
	}

	public void clearState() {
		setSize(CardSize.Normal);
		setDark(false);
		setFlipOver(false);
	}

	public void setFlipOver(boolean flipOver) {
		this.flipOver = flipOver;
		updateView();
	}

	public void setSize(CardSize cardSize) {
		mainImage.setSize(cardSize.width, cardSize.height);
	}

	public void setDark(boolean dark) {
		var color = mainImage.getColor();
		if (dark) {
			color.r *= 0.5;
			color.g *= 0.5;
			color.b *= 0.5;
		} else {
			color.set(Color.WHITE);
		}
	}

	private void updateView() {
		if (cardString != null) {
			if (!flipOver) {
				var texture = Res.getTexture(cardString.frontImages[position]);
				mainImage.setDrawable(texture);
			} else {
				var texture = Res.getTexture(cardString.backImages[position]);
				mainImage.setDrawable(texture);
			}
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	public static enum CardSize {
		Small(Res.CARD_WIDTH_S, Res.CARD_HEIGHT_S), // 小
		Normal(Res.CARD_WIDTH, Res.CARD_HEIGHT), // 中
		Large(Res.CARD_WIDTH_L, Res.CARD_HEIGHT_L), // 大
		LargeLarge(Res.CARD_WIDTH_LL, Res.CARD_HEIGHT_LL), // 特大

		;
		public final float width;
		public final float height;

		private CardSize(float width, float height) {
			this.width = width;
			this.height = height;
		}

	}
}
