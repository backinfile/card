package com.backinfile.card.server.proto;

import java.util.ArrayList;
import java.util.List;

public class DRoom {
	public String roomToken;
	public int state; // 0-normal 1-battle
	public boolean hide = false;
	public List<DPlayer> battlePlayers = new ArrayList<>();
	public List<DPlayer> visitPlayers = new ArrayList<>();

	public static class CSRoomCreate {
	}

	public static class CSRoomJoin {
		public String roomToken;
	}

	public static class SCRoomJoin {
		public DRoom room;
	}

	public static class SCRoomUpdate {
		public DRoom room;
	}

	public static class CSRoomLeave {
	}

	public static class SCRoomLeave {
	}

	public static class CSRoomChangePos {
		public int changeTo; // 0-visitor 1-battle
		public DHumanInit humanInitData;
	}

	public static class CSRoomList {
	}

	public static class SCRoomList {
		public List<DRoom> rooms = new ArrayList<>();
	}
}