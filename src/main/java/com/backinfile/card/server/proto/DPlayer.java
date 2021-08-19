package com.backinfile.card.server.proto;

import java.util.ArrayList;
import java.util.List;

public class DPlayer {
	public String token;
	public String name;
	public String roomToken;
	public int state = 0; // 0-空闲 1-忙碌 2-隐身

	public static class CSOnlinePulse {
		public DPlayer self;
	}

	public static class CSPlayerList {
	}

	public static class SCPlayerList {
		public List<DPlayer> players = new ArrayList<>();
	}
}