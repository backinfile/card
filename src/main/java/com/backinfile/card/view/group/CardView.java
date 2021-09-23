package com.backinfile.card.view.group;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalCardString;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.backinfile.card.model.LocalString.LocalUIString;
import com.backinfile.card.view.actions.TimeoutAction;
import com.backinfile.support.func.Action0;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

// 显示一张卡牌
public class CardView extends Group {

	private static LocalUIString cardToolsString = LocalString.getUIString("cardTools");

	private Image mainImage;
	private Image boarderImage;

	private LocalCardString cardString;
	private boolean flipOver = false;
	private int pilePosition = 0;
	private int zIndex = 0;
	private Action0 leftClickCallback = null; // 点击回调

	public static class CardViewState {
		public Vector2 position = new Vector2();
		public CardSize cardSize = CardSize.Normal;
		public boolean flipOver = false; // 翻面
		public boolean dark = true; // 变暗 变暗状态下不能使用, 改用border形式
		public boolean rotated = false; // 横置
		public int zIndex = 0; // 越大距离玩家越近
	}

	public CardView() {
		boarderImage = new Image(Res.getTexture(cardToolsString.images[0]));
		mainImage = new Image() {
			@Override
			public void setSize(float width, float height) {
				super.setSize(width, height);
				setPosition(0, 0, Align.center);

				boarderImage.setSize(width, height);
				boarderImage.setPosition(0, 0, Align.center);
			}
		};
		addActor(mainImage);
		addActor(boarderImage);

		setSize(CardSize.Normal);
		setDark(false);

		addListener(new ClickListener(Buttons.LEFT) {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				invokeLeftClickCallback();
			}
		});
	}

	public void setCardString(LocalCardString cardString) {
		setCardString(cardString, 0);
	}

	public void setCardString(LocalCardString cardString, int pilePosition) {
		this.cardString = cardString;
		this.pilePosition = pilePosition;
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
		setZIndex(state.zIndex);
	}

	public final void moveToState(CardViewState state, Runnable callback) {
		mainImage.addAction(Actions.sizeTo(state.cardSize.width, state.cardSize.height, Res.BASE_DURATION));
		ParallelAction parallelAction = new ParallelAction();
		parallelAction.addAction(Actions.moveTo(state.position.x, state.position.y, Res.BASE_DURATION));
		parallelAction.addAction(Actions.rotateTo(state.rotated ? 90 : 0, Res.BASE_DURATION));
		parallelAction.addAction(new TimeoutAction(Res.BASE_DURATION / 2, () -> {
			setDark(state.dark);
			setFlipOver(state.flipOver);
		}));
		addAction(parallelAction);
		if (callback != null) {
			var runableAction = new RunnableAction();
			runableAction.setRunnable(callback);
			addAction(runableAction);
		}

		setZIndex(state.zIndex);
	}

	public void setFlipOver(boolean flipOver) {
		this.flipOver = flipOver;
		updateView();
	}

	public boolean isFlipOver() {
		return flipOver;
	}

	public void setLeftClickCallback(Action0 leftClickCallback) {
		this.leftClickCallback = leftClickCallback;
	}

	public void invokeLeftClickCallback() {
		if (this.leftClickCallback != null) {
			this.leftClickCallback.invoke();
		}
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

	@Override
	public boolean setZIndex(int index) {
		this.zIndex = index;
		return true;
	}

	@Override
	public int getZIndex() {
		return this.zIndex;
	}

	public void setDark(boolean dark) {
		boarderImage.setVisible(!dark);
	}

	private void updateView() {
		if (cardString != null) {
			if (!flipOver) {
				var texture = Res.getTexture(cardString.frontImages[pilePosition]);
				mainImage.setDrawable(texture);
			} else {
				var texture = Res.getTexture(cardString.backImages[pilePosition]);
				mainImage.setDrawable(texture);
			}
		}
	}

	public LocalImagePathString getImagePathString() {
		return cardString.frontImages[pilePosition];
	}
}
