package com.backinfile.card.server.proto;

import java.util.HashMap;
import java.util.Map;

public class DHumanInit {
	public String controllerToken;
	public StartPileData startPileData = null;

	public class StartPileData {
		public String heroCard = "";
		public Map<String, Integer> pile = new HashMap<>(); // cardSn->number
	}
}
