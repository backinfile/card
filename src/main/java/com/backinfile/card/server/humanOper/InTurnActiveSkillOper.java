package com.backinfile.card.server.humanOper;

import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.CSSelectSkillToActive;
import com.backinfile.card.gen.GameMessageHandler.SCSelectSkillToActive;
import com.backinfile.card.manager.ConstGame;
import com.backinfile.card.model.Skill;
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
		var skillInfos = activableSkills.stream().map(s -> s.toMsg()).collect(Collectors.toList());
		SCSelectSkillToActive msg = new SCSelectSkillToActive();
		msg.addAllSkillInfos(skillInfos);
		human.sendMessage(msg);
	}

	@Override
	public void onAIAttach() {
		activableSkills = human.board.getActivableSkills(human.token);

		if (ConstGame.AI_DO_NOTHING) { // 直接回合结束
			var skill = human.getSkill(TurnEndSkill.class);
			skill.setContext(human.board, human, null);
			onSelectSkill(skill);
			return;
		}

		if (ConstGame.AI_DO_STORE) { // 一直进行储备
			var findAny = activableSkills.stream().filter(s -> s instanceof StoreSelfSkill).findAny();
			if (findAny.isPresent()) {
				onSelectSkill(findAny.get());
				return;
			}
		}

		// 随机选取
		var skill = activableSkills.get(Utils.nextInt(activableSkills.size()));
		onSelectSkill(skill);
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
