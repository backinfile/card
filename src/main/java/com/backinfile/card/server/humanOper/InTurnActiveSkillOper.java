package com.backinfile.card.server.humanOper;

import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.CSSelectSkillToActive;
import com.backinfile.card.gen.GameMessageHandler.SCSelectSkillToActive;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.skills.TurnEndSkill;

// 回合进行中，出牌事件
public class InTurnActiveSkillOper extends HumanOper {
	private List<Skill> activableSkills;

	@Override
	public void onHumanAttach() {
		activableSkills = human.board.getActivableSkills(human.token);
		var skillInfos = activableSkills.stream().map(s -> s.toMsg()).collect(Collectors.toList());
		SCSelectSkillToActive msg = new SCSelectSkillToActive();
		msg.addAllSkillInfos(skillInfos);
		human.sendMessage(msg);
	}

	@Override
	public void onAIAttach() {
		var skill = human.getSkill(TurnEndSkill.class);
		skill.setContext(human.board, human, null);
		human.board.applySkill(skill);
		setDone();
	}

	@Override
	public void onDetach() {
	}

	@Override
	public void onMessage(CSSelectSkillToActive data) {
		var skillId = data.getSkillId();
		for (var skill : activableSkills) {
			if (skill.id == skillId) {
				human.board.applySkill(skill);
				setDone();
				return;
			}
		}
	}
}
