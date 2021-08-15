package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.backinfile.card.model.skills.EmptySkill;

public abstract class SkillCaster {
	protected List<Skill> skills = new ArrayList<>();

	public final Skill getSkill(long id) {
		for (var skill : skills) {
			if (skill.id == id) {
				return skill;
			}
		}
		return null;
	}

	public final Skill getSkillOrEmpty(long id) {
		for (var skill : skills) {
			if (skill.id == id) {
				return skill;
			}
		}
		return EmptySkill.Instance;
	}

	public final Skill getSkill(String skillName) {
		for (var skill : skills) {
			if (skill.name.equals(skillName)) {
				return skill;
			}
		}
		return null;

	}

	public final boolean containsSkill(String skillName) {
		return getSkill(skillName) != null;
	}
	
	public final void addSkill(Skill skill) {
		skills.add(skill);
	}

}
