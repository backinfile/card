package com.backinfile.card.model.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.backinfile.card.gen.GameMessageHandler.ESlotType;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.model.Card;
import com.backinfile.card.model.Human;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.Skill.SkillAura;
import com.backinfile.card.model.Skill.SkillTrigger;
import com.backinfile.card.server.humanOper.SelectCardOper;
import com.backinfile.card.server.humanOper.SelectEmptySlotOper;
import com.backinfile.support.Log;

public class AttackAction extends WaitAction {
	private Human targetHuman;
	private boolean withAttackEffect = false;
	private AttackResult attackResult = AttackResult.None;
	private List<Skill> triggeredSkillList = new ArrayList<>(); // 已经触发过的技能
	private boolean isAttackDone;

	public AttackAction(Human human, Card card, Human targetHuman) {
		this.human = human;
		this.card = card;
		this.targetHuman = targetHuman;
	}

	public AttackAction(Human human, Card card, Human targetHuman, boolean withAttackEffect) {
		this.human = human;
		this.card = card;
		this.targetHuman = targetHuman;
		this.withAttackEffect = withAttackEffect;
	}

	public static enum AttackResult {
		None, Occupy, Break,
	}

	@Override
	public void init() {
		setDone(false);

		// 有释放特效
		if (withAttackEffect) {
			var skill = card.getSkill(s -> s.testTriggerable(SkillTrigger.ReplaceRelease, SkillAura.AnyWhere));
			if (skill != null) {
				board.applySkill(skill);
				setDone();
				return;
			}
		}

		// 防御型技能
		{
			// 英雄技能
			for (var card : targetHuman.heroPile) {
				for (var skill : card.getSkillList()) {
					if (checkDefendSkill(targetHuman, card, skill, SkillAura.Hero)) {
						return;
					}
				}
			}
			// 手牌里的技能
			for (var card : targetHuman.handPile) {
				for (var skill : card.getSkillList()) {
					if (checkDefendSkill(targetHuman, card, skill, SkillAura.Hand)) {
						return;
					}
				}
			}
			// 储备位上的技能
			for (var card : targetHuman.getAllStoreInSlot(false, false, false, false)) {
				for (var skill : card.getSkillList()) {
					if (checkDefendSkill(targetHuman, card, skill, SkillAura.Slot)) {
						return;
					}
				}
			}
		}

		var emptySlots = targetHuman.getEmptySlots(true);
		if (!emptySlots.isEmpty()) {
			// 有空位，直接侵占上去
			var humanOper = new SelectEmptySlotOper(emptySlots.stream().map(s -> s.index).collect(Collectors.toList()),
					ETargetSlotAimType.Occupy, targetHuman != human, actionString.tips[0]);
			humanOper.setDetachCallback(() -> {
				onOccupy(humanOper.getSelected());
			});
			human.addHumanOper(humanOper);
			return;
		}
		// 没有空位，选择一项击破
		var toBreak = targetHuman.getAllStoreInSlot(false, false, true, true);
		if (toBreak.isEmpty()) {
			setDone();
			return;
		}
		var humanOper = new SelectCardOper(toBreak, actionString.tips[1], 1);
		humanOper.setDetachCallback(() -> {
			onBreak(humanOper.getSelectedPile().getAny());
		});
		targetHuman.addHumanOper(humanOper);
	}

	private boolean checkDefendSkill(Human human, Card card, Skill skill, SkillAura aura) {
		if (triggeredSkillList.contains(skill)) {
			return false;
		}
		if (skill.testTriggerable(SkillTrigger.Defend, aura)) {
			triggeredSkillList.add(skill);
			skill.setParam("attackAction", this);
			board.applySkill(skill);
			setDone();
			return true;
		}
		return false;
	}

	private void onOccupy(int index) {
		addFirst(new RefreshSlotAction());
		board.removeCard(card);
		this.attackResult = AttackResult.Occupy;
		var cardSlot = targetHuman.cardSlotMap.get(index);
		cardSlot.getPile(ESlotType.Seal).add(card);
		board.modifyCard(card);
		setDone();
		Log.game.info("侵占成功");
	}

	private void onBreak(Card breakCard) {
		addFirst(new BreakFinishAction(human));
		addFirst(new DiscardCardAction(human, card));

		this.attackResult = AttackResult.Break;
		addFirst(new DiscardCardAction(targetHuman, targetHuman.getCardSlotByCard(breakCard).getAllCards()));
		setDone();
		Log.game.info("击破");
	}

	public AttackResult getAttackResult() {
		return attackResult;
	}

	@Override
	public void setDone() {
		isAttackDone = true;
	}

	public void setDone(boolean isAttackDone) {
		this.isAttackDone = isAttackDone;
	}

	@Override
	public boolean isDone() {
		return isAttackDone;
	}
}
