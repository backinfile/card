package com.backinfile.card.gen;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.backinfile.dSync.model.DSyncBaseHandler;
import com.backinfile.dSync.model.DSyncException;
import com.backinfile.dSync.model.Mode;

public class MessageHandler extends DSyncBaseHandler {
	private Root root;
	private List<DSyncListener> listeners = new ArrayList<>();

	public MessageHandler(Mode mode) {
		super(mode);
		root = new Root();
		put(root);
		root.sync();
	}

	public Root getRoot() {
		return root;
	}
	
	@Override
	protected void onReceiveChangeLog(long id) {
		var obj = get(id);
		if (obj == null) {
			return;
		}
		if (obj instanceof DBoardInit) {
			for (var listenr : listeners) {
				listenr.onDataChange((DBoardInit) (obj));
			}
			return;
		}
		if (obj instanceof DHumanInit) {
			for (var listenr : listeners) {
				listenr.onDataChange((DHumanInit) (obj));
			}
			return;
		}
		if (obj instanceof DStartPileData) {
			for (var listenr : listeners) {
				listenr.onDataChange((DStartPileData) (obj));
			}
			return;
		}
		if (obj instanceof Root) {
			for (var listenr : listeners) {
				listenr.onDataChange((Root) (obj));
			}
			return;
		}
		if (obj instanceof DRoom) {
			for (var listenr : listeners) {
				listenr.onDataChange((DRoom) (obj));
			}
			return;
		}
		if (obj instanceof DCard) {
			for (var listenr : listeners) {
				listenr.onDataChange((DCard) (obj));
			}
			return;
		}
		if (obj instanceof DStartPileDataPair) {
			for (var listenr : listeners) {
				listenr.onDataChange((DStartPileDataPair) (obj));
			}
			return;
		}
		if (obj instanceof DPlayer) {
			for (var listenr : listeners) {
				listenr.onDataChange((DPlayer) (obj));
			}
			return;
		}
	}
	
	public void addListener(DSyncListener listener) {
		if (mode != Mode.Client) {
			throw new DSyncException("非Client模式下，不能监听数据对象");
		}
		listeners.add(listener);
	}
	
	
	public static abstract class DSyncListener {
		public void onDataChange(DBoardInit data) {
		}
		
		public void onDataChange(DHumanInit data) {
		}
		
		public void onDataChange(DStartPileData data) {
		}
		
		public void onDataChange(Root data) {
		}
		
		public void onDataChange(DRoom data) {
		}
		
		public void onDataChange(DCard data) {
		}
		
		public void onDataChange(DStartPileDataPair data) {
		}
		
		public void onDataChange(DPlayer data) {
		}
		
	}

	@Override
	protected DSyncBase newDSyncInstance(String typeName) {
		switch (typeName) {
		case DBoardInit.TypeName:
			return new DBoardInit();
		case DHumanInit.TypeName:
			return new DHumanInit();
		case DStartPileData.TypeName:
			return new DStartPileData();
		case Root.TypeName:
			return new Root();
		case DRoom.TypeName:
			return new DRoom();
		case DCard.TypeName:
			return new DCard();
		case DStartPileDataPair.TypeName:
			return new DStartPileDataPair();
		case DPlayer.TypeName:
			return new DPlayer();
		default:
			return null;
		}
	}

	/**
	 * --------------------
	 * 对战初始化
	 * --------------------
	 */
	public static class DBoardInit extends DSyncBase {
		public static final String TypeName = "DBoardInit";
		
		private long seed;
		private List<DHumanInit> humanInits;

		public static class K {
			public static final String seed = "seed";
			public static final String humanInits = "humanInits";
		}

		public DBoardInit() {
			init();
		}

		public static DBoardInit newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DBoardInit _struct = new DBoardInit();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			seed = 0;
			humanInits = new ArrayList<>();
		}
		
		public long getSeed() {
			return seed;
		}
		
		public void setSeed(long seed) {
			this.seed = seed;
			onChanged();
		}
		public int getHumanInitsCount() {
			return this.humanInits.size();
		}
		
