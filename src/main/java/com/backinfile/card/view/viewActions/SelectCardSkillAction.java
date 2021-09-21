package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backinfile.card.gen.GameMessageHandler.DSkillInfo;
import com.backinfile.card.view.group.boardView.ButtonInfo;
import com.backinfile.support.Log;

public class SelectCardSkillAction extends ViewAction {

	private Map<Long, List<DSkillInfo>> skillInfoMap = new HashMap<>();

	public SelectCardSkillAction(List<DSkillInfo> skillInfos) {
		for (var skillInfo : skillInfos) {
			var list = skillInfoMap.computeIfAbsent(skillInfo.getOwnerId(), key -> new ArrayList<>());
			list.add(skillInfo);
		}
	}

	@Override
	public void begin() {
		for (var entry : skillInfoMap.entrySet()) {
			var list = entry.getValue();
			long cardId = entry.getKey();
			if (cardId == 0) {

			} else {
				var cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
				if (cardView != null) {
					cardView.setLeftClickCallback(() -> {
						ButtonInfo[] buttonInfos = new ButtonInfo[list.size()];
						for (var i = 0; i < buttonInfos.length; i++) {
							var skillInfo = list.get(i);
							var info = new ButtonInfo();
							buttonInfos[i] = info;
							info.index = i;
							info.text = skillInfo.getTip();
							info.callback = () -> {
								onSelectSkill(skillInfo.getSkillId());
							};
						}
						gameStage.useCardSkillView.show(cardView.getImagePathString(), buttonInfos);
					});
					cardView.setDark(false);
				}
			}
		}
	}

	private void onSelectSkill(long skillId) {
		Log.game.info("use skill {}", skillId);
		gameStage.useCardSkillView.hide();
		for (var entry : skillInfoMap.entrySet()) {
			long cardId = entry.getKey();
			if (cardId == 0) {

			} else {
				var cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
				if (cardView != null) {
					cardView.setLeftClickCallback(null);
					cardView.setDark(true);
				}
			}
		}
	}

}
