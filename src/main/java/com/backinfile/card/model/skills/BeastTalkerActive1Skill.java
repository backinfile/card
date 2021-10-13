package com.backinfile.card.model.skills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.backinfile.card.gen.GameMessageHandler.DCombination;
import com.backinfile.card.model.Skill;
import com.backinfile.card.model.actions.BeastTalkerActive1Action;
import com.backinfile.card.model.cards.MonsterCard;

public class BeastTalkerActive1Skill extends Skill {
	public BeastTalkerActive1Skill() {
		setTriggerType(SkillDuration.Fixed, SkillTrigger.Active, SkillAura.Hero, 1);
	}

	@Override
	public boolean triggerable() {
		return !getCombinations().isEmpty();
	}

	@Override
	public void apply() {
		addLast(new BeastTalkerActive1Action(human, getCombinations()));
	}

	public List<DCombination> getCombinations() {
		var list = new ArrayList<DCombination>();
		for (var i = 0; i < human.handPile.size() - 1; i++) {
			for (var j = i + 1; j < human.handPile.size(); j++) {
				var card = human.handPile.get(i);
				var other = human.handPile.get(j);
				if (card instanceof MonsterCard && other instanceof MonsterCard) {
					if (((MonsterCard) card).getMonsterSkillTypes().stream()
							.anyMatch(((MonsterCard) other)::isMonsterType)) {
						continue;
					}
					var idArray = new ArrayList<Long>(Arrays.asList(card.id, other.id));
					Collections.sort(idArray);
					DCombination combination = new DCombination();
					combination.addAllIdList(idArray);
					list.add(combination);
				}
			}
		}
		return list;
	}

}
