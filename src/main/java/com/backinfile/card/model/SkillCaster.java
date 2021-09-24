package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.backinfile.card.model.Skill.EmptySkill;

public abstract class SkillCaster {
	protected List<Skill> skills = new ArrayList<>();

	public List<Skill> getSkillList() {
		return new ArrayList<Skill>(skills);
	}

	public Skill getSkill(long id) {
		for (var skill : skills) {
			if (skill.id == id) {
				return skill;
			}
		}
		return null;
	}

	public final boolean removeSkill(long id) {
		return skills.removeIf(s -> s.id == id);
	}

	public final boolean removeSkillIf(Predicate<Skill> predicate) {
		return skills.removeIf(predicate);
	}

	public final Skill getSkillOrEmpty(long id) {
		for (var skill : skills) {
			if (skill.id == id) {
				return skill;
			}
		}
		return EmptySkill.Instance;
	}

	@SuppressWarnings("unchecked")
	public <T extends Skill> T getSkill(Class<T> clazz) {
		for (var skill : skills) {
			if (skill.getClass() == clazz) {
				return (T) skill;
			}
		}
		return null;
	}

	public Skill getSkill(Predicate<Skill> predicate) {
		for (var skill : skills) {
			if (predicate.test(skill)) {
				return skill;
			}
		}
		return null;
	}

	public final void addSkill(Skill skill) {
		skills.add(skill);
	}
}
