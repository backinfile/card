package com.backinfile.card.view.group.boardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.backinfile.card.view.actor.ActionButton;
import com.backinfile.card.view.group.BaseView;
import com.backinfile.card.view.stage.GameStage;
import com.badlogic.gdx.utils.Align;

// 控制按钮的显示与回调
public class BoardButtonsView extends BaseView {
	private List<ActionButton> actionButtons;

	public BoardButtonsView(GameStage gameStage, float width, float height) {
		super(gameStage, 0, 0);

		actionButtons = new ArrayList<ActionButton>();
		for (int i = 0; i < 5; i++) {
			ActionButton btn = new ActionButton();
			btn.setVisible(false);
			btn.setPosition(width * 0.856f, height * (0.30f - i * 0.06f), Align.left);
			addActor(btn);
			actionButtons.add(btn);
		}

	}

	public final void setButtonInfos(List<ButtonInfo> buttonInfos) {
		for (var i = 0; i < actionButtons.size(); i++) {
			final var index = i;
			var btn = actionButtons.get(index);
			var optional = buttonInfos.stream().filter(b -> b.index == index).findAny();
			if (optional.isPresent()) {
				btn.setVisible(true);
				btn.set(optional.get().text, optional.get().callback);
			} else {
				btn.setVisible(false);
			}
		}
	}

	public final void setButtonInfos(ButtonInfo... buttonInfos) {
		setButtonInfos(Arrays.asList(buttonInfos));
	}

}
