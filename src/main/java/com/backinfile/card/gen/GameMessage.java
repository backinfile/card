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
		
		public void onMessage(DBoardSetup data) {
		}
		
		public void onMessage(Root data) {
		}
		
		public void onMessage(DActiveSkill data) {
		}
		
		public void onMessage(DCardInfo data) {
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
		
		public void onMessage(DCardInfoList data) {
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
		case DBoardSetup.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DBoardSetup.parseJSONObject(jsonObject));
			}
			break;
		case Root.TypeName:
			for (var listener : listeners) {
				listener.onMessage(Root.parseJSONObject(jsonObject));
			}
			break;
		case DActiveSkill.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DActiveSkill.parseJSONObject(jsonObject));
			}
			break;
		case DCardInfo.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DCardInfo.parseJSONObject(jsonObject));
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
		case DCardInfoList.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DCardInfoList.parseJSONObject(jsonObject));
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
		case DBoardSetup.TypeName:
			return new DBoardSetup();
		case Root.TypeName:
			return new Root();
		case DActiveSkill.TypeName:
			return new DActiveSkill();
		case DCardInfo.TypeName:
			return new DCardInfo();
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
		case DCardInfoList.TypeName:
			return new DCardInfoList();
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
	 * 服务器->客户端
	 * --------------------
	 */
	public static class DBoardSetup extends DSyncBase {
		public static final String TypeName = "DBoardSetup";
		
		/** 主视角玩家token */
		private String viewAt;
		private DCardInfoList cardInfos;

		public static class K {
			public static final String viewAt = "viewAt";
			public static final String cardInfos = "cardInfos";
		}

		public DBoardSetup() {
			init();
		}

		@Override
		protected void init() {
			viewAt = "";
			cardInfos = null;
		}
		
		/** 主视角玩家token */
		public String getViewAt() {
			return viewAt;
		}
		
		/** 主视角玩家token */
		public void setViewAt(String viewAt) {
			this.viewAt = viewAt;
		}
		
		public DCardInfoList getCardInfos() {
			return cardInfos;
		}
		
		public void setCardInfos(DCardInfoList cardInfos) {
			this.cardInfos = cardInfos;
		}
		

		static DBoardSetup parseJSONObject(JSONObject jsonObject) {
			var _value = new DBoardSetup();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DBoardSetup> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DBoardSetup>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DBoardSetup();
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
			jsonObject.put(K.viewAt, viewAt);
			jsonObject.put(K.cardInfos, getJSONObject(cardInfos));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			viewAt = jsonObject.getString(K.viewAt);
			cardInfos = DCardInfoList.parseJSONObject(jsonObject.getJSONObject(K.cardInfos));
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DBoardSetup)) {
				return false;
			}
			var _value = (DBoardSetup) obj;
			if (!this.viewAt.equals(_value.viewAt)) {
				return false;
			}
			if (!this.cardInfos.equals(_value.cardInfos)) {
				return false;
			}
			return true;
		}
		
		public DBoardSetup copy() {
			var _value = new DBoardSetup();
			_value.viewAt = this.viewAt;
			_value.cardInfos = this.cardInfos;
			return _value;
		}
		
		public DBoardSetup deepCopy() {
			var _value = new DBoardSetup();
			_value.viewAt = this.viewAt;
			if (this.cardInfos != null) {
				_value.cardInfos = this.cardInfos.deepCopy();
			}
			return _value;
		}
	}
	
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
	
	/**
	 * 主动技能
	 */
	public static class DActiveSkill extends DSyncBase {
		public static final String TypeName = "DActiveSkill";
		
		private long id;

		public static class K {
			public static final String id = "id";
		}

		public DActiveSkill() {
			init();
		}

		@Override
		protected void init() {
			id = 0;
		}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		

		static DActiveSkill parseJSONObject(JSONObject jsonObject) {
			var _value = new DActiveSkill();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DActiveSkill> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DActiveSkill>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DActiveSkill();
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
			jsonObject.put(K.id, id);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			id = jsonObject.getLongValue(K.id);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DActiveSkill)) {
				return false;
			}
			var _value = (DActiveSkill) obj;
			if (this.id != _value.id) {
				return false;
			}
			return true;
		}
		
		public DActiveSkill copy() {
			var _value = new DActiveSkill();
			_value.id = this.id;
			return _value;
		}
		
		public DActiveSkill deepCopy() {
			var _value = new DActiveSkill();
			_value.id = this.id;
			return _value;
		}
	}
	
	public static class DCardInfo extends DSyncBase {
		public static final String TypeName = "DCardInfo";
		
		private long id;
		/** 卡牌类型 className */
		private String cardName;
		/** 拥有者token */
		private String playerToken;
		/** 牌库类型 */
		private ECardPileType pileType;
		/** 储备区牌类型 */
		private ESlotType slotType;
		/** 处于牌堆中的那个位置 0为最底部 */
		private int pileIndex;
		/** 牌堆中牌的总数 */
		private int pileSize;
		/** 当前slot是计划区 */
		private boolean asPlanSlot;
		/** 当前slot已经准备完成 */
		private boolean ready;

		public static class K {
			public static final String id = "id";
			public static final String cardName = "cardName";
			public static final String playerToken = "playerToken";
			public static final String pileType = "pileType";
			public static final String slotType = "slotType";
			public static final String pileIndex = "pileIndex";
			public static final String pileSize = "pileSize";
			public static final String asPlanSlot = "asPlanSlot";
			public static final String ready = "ready";
		}

		public DCardInfo() {
			init();
		}

		@Override
		protected void init() {
			id = 0;
			cardName = "";
			playerToken = "";
			pileType = ECardPileType.None;
			slotType = ESlotType.None;
			pileIndex = 0;
			pileSize = 0;
			asPlanSlot = false;
			ready = false;
		}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		
		/** 卡牌类型 className */
		public String getCardName() {
			return cardName;
		}
		
		/** 卡牌类型 className */
		public void setCardName(String cardName) {
			this.cardName = cardName;
		}
		
		/** 拥有者token */
		public String getPlayerToken() {
			return playerToken;
		}
		
		/** 拥有者token */
		public void setPlayerToken(String playerToken) {
			this.playerToken = playerToken;
		}
		
		/** 牌库类型 */
		public ECardPileType getPileType() {
			return pileType;
		}
		
		/** 牌库类型 */
		public void setPileType(ECardPileType pileType) {
			this.pileType = pileType;
		}
		
		/** 储备区牌类型 */
		public ESlotType getSlotType() {
			return slotType;
		}
		
		/** 储备区牌类型 */
		public void setSlotType(ESlotType slotType) {
			this.slotType = slotType;
		}
		
		/** 处于牌堆中的那个位置 0为最底部 */
		public int getPileIndex() {
			return pileIndex;
		}
		
		/** 处于牌堆中的那个位置 0为最底部 */
		public void setPileIndex(int pileIndex) {
			this.pileIndex = pileIndex;
		}
		
		/** 牌堆中牌的总数 */
		public int getPileSize() {
			return pileSize;
		}
		
		/** 牌堆中牌的总数 */
		public void setPileSize(int pileSize) {
			this.pileSize = pileSize;
		}
		
		/** 当前slot是计划区 */
		public boolean getAsPlanSlot() {
			return asPlanSlot;
		}
		
		/** 当前slot是计划区 */
		public void setAsPlanSlot(boolean asPlanSlot) {
			this.asPlanSlot = asPlanSlot;
		}
		
		/** 当前slot已经准备完成 */
		public boolean getReady() {
			return ready;
		}
		
		/** 当前slot已经准备完成 */
		public void setReady(boolean ready) {
			this.ready = ready;
		}
		

		static DCardInfo parseJSONObject(JSONObject jsonObject) {
			var _value = new DCardInfo();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DCardInfo> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DCardInfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DCardInfo();
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
			jsonObject.put(K.id, id);
			jsonObject.put(K.cardName, cardName);
			jsonObject.put(K.playerToken, playerToken);
			jsonObject.put(K.pileType, pileType.ordinal());
			jsonObject.put(K.slotType, slotType.ordinal());
			jsonObject.put(K.pileIndex, pileIndex);
			jsonObject.put(K.pileSize, pileSize);
			jsonObject.put(K.asPlanSlot, asPlanSlot);
			jsonObject.put(K.ready, ready);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			id = jsonObject.getLongValue(K.id);
			cardName = jsonObject.getString(K.cardName);
			playerToken = jsonObject.getString(K.playerToken);
			pileType = ECardPileType.values()[(jsonObject.getIntValue(K.pileType))];
			slotType = ESlotType.values()[(jsonObject.getIntValue(K.slotType))];
			pileIndex = jsonObject.getIntValue(K.pileIndex);
			pileSize = jsonObject.getIntValue(K.pileSize);
			asPlanSlot = jsonObject.getBooleanValue(K.asPlanSlot);
			ready = jsonObject.getBooleanValue(K.ready);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DCardInfo)) {
				return false;
			}
			var _value = (DCardInfo) obj;
			if (this.id != _value.id) {
				return false;
			}
			if (!this.cardName.equals(_value.cardName)) {
				return false;
			}
			if (!this.playerToken.equals(_value.playerToken)) {
				return false;
			}
			if (!this.pileType.equals(_value.pileType)) {
				return false;
			}
			if (!this.slotType.equals(_value.slotType)) {
				return false;
			}
			if (this.pileIndex != _value.pileIndex) {
				return false;
			}
			if (this.pileSize != _value.pileSize) {
				return false;
			}
			if (this.asPlanSlot != _value.asPlanSlot) {
				return false;
			}
			if (this.ready != _value.ready) {
				return false;
			}
			return true;
		}
		
		public DCardInfo copy() {
			var _value = new DCardInfo();
			_value.id = this.id;
			_value.cardName = this.cardName;
			_value.playerToken = this.playerToken;
			_value.pileType = this.pileType;
			_value.slotType = this.slotType;
			_value.pileIndex = this.pileIndex;
			_value.pileSize = this.pileSize;
			_value.asPlanSlot = this.asPlanSlot;
			_value.ready = this.ready;
			return _value;
		}
		
		public DCardInfo deepCopy() {
			var _value = new DCardInfo();
			_value.id = this.id;
			_value.cardName = this.cardName;
			_value.playerToken = this.playerToken;
			_value.pileType = this.pileType;
			_value.slotType = this.slotType;
			_value.pileIndex = this.pileIndex;
			_value.pileSize = this.pileSize;
			_value.asPlanSlot = this.asPlanSlot;
			_value.ready = this.ready;
			return _value;
		}
	}
	
	/**
	 * --------------------
	 * 服务器信息
	 * --------------------
	 */
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
	
	public static class DCardInfoList extends DSyncBase {
		public static final String TypeName = "DCardInfoList";
		
		private List<DCardInfo> cards;

		public static class K {
			public static final String cards = "cards";
		}

		public DCardInfoList() {
			init();
		}

		@Override
		protected void init() {
			cards = new ArrayList<>();
		}
		
		public int getCardsCount() {
			return this.cards.size();
		}
		
		public List<DCardInfo> getCardsList() {
			return new ArrayList<>(cards);
		}
		
		public void setCardsList(List<DCardInfo> _value) {
			this.cards.clear();
			this.cards.addAll(_value);
		}

		public void addCards(DCardInfo _value) {
			this.cards.add(_value);
		}
		
		public void addAllCards(List<DCardInfo> _value) {
			this.cards.addAll(_value);
		}
		
		public void clearCards() {
			this.cards.clear();
		}
		

		static DCardInfoList parseJSONObject(JSONObject jsonObject) {
			var _value = new DCardInfoList();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DCardInfoList> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DCardInfoList>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DCardInfoList();
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
			jsonObject.put(K.cards, getJSONArray(cards));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			cards = DCardInfo.parseJSONArray(jsonObject.getJSONArray(K.cards));
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DCardInfoList)) {
				return false;
			}
			var _value = (DCardInfoList) obj;
			if (!this.cards.equals(_value.cards)) {
				return false;
			}
			return true;
		}
		
		public DCardInfoList copy() {
			var _value = new DCardInfoList();
			_value.cards = new ArrayList<>(this.cards);
			return _value;
		}
		
		public DCardInfoList deepCopy() {
			var _value = new DCardInfoList();
			_value.cards = new ArrayList<>();
			for(var _f: this.cards) {
				if (_f != null) {
					_value.cards.add(_f.deepCopy());
				} else {
					_value.cards.add(null);
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
	public static enum ECardPileType {
		None,
		/** 手牌 */
		HandPile,
		/** 牌库 */
		DrawPile,
		/** 弃牌区 */
		DiscardPile,
		/** 废弃牌区 */
		TrashPile,
		/** 标记牌 */
		MarkPile,
		/** 储备牌区 */
		SlotPile,
		/** 英雄牌区 */
		HeroPile,
	}
	public static enum ESlotType {
		None,
		/** 储备牌 */
		Store,
		/** 封印牌 */
		Seal,
		/** 骑乘牌 */
		Ride,
		/** 骚扰牌 */
		Harass,
		/** 充能牌 */
		Charge,
		/** 计划牌 */
		Plan,
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

