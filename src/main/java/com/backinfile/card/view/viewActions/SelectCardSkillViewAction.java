package com.backinfile.card.view.viewActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backinfile.card.gen.GameMessageHandler.CSSelectSkillToActive;
import com.backinfile.card.gen.GameMessageHandler.DSkillInfo;
import com.backinfile.card.manager.LocalString;
import com.backinfile.card.model.CardInfo;
import com.backinfile.card.view.group.boardView.ButtonInfo;
import com.backinfile.support.Log;

public class SelectCardSkillViewAction extends ViewAction {
	private Map<Long, List<DSkillInfo>> hideSkillInfoMap = new HashMap<>();
	private Map<Long, List<DSkillInfo>> skillInfoMap = new HashMap<>();
	private long selectedSkillId = -1;

	public SelectCardSkillViewAction(List<DSkillInfo> skillInfos) {
		for (var skillInfo : skillInfos) {
			var list = skillInfoMap.computeIfAbsent(skillInfo.getOwnerId(), key -> new ArrayList<>());
			list.add(skillInfo);
		}
	}

	@Override
	public void begin() {
		for (var entry : skillInfoMap.entrySet()) {
			long cardId = entry.getKey();
			if (cardId != -1) {
				var cardView = gameStage.boardView.cardGroupView.getCurCardView(cardId);
				if (cardView == null) {
					hideSkillInfoMap.put(cardId, entry.getValue());
				}
			}
		}

		for (var entry : skillInfoMap.entrySet()) {
			var list = entry.getValue();
			long cardId = entry.getKey();
			if (cardId != -1) {
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
						gameStage.showCardView.show(cardView.getImagePathString(), () -> {
							// 卡牌详细界面关闭时，重新显示人物技能
							setHumanSkillButtons();
						});
						gameStage.buttonsView.setButtonInfos(buttonInfos);
					});
					cardView.setDark(false);
				}
			}
		}

		setHumanSkillButtons();
	}

	private void setHumanSkillButtons() {
		List<ButtonInfo> buttonInfos = new ArrayList<ButtonInfo>();

		// 隐藏区域中的技能
		{
			for (var skillList : hideSkillInfoMap.values()) {
				for (var skillInfo : skillList) {
					CardInfo cardInfo = gameStage.boardView.cardGroupView.getCardInfoCache(skillInfo.getOwnerId());
					var pileType = cardInfo.pileInfo.getPileType();
					var name = LocalString.getCardString(cardInfo.info.getSn()).name;
					ButtonInfo buttonInfo = new ButtonInfo();
					buttonInfo.index = buttonInfos.size();
					buttonInfo.text = "[" + LocalString.getUIString("ShowCardListView").strs[pileType.ordinal()] + ":"
							+ name + "]" + skillInfo.getTip();
					buttonInfo.callback = () -> {
						onSelectSkill(skillInfo.getSkillId());
					};
					buttonInfos.add(buttonInfo);
				}
			}
		}

		// 人物技能
		{
			var humanSkillList = skillInfoMap.get(-1L);
			if (humanSkillList != null && !humanSkillList.isEmpty()) {
				for (var skillInfo : humanSkillList) {
					ButtonInfo buttonInfo = new ButtonInfo();
					buttonInfo.index = buttonInfos.size();
					buttonInfo.text = skillInfo.getTip();
					buttonInfo.callback = () -> {
						onSelectSkill(skillInfo.getSkillId());
					};
					buttonInfos.add(buttonInfo);
				}
			}
		}
		gameStage.buttonsView.setButtonInfos(buttonInfos);
	}

	private void onSelectSkill(long skillId) {
		Log.game.info("use skill {}", skillId);
		gameStage.showCardView.hide();
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
		gameStage.buttonsView.setButtonInfos();

		selectedSkillId = skillId;
	}

	@Override
	public boolean isDone() {
		return selectedSkillId > 0;
	}

	@Override
	public void dispose() {
		CSSelectSkillToActive msg = new CSSelectSkillToActive();
		msg.setSkillId(selectedSkillId);
		gameStage.boardView.gameClient.sendMessage(msg);
	}

}
