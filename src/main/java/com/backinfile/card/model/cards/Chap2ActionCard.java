package com.backinfile.card.model.cards;

import com.backinfile.card.model.skills.HarassSkill;
import com.backinfile.card.model.skills.Release2StoreSkill;
import com.backinfile.card.model.skills.ReleaseAttackStoreSkill;
import com.backinfile.card.model.skills.RideSkill;

public class Chap2ActionCard extends ActionCard {
	public Chap2ActionCard() {
		this.chapter = 2;
	}

	public static class Attack extends Chap2ActionCard {
		public Attack() {
			addSkill(new ReleaseAttackStoreSkill());
			addSkill(new Release2StoreSkill());
		}
	}

	public static class Harass extends Chap2ActionCard {
		public Harass() {
			addSkill(new HarassSkill());
		}
	}

	public static class Ride extends Chap2ActionCard {
		public Ride() {
			addSkill(new RideSkill());
		}
	}

	public static class Recall extends Chap2ActionCard {
		public Recall() {
		}
	}

}
