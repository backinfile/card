package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString.LocalCardString;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

// 显示一张卡牌
public class CardView extends Group {

	private Image mainImage;
	private Image maskImage;

	private LocalCardString cardString;
	private boolean flipOver = false;
	private int position = 0;

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

	public CardView() {
		mainImage = new Image();
		maskImage = new Image(Res.TEX_BLACK);
		maskImage.getColor().a = 0.5f;
		addActor(mainImage);
		addActor(maskImage);

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

	public void setFlipOver(boolean flipOver) {
		this.flipOver = flipOver;
		updateView();
	}

	public void setSize(CardSize cardSize) {
		mainImage.setSize(cardSize.width, cardSize.height);
		maskImage.setSize(cardSize.width, cardSize.height);
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

}
