package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.badlogic.gdx.math.Vector2;

public enum CardSize {
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

	public Vector2 getSize() {
		return new Vector2(width, height);
	}

}