		public List<DHumanInit> getAllHumanInits() {
			return new ArrayList<>(humanInits);
		}
		
		public void setAllHumanInits(List<DHumanInit> _value) {
			this.humanInits.clear();
			this.humanInits.addAll(_value);
			onChanged();
		}
		
		public void addHumanInits(DHumanInit _value) {
			this.humanInits.add(_value);
			onChanged();
		}
		
		public void removeHumanInits(DHumanInit _value) {
			this.humanInits.remove(_value);
			onChanged();
		}
		
		public void addAllHumanInits(List<DHumanInit> _value) {
			this.humanInits.addAll(_value);
			onChanged();
		}
		
		public void clearHumanInits() {
			this.humanInits.clear();
			onChanged();
		}
		

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.seed, seed);
			jsonObject.put(K.humanInits, toJSONString(humanInits));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			seed = jsonObject.getLongValue(K.seed);
			humanInits = fromJSONString(jsonObject.getString(K.humanInits));
		}
	}
	
	public static class DHumanInit extends DSyncBase {
		public static final String TypeName = "DHumanInit";
		
		private String controllerToken;
		private DStartPileData startPileData;

		public static class K {
			public static final String controllerToken = "controllerToken";
			public static final String startPileData = "startPileData";
		}

		public DHumanInit() {
			init();
		}

		public static DHumanInit newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DHumanInit _struct = new DHumanInit();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			controllerToken = "";
			startPileData = null;
		}
		
		public String getControllerToken() {
			return controllerToken;
		}
		
		public void setControllerToken(String controllerToken) {
			this.controllerToken = controllerToken;
			onChanged();
		}
		public DStartPileData getStartPileData() {
			return startPileData;
		}
		
		public void setStartPileData(DStartPileData startPileData) {
			this.startPileData = startPileData;
			onChanged();
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.controllerToken, controllerToken);
			jsonObject.put(K.startPileData, startPileData.get_dSync_id());
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			controllerToken = jsonObject.getString(K.controllerToken);
			startPileData = (DStartPileData) handler.get(jsonObject.getLongValue(K.startPileData));
		}
	}
	
	public static class DStartPileData extends DSyncBase {
		public static final String TypeName = "DStartPileData";
		
		private String heroCard;
		private List<DStartPileDataPair> pile;

		public static class K {
			public static final String heroCard = "heroCard";
			public static final String pile = "pile";
		}

		public DStartPileData() {
			init();
		}

		public static DStartPileData newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DStartPileData _struct = new DStartPileData();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			heroCard = "";
			pile = new ArrayList<>();
		}
		
		public String getHeroCard() {
			return heroCard;
		}
		
		public void setHeroCard(String heroCard) {
			this.heroCard = heroCard;
			onChanged();
		}
		public int getPileCount() {
			return this.pile.size();
		}
		
		public List<DStartPileDataPair> getAllPile() {
			return new ArrayList<>(pile);
		}
		
		public void setAllPile(List<DStartPileDataPair> _value) {
			this.pile.clear();
			this.pile.addAll(_value);
			onChanged();
		}
		
		public void addPile(DStartPileDataPair _value) {
			this.pile.add(_value);
			onChanged();
		}
		
		public void removePile(DStartPileDataPair _value) {
			this.pile.remove(_value);
			onChanged();
		}
		
		public void addAllPile(List<DStartPileDataPair> _value) {
			this.pile.addAll(_value);
			onChanged();
		}
		
		public void clearPile() {
			this.pile.clear();
			onChanged();
		}
		

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.heroCard, heroCard);
			jsonObject.put(K.pile, toJSONString(pile));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			heroCard = jsonObject.getString(K.heroCard);
			pile = fromJSONString(jsonObject.getString(K.pile));
		}
	}
	
	/**
	 * --------------------
	 * 服务器信息
	 * --------------------
	 */
	public static class Root extends DSyncBase {
		public static final String TypeName = "Root";
		
		private List<DPlayer> onlinePlayers;

		public static class K {
			public static final String onlinePlayers = "onlinePlayers";
		}

		public Root() {
			init();
		}

		public static Root newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			Root _struct = new Root();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			onlinePlayers = new ArrayList<>();
		}
		
		public int getOnlinePlayersCount() {
			return this.onlinePlayers.size();
		}
		
		public List<DPlayer> getAllOnlinePlayers() {
			return new ArrayList<>(onlinePlayers);
		}
		
		public void setAllOnlinePlayers(List<DPlayer> _value) {
			this.onlinePlayers.clear();
			this.onlinePlayers.addAll(_value);
			onChanged();
		}
		
		public void addOnlinePlayers(DPlayer _value) {
			this.onlinePlayers.add(_value);
			onChanged();
		}
		
		public void removeOnlinePlayers(DPlayer _value) {
			this.onlinePlayers.remove(_value);
			onChanged();
		}
		
		public void addAllOnlinePlayers(List<DPlayer> _value) {
			this.onlinePlayers.addAll(_value);
			onChanged();
		}
		
		public void clearOnlinePlayers() {
			this.onlinePlayers.clear();
			onChanged();
		}
		

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.onlinePlayers, toJSONString(onlinePlayers));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			onlinePlayers = fromJSONString(jsonObject.getString(K.onlinePlayers));
		}
	}
	
	public static class DRoom extends DSyncBase {
		public static final String TypeName = "DRoom";
		
		private String roomToken;
		/** 0-normal 1-battle */
		private int state;
		private boolean hide;
		private List<DPlayer> battlePlayers;
		private List<DPlayer> visitPlayers;

		public static class K {
			public static final String roomToken = "roomToken";
			public static final String state = "state";
			public static final String hide = "hide";
			public static final String battlePlayers = "battlePlayers";
			public static final String visitPlayers = "visitPlayers";
		}

		public DRoom() {
			init();
		}

		public static DRoom newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DRoom _struct = new DRoom();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			roomToken = "";
			state = 0;
			hide = false;
			battlePlayers = new ArrayList<>();
			visitPlayers = new ArrayList<>();
		}
		
		public String getRoomToken() {
			return roomToken;
		}
		
		public void setRoomToken(String roomToken) {
			this.roomToken = roomToken;
			onChanged();
		}
		/** 0-normal 1-battle */
		public int getState() {
			return state;
		}
		
		/** 0-normal 1-battle */
		public void setState(int state) {
			this.state = state;
			onChanged();
		}
		public boolean getHide() {
			return hide;
		}
		
		public void setHide(boolean hide) {
			this.hide = hide;
			onChanged();
		}
		public int getBattlePlayersCount() {
			return this.battlePlayers.size();
		}
		
		public List<DPlayer> getAllBattlePlayers() {
			return new ArrayList<>(battlePlayers);
		}
		
		public void setAllBattlePlayers(List<DPlayer> _value) {
			this.battlePlayers.clear();
			this.battlePlayers.addAll(_value);
			onChanged();
		}
		
		public void addBattlePlayers(DPlayer _value) {
			this.battlePlayers.add(_value);
			onChanged();
		}
		
		public void removeBattlePlayers(DPlayer _value) {
			this.battlePlayers.remove(_value);
			onChanged();
		}
		
		public void addAllBattlePlayers(List<DPlayer> _value) {
			this.battlePlayers.addAll(_value);
			onChanged();
		}
		
		public void clearBattlePlayers() {
			this.battlePlayers.clear();
			onChanged();
		}
		
		public int getVisitPlayersCount() {
			return this.visitPlayers.size();
		}
		
		public List<DPlayer> getAllVisitPlayers() {
			return new ArrayList<>(visitPlayers);
		}
		
		public void setAllVisitPlayers(List<DPlayer> _value) {
			this.visitPlayers.clear();
			this.visitPlayers.addAll(_value);
			onChanged();
		}
		
		public void addVisitPlayers(DPlayer _value) {
			this.visitPlayers.add(_value);
			onChanged();
		}
		
		public void removeVisitPlayers(DPlayer _value) {
			this.visitPlayers.remove(_value);
			onChanged();
		}
		
		public void addAllVisitPlayers(List<DPlayer> _value) {
			this.visitPlayers.addAll(_value);
			onChanged();
		}
		
		public void clearVisitPlayers() {
			this.visitPlayers.clear();
			onChanged();
		}
		

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.roomToken, roomToken);
			jsonObject.put(K.state, state);
			jsonObject.put(K.hide, hide);
			jsonObject.put(K.battlePlayers, toJSONString(battlePlayers));
			jsonObject.put(K.visitPlayers, toJSONString(visitPlayers));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			roomToken = jsonObject.getString(K.roomToken);
			state = jsonObject.getIntValue(K.state);
			hide = jsonObject.getBooleanValue(K.hide);
			battlePlayers = fromJSONString(jsonObject.getString(K.battlePlayers));
			visitPlayers = fromJSONString(jsonObject.getString(K.visitPlayers));
		}
	}
	
	public static class DCard extends DSyncBase {
		public static final String TypeName = "DCard";
		
		private long id;
		private EPileType pileType;

		public static class K {
			public static final String id = "id";
			public static final String pileType = "pileType";
		}

		public DCard() {
			init();
		}

		public static DCard newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DCard _struct = new DCard();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			id = 0;
			pileType = EPileType.None;
		}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
			onChanged();
		}
		public EPileType getPileType() {
			return pileType;
		}
		
		public void setPileType(EPileType pileType) {
			this.pileType = pileType;
			onChanged();
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.id, id);
			jsonObject.put(K.pileType, pileType.ordinal());
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			id = jsonObject.getLongValue(K.id);
			pileType = EPileType.values()[(jsonObject.getIntValue(K.pileType))];
		}
	}
	
	public static class DStartPileDataPair extends DSyncBase {
		public static final String TypeName = "DStartPileDataPair";
		
		private String card;
		private int count;

		public static class K {
			public static final String card = "card";
			public static final String count = "count";
		}

		public DStartPileDataPair() {
			init();
		}

		public static DStartPileDataPair newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DStartPileDataPair _struct = new DStartPileDataPair();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			card = "";
			count = 0;
		}
		
		public String getCard() {
			return card;
		}
		
		public void setCard(String card) {
			this.card = card;
			onChanged();
		}
		public int getCount() {
			return count;
		}
		
		public void setCount(int count) {
			this.count = count;
			onChanged();
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.card, card);
			jsonObject.put(K.count, count);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			card = jsonObject.getString(K.card);
			count = jsonObject.getIntValue(K.count);
		}
	}
	
	public static class DPlayer extends DSyncBase {
		public static final String TypeName = "DPlayer";
		
		private String token;
		private String name;
		private String roomToken;
		/** 0-空闲 1-忙碌 */
		private int state;

		public static class K {
			public static final String token = "token";
			public static final String name = "name";
			public static final String roomToken = "roomToken";
			public static final String state = "state";
		}

		public DPlayer() {
			init();
		}

		public static DPlayer newInstance(MessageHandler _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DPlayer _struct = new DPlayer();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			token = "";
			name = "";
			roomToken = "";
			state = 0;
		}
		
		public String getToken() {
			return token;
		}
		
		public void setToken(String token) {
			this.token = token;
			onChanged();
		}
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
			onChanged();
		}
		public String getRoomToken() {
			return roomToken;
		}
		
		public void setRoomToken(String roomToken) {
			this.roomToken = roomToken;
			onChanged();
		}
		/** 0-空闲 1-忙碌 */
		public int getState() {
			return state;
		}
		
		/** 0-空闲 1-忙碌 */
		public void setState(int state) {
			this.state = state;
			onChanged();
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.name, name);
			jsonObject.put(K.roomToken, roomToken);
			jsonObject.put(K.state, state);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			token = jsonObject.getString(K.token);
			name = jsonObject.getString(K.name);
			roomToken = jsonObject.getString(K.roomToken);
			state = jsonObject.getIntValue(K.state);
		}
	}
	


	public static enum EPileType {
		None,
		/** 手牌 */
		HandPile,
		/** 牌库 */
		DrawPile,
	}
}

