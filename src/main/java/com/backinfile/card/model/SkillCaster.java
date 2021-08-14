package com.backinfile.card.model;

import java.util.HashMap;
import java.util.Map;

import com.backinfile.card.model.skills.EmptySkill;

public abstract class SkillCaster {
	protected Map<Long, Skill> skillMap = new HashMap<>();

	public final Skill getSkill(long id) {
		return skillMap.get(id);
	}

	public final Skill getSkillOrEmpty(long id) {
		return skillMap.getOrDefault(id, EmptySkill.Instance);
	}

	public final Skill getSkill(String skillName) {
		for (var skill : skillMap.values()) {
			if (skill.name.equals(skillName)) {
				return skill;
			}
		}
		return null;

	}

	public final boolean containsSkill(String skillName) {
		return getSkill(skillName) != null;
	}

}
