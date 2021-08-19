package com.backinfile.card.server.proto;

import java.util.ArrayList;
import java.util.List;

public class DBoardInit {
	public long seed;
	public List<DHumanInit> humanInits = new ArrayList<>();
	public List<DHumanOper> humanOperHistory = new ArrayList<>();

	public static class SCGameInit {
		public DBoardInit boardInit;
	}

	public static class CSGameReady {
	}

	public static class SCGameStart {
	}

	public static class CSGameHumanOper {
		public DHumanOper humanOper;
	}

	public static class SCGameHumanOper {
		public DHumanOper humanOper;
	}
}
