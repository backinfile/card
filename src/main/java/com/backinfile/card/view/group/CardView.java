package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString.LocalCardString;
import com.backinfile.card.view.actions.TimeoutAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

// 显示一张卡牌
public class CardView extends Group {

	private Image mainImage;

	private LocalCardString cardString;
	private boolean flipOver = false;
	private int position = 0;

	public static class CardViewState {
		public Vector2 position = new Vector2();
		public CardSize cardSize = CardSize.Normal;
		public boolean flipOver = false; // 翻面
		public boolean dark = false; // 变暗
		public boolean rotated = false; // 横置
	}

	public CardView() {
		mainImage = new Image() {
			@Override
			public void setSize(float width, float height) {
				super.setSize(width, height);
				setPosition(0, 0, Align.center);
			}
		};
		addActor(mainImage);
		setSize(CardSize.Normal);
	}

	public void setCardString(LocalCardString cardString) {
		setCardString(cardString, 0);
	}

	public void setCardString(LocalCardString cardString, int position) {
		this.cardString = cardString;
		this.position = position;
		updateView();
	}

	public final void clearState() {
		setState(new CardViewState());
	}

	public final void setState(CardViewState state) {
		clearActions();
		setSize(state.cardSize); // 设置mainImage
		setPosition(state.position.x, state.position.y); // 设置本身属性
		setRotation(state.rotated ? 90 : 0); // 设置mainImage
		setDark(state.dark); // 设置mainImage
		setFlipOver(state.flipOver); // 设置本身属性
	}

	public final void moveToState(CardViewState state) {
		mainImage.addAction(Actions.sizeTo(state.cardSize.width, state.cardSize.height, Res.BASE_DURATION));
		ParallelAction parallelAction = new ParallelAction();
		parallelAction.addAction(Actions.moveTo(state.position.x, state.position.y, Res.BASE_DURATION));
		parallelAction.addAction(Actions.rotateTo(state.rotated ? 90 : 0, Res.BASE_DURATION));
		parallelAction.addAction(new TimeoutAction(Res.BASE_DURATION / 2, () -> {
			setDark(state.dark);
			setFlipOver(state.flipOver);
		}));
		addAction(parallelAction);
	}

	public void setFlipOver(boolean flipOver) {
		this.flipOver = flipOver;
		updateView();
	}

	public void setSize(CardSize cardSize) {
		mainImage.setSize(cardSize.width, cardSize.height);
		mainImage.setPosition(0, 0, Align.center);
		mainImage.setOrigin(Align.center);
	}

	@Override
	public void setSize(float width, float height) {
		mainImage.setSize(width, height);
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

}
