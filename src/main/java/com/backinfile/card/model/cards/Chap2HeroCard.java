package com.backinfile.card.model.cards;

import com.backinfile.card.model.skills.BeastTalkerActive1Skill;
import com.backinfile.card.model.skills.BeastTalkerActive2Skill;
import com.backinfile.card.model.skills.BeastWarriorActive1Skill;
import com.backinfile.card.model.skills.BeastWarriorActive2Skill;
import com.backinfile.card.model.skills.BeeKeeperActiveSkill;
import com.backinfile.card.model.skills.BirdRideOutTurnSkill;
import com.backinfile.card.model.skills.BlackTurtleDefendSkill;
import com.backinfile.card.model.skills.BlackTurtleRecallSkill;
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

	public static class BeastWarrior extends Chap2HeroCard {
		public BeastWarrior() {
			addSkill(new BeastWarriorActive1Skill());
			addSkill(new BeastWarriorActive2Skill());
		}
	}

	public static class BeastTalker extends Chap2HeroCard {
		public BeastTalker() {
			addSkill(new BeastTalkerActive1Skill());
			addSkill(new BeastTalkerActive2Skill());
		}
	}

	public static class RedPhoenix extends Chap2HeroCard {
		public RedPhoenix() {
		}
	}

	public static class BlackTurtle extends Chap2HeroCard {
		public BlackTurtle() {
			addSkill(new BlackTurtleRecallSkill());
			addSkill(new BlackTurtleDefendSkill());
		}
	}

	public static class WhiteTiger extends Chap2HeroCard {
		public WhiteTiger() {
		}
	}

	public static class CyanDragon extends Chap2HeroCard {
		public CyanDragon() {
		}
	}
}
