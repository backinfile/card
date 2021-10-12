package com.backinfile.card.model.cards;

import com.backinfile.card.model.skills.BeeKeeperActiveSkill;
import com.backinfile.card.model.skills.BirdRideOutTurnSkill;
import com.backinfile.card.model.skills.RecallSkill;
import com.backinfile.card.model.skills.SeaTalkerActiveSkill;
import com.backinfile.card.model.skills.WindSeekerSkill;

public class Chap2HeroCard extends HeroCard {
	public Chap2HeroCard() {
		this.chapter = 2;
		addSkill(new BirdRideOutTurnSkill());
		addSkill(new RecallSkill());
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

	public static class SeaTalker extends Chap2HeroCard {
		public SeaTalker() {
			addSkill(new SeaTalkerActiveSkill());
		}
	}

	public static class HeartFire extends Chap2HeroCard {
		public HeartFire() {
		}
	}

	public static class WindSeeker extends Chap2HeroCard {
		public WindSeeker() {
			addSkill(new WindSeekerSkill());
		}
	}

	public static class DragonKnight extends Chap2HeroCard {
		public DragonKnight() {
		}
	}
}
