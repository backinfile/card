package com.backinfile.card.model.actions;

import com.backinfile.card.gen.GameMessageHandler.EGameLogType;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.cards.ActionCard;
import com.backinfile.card.model.skills.WindSeekerSkill;
import com.backinfile.card.server.humanOper.ConfirmOper;
import com.backinfile.support.Utils;

public class WindSeekSkipAction extends WaitAction {
	private Skill skill;

	public WindSeekSkipAction(Human human, Skill skill) {
		this.human = human;
		this.skill = skill;
	}

	@Override
	public void init() {
		if (human.drawPile.isEmpty()) {
			notSkip();
			return;
		}

		var humanOper = new ConfirmOper(true, Utils.format(actionString.tips[0], skill.card.cardString.name));
		humanOper.setDetachCallback(() -> {
			if (humanOper.getResult()) {
				toJudge();
			} else {
				notSkip();
			}
		});
		human.addHumanOper(humanOper);
	}

	private void toJudge() {
		board.gameLog(human, EGameLogType.Action, actionString.tips[4]);
		var humanOper = new ConfirmOper(true, actionString.tips[1], actionString.tips[2], actionString.tips[3]);
		humanOper.setDetachCallback(() -> {
			var topCard = human.drawPile.getTop();
			board.gameLog(human, EGameLogType.Action, actionString.tips[5], topCard.cardString.name);
			if (topCard instanceof ActionCard == humanOper.getResult()) {
				board.gameLog(human, EGameLogType.Action, actionString.tips[6]);
				toSkip();
			} else {
				board.gameLog(human, EGameLogType.Action, actionString.tips[7]);
				failSkip();
			}
			addFirst(new DiscardCardAction(human, topCard));
		});
		human.addHumanOper(humanOper);
	}

	private void toSkip() {
		var heroSkill = human.getHeroSkill(WindSeekerSkill.class);
		heroSkill.triggerTimesLimit = Math.min(0, heroSkill.triggerTimesLimit - 1);

		addFirst(new ArrangePileAction(human));
		addFirst(new DiscardCardAction(human, skill.card));
		setDone();
	}

	private void failSkip() {
		var heroSkill = human.getHeroSkill(WindSeekerSkill.class);
		heroSkill.triggerTimesLimit = Math.min(0, heroSkill.triggerTimesLimit - 1);
		board.applySkillNoAPCost(skill);
		setDone();
	}

	private void notSkip() {
		board.applySkillNoAPCost(skill);
		setDone();
	}
}
