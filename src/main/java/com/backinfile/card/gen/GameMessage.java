package com.backinfile.card.gen;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.backinfile.dSync.model.DSyncBaseHandler;

@SuppressWarnings("unused")
public class GameMessage extends DSyncBaseHandler {
	private List<DSyncListener> listeners = new ArrayList<>();

	public void addListener(DSyncListener listener) {
		listeners.add(listener);
	}
	
	public static abstract class DSyncListener {
		public void onMessage(CSJoinRoom data) {
		}
		
		public void onMessage(DBoardInit data) {
		}
		
		public void onMessage(Root data) {
		}
		
		public void onMessage(DServer data) {
		}
		
		public void onMessage(DClientPlayerInfo data) {
		}
		
		public void onMessage(DStartPileDataPair data) {
		}
		
		public void onMessage(DPlayer data) {
		}
		
		public void onMessage(DHumanInit data) {
		}
		
		public void onMessage(CSCreateRoom data) {
		}
		
		public void onMessage(DRoom data) {
		}
		
		public void onMessage(DHumanOper data) {
		}
		
	}
	
	public void onMessage(String string) {
		var jsonObject = JSONObject.parseObject(string);
		String typeName = jsonObject.getString(DSyncBase.K.TypeName);
		switch (typeName) {
		case CSJoinRoom.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSJoinRoom.parseJSONObject(jsonObject));
			}
			break;
		case DBoardInit.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DBoardInit.parseJSONObject(jsonObject));
			}
			break;
		case Root.TypeName:
			for (var listener : listeners) {
				listener.onMessage(Root.parseJSONObject(jsonObject));
			}
			break;
		case DServer.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DServer.parseJSONObject(jsonObject));
			}
			break;
		case DClientPlayerInfo.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DClientPlayerInfo.parseJSONObject(jsonObject));
			}
			break;
		case DStartPileDataPair.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DStartPileDataPair.parseJSONObject(jsonObject));
			}
			break;
		case DPlayer.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DPlayer.parseJSONObject(jsonObject));
			}
			break;
		case DHumanInit.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DHumanInit.parseJSONObject(jsonObject));
			}
			break;
		case CSCreateRoom.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSCreateRoom.parseJSONObject(jsonObject));
			}
			break;
		case DRoom.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DRoom.parseJSONObject(jsonObject));
			}
			break;
		case DHumanOper.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DHumanOper.parseJSONObject(jsonObject));
			}
			break;
		}
	}

	protected static DSyncBase newDSyncInstance(String typeName) {
		switch (typeName) {
		case CSJoinRoom.TypeName:
			return new CSJoinRoom();
		case DBoardInit.TypeName:
			return new DBoardInit();
		case Root.TypeName:
			return new Root();
		case DServer.TypeName:
			return new DServer();
		case DClientPlayerInfo.TypeName:
			return new DClientPlayerInfo();
		case DStartPileDataPair.TypeName:
			return new DStartPileDataPair();
		case DPlayer.TypeName:
			return new DPlayer();
		case DHumanInit.TypeName:
			return new DHumanInit();
		case CSCreateRoom.TypeName:
			return new CSCreateRoom();
		case DRoom.TypeName:
			return new DRoom();
		case DHumanOper.TypeName:
			return new DHumanOper();
		default:
			return null;
		}
	}

	public static class CSJoinRoom extends DSyncBase {
		public static final String TypeName = "CSJoinRoom";
		

		public static class K {
		}

		public CSJoinRoom() {
			init();
		}

		@Override
		protected void init() {
		}
		

		static CSJoinRoom parseJSONObject(JSONObject jsonObject) {
			var _value = new CSJoinRoom();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSJoinRoom> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSJoinRoom>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSJoinRoom();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CSJoinRoom)) {
				return false;
			}
			var _value = (CSJoinRoom) obj;
			return true;
		}
		
		public CSJoinRoom copy() {
			var _value = new CSJoinRoom();
			return _value;
		}
		
		public CSJoinRoom deepCopy() {
			var _value = new CSJoinRoom();
			return _value;
		}
	}
	
	/**
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
		}

		public void addHumanInits(DHumanInit _value) {
			this.humanInits.add(_value);
		}
		
		public void addAllHumanInits(List<DHumanInit> _value) {
			this.humanInits.addAll(_value);
		}
		
		public void clearHumanInits() {
			this.humanInits.clear();
		}
		

		static DBoardInit parseJSONObject(JSONObject jsonObject) {
			var _value = new DBoardInit();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DBoardInit> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DBoardInit>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DBoardInit();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.seed, seed);
			jsonObject.put(K.humanInits, getJSONArray(humanInits));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			seed = jsonObject.getLongValue(K.seed);
			humanInits = DHumanInit.parseJSONArray(jsonObject.getJSONArray(K.humanInits));
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
			_value.seed = this.seed;
			_value.humanInits = new ArrayList<>(this.humanInits);
			return _value;
		}
		
		public DBoardInit deepCopy() {
			var _value = new DBoardInit();
			_value.seed = this.seed;
			_value.humanInits = new ArrayList<>();
			for(var _f: this.humanInits) {
				if (_f != null) {
					_value.humanInits.add(_f.deepCopy());
				} else {
					_value.humanInits.add(null);
				}
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
		

		public static class K {
		}

		public Root() {
			init();
		}

		@Override
		protected void init() {
		}
		

		static Root parseJSONObject(JSONObject jsonObject) {
			var _value = new Root();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<Root> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<Root>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new Root();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
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
			return true;
		}
		
		public Root copy() {
			var _value = new Root();
			return _value;
		}
		
		public Root deepCopy() {
			var _value = new Root();
			return _value;
		}
	}
	
	public static class DServer extends DSyncBase {
		public static final String TypeName = "DServer";
		
		private List<DPlayer> onlinePlayerList;
		private List<DRoom> roomList;
		private DRoom myRoom;

		public static class K {
			public static final String onlinePlayerList = "onlinePlayerList";
			public static final String roomList = "roomList";
			public static final String myRoom = "myRoom";
		}

		public DServer() {
			init();
		}

		@Override
		protected void init() {
			onlinePlayerList = new ArrayList<>();
			roomList = new ArrayList<>();
			myRoom = null;
		}
		
		public int getOnlinePlayerListCount() {
			return this.onlinePlayerList.size();
		}
		
		public List<DPlayer> getOnlinePlayerListList() {
			return new ArrayList<>(onlinePlayerList);
		}
		
		public void setOnlinePlayerListList(List<DPlayer> _value) {
			this.onlinePlayerList.clear();
			this.onlinePlayerList.addAll(_value);
		}

		public void addOnlinePlayerList(DPlayer _value) {
			this.onlinePlayerList.add(_value);
		}
		
		public void addAllOnlinePlayerList(List<DPlayer> _value) {
			this.onlinePlayerList.addAll(_value);
		}
		
		public void clearOnlinePlayerList() {
			this.onlinePlayerList.clear();
		}
		
		public int getRoomListCount() {
			return this.roomList.size();
		}
		
		public List<DRoom> getRoomListList() {
			return new ArrayList<>(roomList);
		}
		
		public void setRoomListList(List<DRoom> _value) {
			this.roomList.clear();
			this.roomList.addAll(_value);
		}

		public void addRoomList(DRoom _value) {
			this.roomList.add(_value);
		}
		
		public void addAllRoomList(List<DRoom> _value) {
			this.roomList.addAll(_value);
		}
		
		public void clearRoomList() {
			this.roomList.clear();
		}
		
		public DRoom getMyRoom() {
			return myRoom;
		}
		
		public void setMyRoom(DRoom myRoom) {
			this.myRoom = myRoom;
		}
		

		static DServer parseJSONObject(JSONObject jsonObject) {
			var _value = new DServer();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DServer> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DServer>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DServer();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.onlinePlayerList, getJSONArray(onlinePlayerList));
			jsonObject.put(K.roomList, getJSONArray(roomList));
			jsonObject.put(K.myRoom, getJSONObject(myRoom));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			onlinePlayerList = DPlayer.parseJSONArray(jsonObject.getJSONArray(K.onlinePlayerList));
			roomList = DRoom.parseJSONArray(jsonObject.getJSONArray(K.roomList));
			myRoom = DRoom.parseJSONObject(jsonObject.getJSONObject(K.myRoom));
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DServer)) {
				return false;
			}
			var _value = (DServer) obj;
			if (!this.onlinePlayerList.equals(_value.onlinePlayerList)) {
				return false;
			}
			if (!this.roomList.equals(_value.roomList)) {
				return false;
			}
			if (!this.myRoom.equals(_value.myRoom)) {
				return false;
			}
			return true;
		}
		
		public DServer copy() {
			var _value = new DServer();
			_value.onlinePlayerList = new ArrayList<>(this.onlinePlayerList);
			_value.roomList = new ArrayList<>(this.roomList);
			_value.myRoom = this.myRoom;
			return _value;
		}
		
		public DServer deepCopy() {
			var _value = new DServer();
			_value.onlinePlayerList = new ArrayList<>();
			for(var _f: this.onlinePlayerList) {
				if (_f != null) {
					_value.onlinePlayerList.add(_f.deepCopy());
				} else {
					_value.onlinePlayerList.add(null);
				}
			}
			_value.roomList = new ArrayList<>();
			for(var _f: this.roomList) {
				if (_f != null) {
					_value.roomList.add(_f.deepCopy());
				} else {
					_value.roomList.add(null);
				}
			}
			if (this.myRoom != null) {
				_value.myRoom = this.myRoom.deepCopy();
			}
			return _value;
		}
	}
	
	/**
	 * --------------------
	 * 对战初始化
	 * --------------------
	 */
	public static class DClientPlayerInfo extends DSyncBase {
		public static final String TypeName = "DClientPlayerInfo";
		
		private String token;
		private String name;

		public static class K {
			public static final String token = "token";
			public static final String name = "name";
		}

		public DClientPlayerInfo() {
			init();
		}

		@Override
		protected void init() {
			token = "";
			name = "";
		}
		
		public String getToken() {
			return token;
		}
		
		public void setToken(String token) {
			this.token = token;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		

		static DClientPlayerInfo parseJSONObject(JSONObject jsonObject) {
			var _value = new DClientPlayerInfo();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DClientPlayerInfo> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DClientPlayerInfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DClientPlayerInfo();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.name, name);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			token = jsonObject.getString(K.token);
			name = jsonObject.getString(K.name);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DClientPlayerInfo)) {
				return false;
			}
			var _value = (DClientPlayerInfo) obj;
			if (!this.token.equals(_value.token)) {
				return false;
			}
			if (!this.name.equals(_value.name)) {
				return false;
			}
			return true;
		}
		
		public DClientPlayerInfo copy() {
			var _value = new DClientPlayerInfo();
			_value.token = this.token;
			_value.name = this.name;
			return _value;
		}
		
		public DClientPlayerInfo deepCopy() {
			var _value = new DClientPlayerInfo();
			_value.token = this.token;
			_value.name = this.name;
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
		}
		
		public int getCount() {
			return count;
		}
		
		public void setCount(int count) {
			this.count = count;
		}
		

		static DStartPileDataPair parseJSONObject(JSONObject jsonObject) {
			var _value = new DStartPileDataPair();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DStartPileDataPair> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DStartPileDataPair>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DStartPileDataPair();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
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
			_value.card = this.card;
			_value.count = this.count;
			return _value;
		}
		
		public DStartPileDataPair deepCopy() {
			var _value = new DStartPileDataPair();
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
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getRoomToken() {
			return roomToken;
		}
		
		public void setRoomToken(String roomToken) {
			this.roomToken = roomToken;
		}
		
		public EPlayerState getState() {
			return state;
		}
		
		public void setState(EPlayerState state) {
			this.state = state;
		}
		

		static DPlayer parseJSONObject(JSONObject jsonObject) {
			var _value = new DPlayer();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DPlayer> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DPlayer>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DPlayer();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
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
			_value.token = this.token;
			_value.name = this.name;
			_value.roomToken = this.roomToken;
			_value.state = this.state;
			return _value;
		}
		
		public DPlayer deepCopy() {
			var _value = new DPlayer();
			_value.token = this.token;
			_value.name = this.name;
			_value.roomToken = this.roomToken;
			_value.state = this.state;
			return _value;
		}
	}
	
	public static class DHumanInit extends DSyncBase {
		public static final String TypeName = "DHumanInit";
		
		private String controllerToken;
		private String heroCard;
		private List<DStartPileDataPair> pile;

		public static class K {
			public static final String controllerToken = "controllerToken";
			public static final String heroCard = "heroCard";
			public static final String pile = "pile";
		}

		public DHumanInit() {
			init();
		}

		@Override
		protected void init() {
			controllerToken = "";
			heroCard = "";
			pile = new ArrayList<>();
		}
		
		public String getControllerToken() {
			return controllerToken;
		}
		
		public void setControllerToken(String controllerToken) {
			this.controllerToken = controllerToken;
		}
		
		public String getHeroCard() {
			return heroCard;
		}
		
		public void setHeroCard(String heroCard) {
			this.heroCard = heroCard;
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
		}

		public void addPile(DStartPileDataPair _value) {
			this.pile.add(_value);
		}
		
		public void addAllPile(List<DStartPileDataPair> _value) {
			this.pile.addAll(_value);
		}
		
		public void clearPile() {
			this.pile.clear();
		}
		

		static DHumanInit parseJSONObject(JSONObject jsonObject) {
			var _value = new DHumanInit();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DHumanInit> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DHumanInit>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DHumanInit();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.controllerToken, controllerToken);
			jsonObject.put(K.heroCard, heroCard);
			jsonObject.put(K.pile, getJSONArray(pile));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			controllerToken = jsonObject.getString(K.controllerToken);
			heroCard = jsonObject.getString(K.heroCard);
			pile = DStartPileDataPair.parseJSONArray(jsonObject.getJSONArray(K.pile));
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
			if (!this.heroCard.equals(_value.heroCard)) {
				return false;
			}
			if (!this.pile.equals(_value.pile)) {
				return false;
			}
			return true;
		}
		
		public DHumanInit copy() {
			var _value = new DHumanInit();
			_value.controllerToken = this.controllerToken;
			_value.heroCard = this.heroCard;
			_value.pile = new ArrayList<>(this.pile);
			return _value;
		}
		
		public DHumanInit deepCopy() {
			var _value = new DHumanInit();
			_value.controllerToken = this.controllerToken;
			_value.heroCard = this.heroCard;
			_value.pile = new ArrayList<>();
			for(var _f: this.pile) {
				if (_f != null) {
					_value.pile.add(_f.deepCopy());
				} else {
					_value.pile.add(null);
				}
			}
			return _value;
		}
	}
	
	/**
	 * --------------------
	 * 客户端->服务器
	 * --------------------
	 */
	public static class CSCreateRoom extends DSyncBase {
		public static final String TypeName = "CSCreateRoom";
		

		public static class K {
		}

		public CSCreateRoom() {
			init();
		}

		@Override
		protected void init() {
		}
		

		static CSCreateRoom parseJSONObject(JSONObject jsonObject) {
			var _value = new CSCreateRoom();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSCreateRoom> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSCreateRoom>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSCreateRoom();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CSCreateRoom)) {
				return false;
			}
			var _value = (CSCreateRoom) obj;
			return true;
		}
		
		public CSCreateRoom copy() {
			var _value = new CSCreateRoom();
			return _value;
		}
		
		public CSCreateRoom deepCopy() {
			var _value = new CSCreateRoom();
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
		}
		
		public ERoomStage getState() {
			return state;
		}
		
		public void setState(ERoomStage state) {
			this.state = state;
		}
		
		public boolean getHide() {
			return hide;
		}
		
		public void setHide(boolean hide) {
			this.hide = hide;
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
		}

		public void addBattlePlayers(DPlayer _value) {
			this.battlePlayers.add(_value);
		}
		
		public void addAllBattlePlayers(List<DPlayer> _value) {
			this.battlePlayers.addAll(_value);
		}
		
		public void clearBattlePlayers() {
			this.battlePlayers.clear();
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
		}

		public void addVisitPlayers(DPlayer _value) {
			this.visitPlayers.add(_value);
		}
		
		public void addAllVisitPlayers(List<DPlayer> _value) {
			this.visitPlayers.addAll(_value);
		}
		
		public void clearVisitPlayers() {
			this.visitPlayers.clear();
		}
		

		static DRoom parseJSONObject(JSONObject jsonObject) {
			var _value = new DRoom();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DRoom> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DRoom>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DRoom();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.state, state.ordinal());
			jsonObject.put(K.hide, hide);
			jsonObject.put(K.battlePlayers, getJSONArray(battlePlayers));
			jsonObject.put(K.visitPlayers, getJSONArray(visitPlayers));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			token = jsonObject.getString(K.token);
			state = ERoomStage.values()[(jsonObject.getIntValue(K.state))];
			hide = jsonObject.getBooleanValue(K.hide);
			battlePlayers = DPlayer.parseJSONArray(jsonObject.getJSONArray(K.battlePlayers));
			visitPlayers = DPlayer.parseJSONArray(jsonObject.getJSONArray(K.visitPlayers));
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
			_value.token = this.token;
			_value.state = this.state;
			_value.hide = this.hide;
			_value.battlePlayers = new ArrayList<>(this.battlePlayers);
			_value.visitPlayers = new ArrayList<>(this.visitPlayers);
			return _value;
		}
		
		public DRoom deepCopy() {
			var _value = new DRoom();
			_value.token = this.token;
			_value.state = this.state;
			_value.hide = this.hide;
			_value.battlePlayers = new ArrayList<>();
			for(var _f: this.battlePlayers) {
				if (_f != null) {
					_value.battlePlayers.add(_f.deepCopy());
				} else {
					_value.battlePlayers.add(null);
				}
			}
			_value.visitPlayers = new ArrayList<>();
			for(var _f: this.visitPlayers) {
				if (_f != null) {
					_value.visitPlayers.add(_f.deepCopy());
				} else {
					_value.visitPlayers.add(null);
				}
			}
			return _value;
		}
	}
	
	public static class DHumanOper extends DSyncBase {
		public static final String TypeName = "DHumanOper";
		
		private EOperType type;
		private long skillId;

		public static class K {
			public static final String type = "type";
			public static final String skillId = "skillId";
		}

		public DHumanOper() {
			init();
		}

		@Override
		protected void init() {
			type = EOperType.None;
			skillId = 0;
		}
		
		public EOperType getType() {
			return type;
		}
		
		public void setType(EOperType type) {
			this.type = type;
		}
		
		public long getSkillId() {
			return skillId;
		}
		
		public void setSkillId(long skillId) {
			this.skillId = skillId;
		}
		

		static DHumanOper parseJSONObject(JSONObject jsonObject) {
			var _value = new DHumanOper();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DHumanOper> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DHumanOper>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DHumanOper();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.type, type.ordinal());
			jsonObject.put(K.skillId, skillId);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			type = EOperType.values()[(jsonObject.getIntValue(K.type))];
			skillId = jsonObject.getLongValue(K.skillId);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DHumanOper)) {
				return false;
			}
			var _value = (DHumanOper) obj;
			if (!this.type.equals(_value.type)) {
				return false;
			}
			if (this.skillId != _value.skillId) {
				return false;
			}
			return true;
		}
		
		public DHumanOper copy() {
			var _value = new DHumanOper();
			_value.type = this.type;
			_value.skillId = this.skillId;
			return _value;
		}
		
		public DHumanOper deepCopy() {
			var _value = new DHumanOper();
			_value.type = this.type;
			_value.skillId = this.skillId;
			return _value;
		}
	}
	

	public static enum ERoomStage {
		Normal,
		Battle,
	}
	public static enum EPlayerState {
		Normal,
		Game,
	}
	public static enum EOperType {
		None,
		Skill,
	}
}

