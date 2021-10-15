package com.backinfile.card.view.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.backinfile.card.manager.CardManager;
import com.backinfile.card.manager.GameUtils;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.manager.LocalString.LocalUIString;
import com.backinfile.card.manager.Res;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Card.CardType;
import com.backinfile.support.Utils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

public class IntroStage extends Stage {
	private HashMap<String, Label> cardNumberLabelMap = new HashMap<String, Label>();
	private static LocalUIString uiString = LocalString.getUIString("introStage");

	public IntroStage(Viewport viewport) {
		super(viewport);

		init();
	}

	private void init() {
		var table = new Table();
		TextButtonStyle textButtonStyle = new TextButtonStyle(Res.TEX_GRAY, null, null, Res.DefaultFont);
		LabelStyle labelStyle = new LabelStyle(Res.DefaultFont, Color.WHITE);
		ListStyle listStyle = new ListStyle(Res.DefaultFont, Color.WHITE, Color.WHITE, Res.TEX_DARK_GRAY);
		ScrollPaneStyle scrollStyle = new ScrollPaneStyle();
		scrollStyle.background = Res.TEX_BLACK;
		SelectBoxStyle selectBoxStyle = new SelectBoxStyle(Res.DefaultFont, Color.WHITE, Res.TEX_GRAY, scrollStyle,
				listStyle);

		// 选择英雄
		{
			List<Card> heroCardList = CardManager.getSpecOriCards(c -> c.chapter == 2 && c.mainType == CardType.HERO);
			var selectBox = new SelectBox<String>(selectBoxStyle);
			selectBox.setAlignment(Align.center);
			selectBox.setItems(heroCardList.stream().map(c -> c.cardString.name).toArray(String[]::new));
			if (!Utils.isNullOrEmpty(LocalData.instance().startHeroCard)) {
				var find = heroCardList.stream()
						.filter(c -> c.getClass().getSimpleName().equals(LocalData.instance().startHeroCard))
						.findFirst();
				if (find.isPresent()) {
					selectBox.setSelected(find.get().cardString.name);
				}
			}
			selectBox.getScrollPane().addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					var find = heroCardList.stream().filter(c -> c.cardString.name.equals(selectBox.getSelected()))
							.findFirst();
					if (find.isPresent()) {
						LocalData.instance().startHeroCard = find.get().getClass().getSimpleName();
						LocalData.instance().save();
					}
				};
			});
			table.add(new Label(uiString.strs[0], labelStyle)).left();
			table.add(selectBox).colspan(3).center().fillX();
			table.row();
		}

		// 卡牌
		var cardList = new ArrayList<Card>();
		cardList.addAll(CardManager.getSpecOriCards(c -> c.chapter == 2 && c.mainType == CardType.ACTION));
		cardList.addAll(CardManager.getSpecOriCards(c -> c.chapter == 2 && c.mainType == CardType.STORE));

		for (var card : cardList) {
			var sn = card.getClass().getSimpleName();
			var numberField = new Label("", labelStyle);
			var leftBtn = new TextButton("<", textButtonStyle);
			var rightBtn = new TextButton(">", textButtonStyle);
			cardNumberLabelMap.put(sn, numberField);

			numberField.setText(getCardNumber(sn));
			numberField.setAlignment(Align.center);
			leftBtn.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					numberField.setText(changeCardNumber(sn, -1));
				}
			});
			rightBtn.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					numberField.setText(changeCardNumber(sn, 1));
				}
			});

			table.add(new Label(card.cardString.name, labelStyle)).width(Res.CARD_WIDTH);
			table.add(leftBtn);
			table.add(numberField).width(Res.PILE_ICON_WIDTH);
			table.add(rightBtn);
			table.row();
		}

		// ai等级
		{
			var ailevelSelectBox = new SelectBox<String>(selectBoxStyle);
			ailevelSelectBox.setAlignment(Align.center);
			ailevelSelectBox.setItems(LocalString.getUIString("AILevel").strs);
			ailevelSelectBox.setSelectedIndex(LocalData.instance().AILevel);
			ailevelSelectBox.getScrollPane().addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					LocalData.instance().AILevel = ailevelSelectBox.getSelectedIndex();
					LocalData.instance().save();
				};
			});
			table.add(new Label(uiString.strs[2], labelStyle)).left();
			table.add(ailevelSelectBox).colspan(3).center().fillX();
			table.row();
		}

		// 开始按钮
		{
			var startButton = new TextButton(uiString.strs[1], textButtonStyle);
			startButton.addListener(new ClickListener() {
				public void clicked(InputEvent event, float x, float y) {
					GameScreen.Instance.startGame();
				};
			});
			table.add(startButton).colspan(4).center().padTop(Res.PILE_ICON_HEIGHT);
		}

		table.setFillParent(true);
		addActor(table);
	}

	private void reset() {
		LocalData.instance().startPile = GameUtils.getDefaultStartPile();
		LocalData.instance().save();
		for (var entry : cardNumberLabelMap.entrySet()) {
			entry.getValue().setText(getCardNumber(entry.getKey()));
		}
	}

	private int getCardNumber(String sn) {
		return LocalData.instance().startPile.getOrDefault(sn, 0);
	}

	private int changeCardNumber(String sn, int d) {
		int number = MathUtils.clamp(getCardNumber(sn) + d, 0, 99);
		LocalData.instance().startPile.put(sn, number);
		LocalData.instance().save();
		return number;
	}
}
