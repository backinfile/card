package com.backinfile.card.server.proto;

public class DChat {
	public int channel; // 0-world 1-room
	public String content;

	public static class CSChat {
		public DChat chat;
	}

	public static class SCChat {
		public DPlayer sender;
		public DChat chat;
	}
}
