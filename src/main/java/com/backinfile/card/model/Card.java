package com.backinfile.card.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Card {
	public final int id;
	public final int sn;

	private Map<String, Skill> skillMap = new HashMap<>();

	public Card(int id, int sn) {
		this.id = id;
		this.sn = sn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Card) {
			return ((Card) obj).id == this.id;
		}
		return false;
	}

}
