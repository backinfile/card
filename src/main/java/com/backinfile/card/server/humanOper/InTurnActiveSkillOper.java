package com.backinfile.card.server.humanOper;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.CSSelectSkillToActive;
import com.backinfile.card.gen.GameMessageHandler.SCSelectSkillToActive;
import com.backinfile.card.manager.LocalData;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.skills.OutTurnSkillCancelSkill;
import com.backinfile.card.model.skills.StoreSelfSkill;
import com.backinfile.card.model.skills.TurnEndSkill;
import com.backinfile.support.Utils;

// 回合进行中，出牌事件
public class InTurnActiveSkillOper extends HumanOper {
	private List<Skill> activableSkills;

	@Override
	public void onHumanAttach() {
		human.sendMessage(human.board.getBoardSetup(human.token));
		activableSkills = human.board.getActivableSkills(human.token);
		activableSkills.removeIf(s -> s instanceof OutTurnSkillCancelSkill);
		var skillInfos = activableSkills.stream().map(s -> s.toMsg()).collect(Collectors.toList());
		SCSelectSkillToActive msg = new SCSelectSkillToActive();
		msg.addAllSkillInfos(skillInfos);
		human.sendMessage(msg);
	}

	@Override
	public void onAIAttach() {
		activableSkills = human.board.getActivableSkills(human.token);
		activableSkills.removeIf(s -> s instanceof OutTurnSkillCancelSkill);

		if (activableSkills.size() == 1) { // 直接回合结束
			var skill = activableSkills.get(0);
			onSelectSkill(skill);
			return;
		}

		if (LocalData.instance().AILevel == 0) { // 直接回合结束
			var skill = human.getSkill(TurnEndSkill.class);
			onSelectSkill(skill);
			return;
		}

		if (LocalData.instance().AILevel == 1) { // 一直进行储备
			var findAny = activableSkills.stream().filter(s -> s instanceof StoreSelfSkill).findAny();
			if (findAny.isPresent()) {
				onSelectSkill(findAny.get());
				return;
			}
		}

		// 先从正常打牌中随机选取
		{
			Predicate<Skill> predicate = s -> s.card != null;
			// 场上没有空位就不要进行储备操作了
			if (human.getEmptySlots(false).isEmpty()) {
				predicate = predicate.and(s -> !(s instanceof StoreSelfSkill));
			}
			var skills = activableSkills.stream().filter(predicate).collect(Collectors.toList());

			if (!skills.isEmpty()) {
				onSelectSkill(Utils.randItem(skills));
				return;
			}
		}

		// 随机选取
		onSelectSkill(Utils.randItem(activableSkills));
	}

	@Override
	public void onDetach() {
	}

	@Override
	public void onMessage(CSSelectSkillToActive data) {
		var skillId = data.getSkillId();
		for (var skill : activableSkills) {
			if (skill.id == skillId) {
				onSelectSkill(skill);
				return;
			}
		}
	}

	private void onSelectSkill(Skill skill) {
		human.board.applySkill(skill);
		setDone();
	}
}
