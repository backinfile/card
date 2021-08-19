package com.backinfile.card.server.proto;

import java.util.HashMap;
import java.util.Map;

public class DHumanInit {
	public StartPileData startPileData = null;
	public String startHeroCard = "";

	public class StartPileData {
		public Map<String, Integer> pile = new HashMap<>(); // cardSn->number
	}
}
