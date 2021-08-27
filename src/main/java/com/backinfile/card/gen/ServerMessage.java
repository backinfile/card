package com.backinfile.card.gen;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.backinfile.dSync.model.DSyncBaseHandler;
import com.backinfile.dSync.model.DSyncException;
import com.backinfile.dSync.model.Mode;

public class ServerMessage extends DSyncBaseHandler {
	private Root root;
	private List<DSyncListener> listeners = new ArrayList<>();

	public ServerMessage(Mode mode) {
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
	 * 仅用于存数据
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

		public static DBoardInit newInstance(ServerMessage _handler) {
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
		
		public List<DHumanInit> getHumanInitsList() {
			return new ArrayList<>(humanInits);
		}
		
		public void setHumanInitsList(List<DHumanInit> _value) {
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
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DBoardInit)) {
				return false;
			}
			var _value = (DBoardInit) obj;
			if (this.seed != _value.seed) {
				return false;
			}
			if (!this.humanInits.equals(_value.humanInits)) {
				return false;
			}
			return true;
		}
		
		public DBoardInit copy() {
			var _value = new DBoardInit();
			_value._dSync_id = -1;
			_value.seed = this.seed;
			_value.humanInits = new ArrayList<>(this.humanInits);
			return _value;
		}
		
		
		public DBoardInit deepCopy() {
			var _value = new DBoardInit();
			_value._dSync_id = -1;
			_value.seed = this.seed;
			_value.humanInits = new ArrayList<>();
			for(var _f: this.humanInits) {
				_value.humanInits.add(_f.deepCopy());
			}
			return _value;
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

		public static DHumanInit newInstance(ServerMessage _handler) {
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
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DHumanInit)) {
				return false;
			}
			var _value = (DHumanInit) obj;
			if (!this.controllerToken.equals(_value.controllerToken)) {
				return false;
			}
			if (!this.startPileData.equals(_value.startPileData)) {
				return false;
			}
			return true;
		}
		
		public DHumanInit copy() {
			var _value = new DHumanInit();
			_value._dSync_id = -1;
			_value.controllerToken = this.controllerToken;
			_value.startPileData = this.startPileData;
			return _value;
		}
		
		
		public DHumanInit deepCopy() {
			var _value = new DHumanInit();
			_value._dSync_id = -1;
			_value.controllerToken = this.controllerToken;
			_value.startPileData = this.startPileData.deepCopy();
			return _value;
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

		public static DStartPileData newInstance(ServerMessage _handler) {
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
		
		public List<DStartPileDataPair> getPileList() {
			return new ArrayList<>(pile);
		}
		
		public void setPileList(List<DStartPileDataPair> _value) {
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
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DStartPileData)) {
				return false;
			}
			var _value = (DStartPileData) obj;
			if (!this.heroCard.equals(_value.heroCard)) {
				return false;
			}
			if (!this.pile.equals(_value.pile)) {
				return false;
			}
			return true;
		}
		
		public DStartPileData copy() {
			var _value = new DStartPileData();
			_value._dSync_id = -1;
			_value.heroCard = this.heroCard;
			_value.pile = new ArrayList<>(this.pile);
			return _value;
		}
		
		
		public DStartPileData deepCopy() {
			var _value = new DStartPileData();
			_value._dSync_id = -1;
			_value.heroCard = this.heroCard;
			_value.pile = new ArrayList<>();
			for(var _f: this.pile) {
				_value.pile.add(_f.deepCopy());
			}
			return _value;
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
		private List<DRoom> rooms;

		public static class K {
			public static final String onlinePlayers = "onlinePlayers";
			public static final String rooms = "rooms";
		}

		public Root() {
			init();
		}

		public static Root newInstance(ServerMessage _handler) {
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
			rooms = new ArrayList<>();
		}
		
		public int getOnlinePlayersCount() {
			return this.onlinePlayers.size();
		}
		
		public List<DPlayer> getOnlinePlayersList() {
			return new ArrayList<>(onlinePlayers);
		}
		
		public void setOnlinePlayersList(List<DPlayer> _value) {
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
		
		public int getRoomsCount() {
			return this.rooms.size();
		}
		
		public List<DRoom> getRoomsList() {
			return new ArrayList<>(rooms);
		}
		
		public void setRoomsList(List<DRoom> _value) {
			this.rooms.clear();
			this.rooms.addAll(_value);
			onChanged();
		}
		
		public void addRooms(DRoom _value) {
			this.rooms.add(_value);
			onChanged();
		}
		
		public void removeRooms(DRoom _value) {
			this.rooms.remove(_value);
			onChanged();
		}
		
		public void addAllRooms(List<DRoom> _value) {
			this.rooms.addAll(_value);
			onChanged();
		}
		
		public void clearRooms() {
			this.rooms.clear();
			onChanged();
		}
		

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.onlinePlayers, toJSONString(onlinePlayers));
			jsonObject.put(K.rooms, toJSONString(rooms));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			onlinePlayers = fromJSONString(jsonObject.getString(K.onlinePlayers));
			rooms = fromJSONString(jsonObject.getString(K.rooms));
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Root)) {
				return false;
			}
			var _value = (Root) obj;
			if (!this.onlinePlayers.equals(_value.onlinePlayers)) {
				return false;
			}
			if (!this.rooms.equals(_value.rooms)) {
				return false;
			}
			return true;
		}
		
		public Root copy() {
			var _value = new Root();
			_value._dSync_id = -1;
			_value.onlinePlayers = new ArrayList<>(this.onlinePlayers);
			_value.rooms = new ArrayList<>(this.rooms);
			return _value;
		}
		
		
		public Root deepCopy() {
			var _value = new Root();
			_value._dSync_id = -1;
			_value.onlinePlayers = new ArrayList<>();
			for(var _f: this.onlinePlayers) {
				_value.onlinePlayers.add(_f.deepCopy());
			}
			_value.rooms = new ArrayList<>();
			for(var _f: this.rooms) {
				_value.rooms.add(_f.deepCopy());
			}
			return _value;
		}
	}
	
	public static class DRoom extends DSyncBase {
		public static final String TypeName = "DRoom";
		
		private String token;
		private ERoomStage state;
		private boolean hide;
		private List<DPlayer> battlePlayers;
		private List<DPlayer> visitPlayers;

		public static class K {
			public static final String token = "token";
			public static final String state = "state";
			public static final String hide = "hide";
			public static final String battlePlayers = "battlePlayers";
			public static final String visitPlayers = "visitPlayers";
		}

		public DRoom() {
			init();
		}

		public static DRoom newInstance(ServerMessage _handler) {
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
			token = "";
			state = ERoomStage.Normal;
			hide = false;
			battlePlayers = new ArrayList<>();
			visitPlayers = new ArrayList<>();
		}
		
		public String getToken() {
			return token;
		}
		
		public void setToken(String token) {
			this.token = token;
			onChanged();
		}
		public ERoomStage getState() {
			return state;
		}
		
		public void setState(ERoomStage state) {
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
		
		public List<DPlayer> getBattlePlayersList() {
			return new ArrayList<>(battlePlayers);
		}
		
		public void setBattlePlayersList(List<DPlayer> _value) {
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
		
		public List<DPlayer> getVisitPlayersList() {
			return new ArrayList<>(visitPlayers);
		}
		
		public void setVisitPlayersList(List<DPlayer> _value) {
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
			jsonObject.put(K.token, token);
			jsonObject.put(K.state, state.ordinal());
			jsonObject.put(K.hide, hide);
			jsonObject.put(K.battlePlayers, toJSONString(battlePlayers));
			jsonObject.put(K.visitPlayers, toJSONString(visitPlayers));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			token = jsonObject.getString(K.token);
			state = ERoomStage.values()[(jsonObject.getIntValue(K.state))];
			hide = jsonObject.getBooleanValue(K.hide);
			battlePlayers = fromJSONString(jsonObject.getString(K.battlePlayers));
			visitPlayers = fromJSONString(jsonObject.getString(K.visitPlayers));
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DRoom)) {
				return false;
			}
			var _value = (DRoom) obj;
			if (!this.token.equals(_value.token)) {
				return false;
			}
			if (!this.state.equals(_value.state)) {
				return false;
			}
			if (this.hide != _value.hide) {
				return false;
			}
			if (!this.battlePlayers.equals(_value.battlePlayers)) {
				return false;
			}
			if (!this.visitPlayers.equals(_value.visitPlayers)) {
				return false;
			}
			return true;
		}
		
		public DRoom copy() {
			var _value = new DRoom();
			_value._dSync_id = -1;
			_value.token = this.token;
			_value.state = this.state;
			_value.hide = this.hide;
			_value.battlePlayers = new ArrayList<>(this.battlePlayers);
			_value.visitPlayers = new ArrayList<>(this.visitPlayers);
			return _value;
		}
		
		
		public DRoom deepCopy() {
			var _value = new DRoom();
			_value._dSync_id = -1;
			_value.token = this.token;
			_value.state = this.state;
			_value.hide = this.hide;
			_value.battlePlayers = new ArrayList<>();
			for(var _f: this.battlePlayers) {
				_value.battlePlayers.add(_f.deepCopy());
			}
			_value.visitPlayers = new ArrayList<>();
			for(var _f: this.visitPlayers) {
				_value.visitPlayers.add(_f.deepCopy());
			}
			return _value;
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

		public static DCard newInstance(ServerMessage _handler) {
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
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DCard)) {
				return false;
			}
			var _value = (DCard) obj;
			if (this.id != _value.id) {
				return false;
			}
			if (!this.pileType.equals(_value.pileType)) {
				return false;
			}
			return true;
		}
		
		public DCard copy() {
			var _value = new DCard();
			_value._dSync_id = -1;
			_value.id = this.id;
			_value.pileType = this.pileType;
			return _value;
		}
		
		
		public DCard deepCopy() {
			var _value = new DCard();
			_value._dSync_id = -1;
			_value.id = this.id;
			_value.pileType = this.pileType;
			return _value;
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

		public static DStartPileDataPair newInstance(ServerMessage _handler) {
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
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DStartPileDataPair)) {
				return false;
			}
			var _value = (DStartPileDataPair) obj;
			if (!this.card.equals(_value.card)) {
				return false;
			}
			if (this.count != _value.count) {
				return false;
			}
			return true;
		}
		
		public DStartPileDataPair copy() {
			var _value = new DStartPileDataPair();
			_value._dSync_id = -1;
			_value.card = this.card;
			_value.count = this.count;
			return _value;
		}
		
		
		public DStartPileDataPair deepCopy() {
			var _value = new DStartPileDataPair();
			_value._dSync_id = -1;
			_value.card = this.card;
			_value.count = this.count;
			return _value;
		}
	}
	
	public static class DPlayer extends DSyncBase {
		public static final String TypeName = "DPlayer";
		
		private String token;
		private String name;
		private String roomToken;
		private EPlayerState state;

		public static class K {
			public static final String token = "token";
			public static final String name = "name";
			public static final String roomToken = "roomToken";
			public static final String state = "state";
		}

		public DPlayer() {
			init();
		}

		public static DPlayer newInstance(ServerMessage _handler) {
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
			state = EPlayerState.Normal;
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
		public EPlayerState getState() {
			return state;
		}
		
		public void setState(EPlayerState state) {
			this.state = state;
			onChanged();
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.name, name);
			jsonObject.put(K.roomToken, roomToken);
			jsonObject.put(K.state, state.ordinal());
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			token = jsonObject.getString(K.token);
			name = jsonObject.getString(K.name);
			roomToken = jsonObject.getString(K.roomToken);
			state = EPlayerState.values()[(jsonObject.getIntValue(K.state))];
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DPlayer)) {
				return false;
			}
			var _value = (DPlayer) obj;
			if (!this.token.equals(_value.token)) {
				return false;
			}
			if (!this.name.equals(_value.name)) {
				return false;
			}
			if (!this.roomToken.equals(_value.roomToken)) {
				return false;
			}
			if (!this.state.equals(_value.state)) {
				return false;
			}
			return true;
		}
		
		public DPlayer copy() {
			var _value = new DPlayer();
			_value._dSync_id = -1;
			_value.token = this.token;
			_value.name = this.name;
			_value.roomToken = this.roomToken;
			_value.state = this.state;
			return _value;
		}
		
		
		public DPlayer deepCopy() {
			var _value = new DPlayer();
			_value._dSync_id = -1;
			_value.token = this.token;
			_value.name = this.name;
			_value.roomToken = this.roomToken;
			_value.state = this.state;
			return _value;
		}
	}
	


	public static enum EPileType {
		None,
		/** 手牌 */
		HandPile,
		/** 牌库 */
		DrawPile,
	}
	public static enum ERoomStage {
		Normal,
		Battle,
	}
	public static enum EPlayerState {
		Normal,
		Game,
	}
}

