package com.backinfile.card.view.group.boardView;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.model.LocalString;
import com.backinfile.card.model.LocalString.LocalUIString;
import com.backinfile.card.view.actions.TimeoutAction;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.group.CardSize;
import com.backinfile.card.view.group.PileView.PilePosition;
import com.backinfile.card.view.stage.GameStage;
import com.backinfile.support.ObjectPool;
import com.backinfile.support.func.Action1;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class ShowCardListView extends BaseView {
	private Image maskImage;
	private Group viewGroup;
	private Label tipLabel;

	private float curScroolAmount = 0;
	private float maxScroolAmount = 0;

	private ObjectPool<CardView> cardViewPool;
	private List<CardView> visibleVardViews = new ArrayList<>();

	private Action1<CardInfo> callback;

	private static final LocalUIString uiString = LocalString.getUIString(ShowCardListView.class.getSimpleName());
	private static final float LargeRateW = 1.3f;
	private static final float LargeRateH = 1.2f;
	private static final int HNumber = 4;
	private static final float ScroolRate = 0.2f;

	public ShowCardListView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		maskImage = new Image(Res.TEX_BLACK);
		maskImage.getColor().a = 0.8f;
		maskImage.setSize(getWidth(), getHeight());
		addActor(maskImage);
		viewGroup = new Group();
		addActor(viewGroup);
		tipLabel = new Label("", new LabelStyle(Res.DefaultFont, Color.WHITE));
		tipLabel.setAlignment(Align.center);
		tipLabel.setX(width / 2, Align.center);
		tipLabel.setY(getHeight() / 2 + 1.6f * CardSize.Large.height * LargeRateH, Align.center);
		addActor(tipLabel);

		cardViewPool = new ObjectPool<CardView>() {
			@Override
			protected CardView newObject() {
				var cardView = new CardView();
				cardView.addListener(new ClickListener(Buttons.RIGHT) {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						gameStage.showCardView.show(cardView.getImagePathString());
					}
				});
				return cardView;
			}
		};

		addListener(new InputListener() {
			@Override
			public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
				onScroll(amountY);
				return true;
			}
		});

		setVisible(false);
	}

	public void show(List<CardInfo> cardInfos, String tip, Action1<CardInfo> callback) {
		// 初始化
		clearActions();
		this.callback = callback;
		gameStage.setScrollFocus(this);
		viewGroup.setPosition(0, 0);
		curScroolAmount = 0;
		setVisible(true);
		for (var cardView : visibleVardViews) {
			viewGroup.removeActor(cardView);
			cardViewPool.free(cardView);
		}
		visibleVardViews.clear();
		tipLabel.setText(tip);

		// 添加卡牌
		maxScroolAmount = (float) Math.max(0, Math.ceil(cardInfos.size() * 1f / HNumber) - 3) * (1 / ScroolRate);
		for (int i = 0; i < cardInfos.size(); i++) {
			var cardInfo = cardInfos.get(i);
			CardView cardView = cardViewPool.obtain();
			cardView.setCardString(LocalString.getCardString(cardInfo.info.getSn()),
					cardInfo.getPilePosition().ordinal());
			cardView.setSize(CardSize.Large);
			cardView.setDark(false);
			cardView.setLeftClickCallback(() -> {
				onClick(cardInfo);
			});
			if (cardInfo.getPilePosition() == PilePosition.Self) {
				cardView.setText(uiString.strs[cardInfo.pileInfo.getPileType().ordinal()]);
			} else {
				cardView.setText(uiString.str + uiString.strs[cardInfo.pileInfo.getPileType().ordinal()]);
			}
			var position = getCardPosition(i);
			cardView.setPosition(position.x, position.y);
			viewGroup.addActor(cardView);
			visibleVardViews.add(cardView);
		}
	}

	private Vector2 getCardPosition(int index) {
		Vector2 position = new Vector2();
		int xIndex = index % HNumber;
		int yIndex = index / HNumber - 1;
		float xIndexCenter = xIndex - (HNumber - 1f) / 2f;
		position.x = getWidth() / 2 + xIndexCenter * CardSize.Large.width * LargeRateW;
		position.y = getHeight() / 2 - yIndex * CardSize.Large.height * LargeRateH;
		return position;
	}

	private void onScroll(float amount) {
		curScroolAmount = MathUtils.clamp(curScroolAmount + amount, 0f, maxScroolAmount);
		viewGroup.setY(curScroolAmount * CardSize.Large.height * ScroolRate);
	}

	private void onClick(CardInfo cardInfo) {
		if (callback != null) {
			callback.invoke(cardInfo);
		}
	}

	private static class CardView extends com.backinfile.card.view.group.CardView {
		private Label pileLabel;

		public CardView() {
			super();

			LabelStyle labelStyle = new LabelStyle(Res.DefaultFont, Color.WHITE);
			pileLabel = new Label("12345", labelStyle);
			pileLabel.setAlignment(Align.center);
			addActor(pileLabel);
			updatePileLabel();
		}

		@Override
		public void setSize(CardSize cardSize) {
			super.setSize(cardSize);
			updatePileLabel();
		}

		public void setText(String text) {
			pileLabel.setText(text);
		}

		private void updatePileLabel() {
			if (pileLabel != null) {
				pileLabel.setPosition(0, -mainImage.getHeight() / 2, Align.top);
			}
		}
	}

	@Override
	public void hide() {
		addAction(new TimeoutAction(0.05f, () -> {
			setVisible(false);
		}));
	}
}
