package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.List;

import com.backinfile.card.model.Skill.EmptySkill;

public abstract class SkillCaster {
	protected List<Skill> skills = new ArrayList<>();
	
	public final List<Skill> getSkillList() {
		return skills;
	}

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

	public final void addSkill(Skill skill) {
		skills.add(skill);
	}

}
