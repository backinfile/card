package com.backinfile.card.view.group.boardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.backinfile.card.manager.Res;
import com.backinfile.card.model.LocalString.LocalImagePathString;
import com.backinfile.card.view.actor.ActionButton;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.group.CardSize;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

// 选择触发卡牌的一项技能
public class UseCardSkillView extends BaseView {
	private Image maskImage;
	private Image mainCardImage;
	private List<ActionButton> actionButtons;

	public UseCardSkillView(GameStage gameStage, float width, float height) {
		super(gameStage, width, height);

		mainCardImage = new Image();
		maskImage = new Image(Res.TEX_BLACK);
		maskImage.getColor().a = 0.5f;
		maskImage.setSize(getWidth(), getHeight());

		addActor(maskImage);
		addActor(mainCardImage);

		maskImage.addListener(new ClickListener(-1) {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setVisible(false);
			}
		});

		actionButtons = new ArrayList<ActionButton>();
		for (int i = 0; i < 5; i++) {
			ActionButton btn = new ActionButton();
			btn.setVisible(false);
			btn.setPosition(width * 0.874f, height * (0.197f - i * 0.06f), Align.left);
			addActor(btn);
			actionButtons.add(btn);
		}

		setVisible(false);
	}

	public void show(LocalImagePathString imagePathString, ButtonInfo... buttonInfos) {
		mainCardImage.setDrawable(Res.getTexture(imagePathString));
		mainCardImage.setSize(CardSize.LargeLarge.width, CardSize.LargeLarge.height);
		mainCardImage.setPosition(getWidth() / 2, getHeight() / 2, Align.center);
		this.setVisible(true);

		setButtonInfos(buttonInfos);
	}

	private void setButtonInfos(ButtonInfo... buttonInfos) {
		for (var i = 0; i < actionButtons.size(); i++) {
			final var index = i;
			var btn = actionButtons.get(index);
			var optional = Arrays.stream(buttonInfos).filter(b -> b.index == index).findAny();
			if (optional.isPresent()) {
				btn.setVisible(true);
				btn.set(optional.get().text, optional.get().callback);
			} else {
				btn.setVisible(false);
			}
		}
	}
}
