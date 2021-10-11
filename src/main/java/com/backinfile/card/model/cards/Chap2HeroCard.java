package com.backinfile.card.model.cards;

import com.backinfile.card.model.skills.BeeKeeperActiveSkill;

public class Chap2HeroCard extends HeroCard {
	public Chap2HeroCard() {
		this.chapter = 2;
	}

	public static class Beekeeper extends Chap2HeroCard {
		public Beekeeper() {
			addSkill(new BeeKeeperActiveSkill());
		}
	}

	public static class DreamBuilder extends Chap2HeroCard {
		public DreamBuilder() {
		}
	}
}
