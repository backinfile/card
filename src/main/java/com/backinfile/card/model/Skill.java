package com.backinfile.card.model;

import java.util.List;

public abstract class Skill {
	public boolean passive = true;
	public int targetCardNumber = 0;
	public String key;

	public void init() {
	}

	public void apply(Board board, Human hero, Card card, List<Card> targetCards) {

	}
}
