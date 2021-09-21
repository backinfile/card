package com.backinfile.card.gen;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.backinfile.dSync.model.DSyncBaseHandler;

@SuppressWarnings("unused")
public class GameMessageHandler extends DSyncBaseHandler {
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
		
		public void onMessage(CSSelectCard data) {
		}
		
		public void onMessage(DCardInfo data) {
		}
		
		public void onMessage(DClientPlayerInfo data) {
		}
		
		public void onMessage(DPlayer data) {
		}
		
		public void onMessage(DTargetInfo data) {
		}
		
		public void onMessage(DHumanInit data) {
		}
		
		public void onMessage(DCardInfoList data) {
		}
		
		public void onMessage(CSCreateRoom data) {
		}
		
		public void onMessage(DRoom data) {
		}
		
		public void onMessage(DTargetSelect data) {
		}
		
		public void onMessage(DHumanOper data) {
		}
		
		public void onMessage(DBoardData data) {
		}
		
		public void onMessage(Root data) {
		}
		
		public void onMessage(DSkillInfo data) {
		}
		
		public void onMessage(DServer data) {
		}
		
		public void onMessage(CSSelectSkillToActive data) {
		}
		
		public void onMessage(DStartPileDataPair data) {
		}
		
		public void onMessage(SCSelectCards data) {
		}
		
		public void onMessage(DCardPileInfo data) {
		}
		
		public void onMessage(SCSelectSkillToActive data) {
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
		case CSSelectCard.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSSelectCard.parseJSONObject(jsonObject));
			}
			break;
		case DCardInfo.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DCardInfo.parseJSONObject(jsonObject));
			}
			break;
		case DClientPlayerInfo.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DClientPlayerInfo.parseJSONObject(jsonObject));
			}
			break;
		case DPlayer.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DPlayer.parseJSONObject(jsonObject));
			}
			break;
		case DTargetInfo.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DTargetInfo.parseJSONObject(jsonObject));
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
		case DTargetSelect.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DTargetSelect.parseJSONObject(jsonObject));
			}
			break;
		case DHumanOper.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DHumanOper.parseJSONObject(jsonObject));
			}
			break;
		case DBoardData.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DBoardData.parseJSONObject(jsonObject));
			}
			break;
		case Root.TypeName:
			for (var listener : listeners) {
				listener.onMessage(Root.parseJSONObject(jsonObject));
			}
			break;
		case DSkillInfo.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DSkillInfo.parseJSONObject(jsonObject));
			}
			break;
		case DServer.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DServer.parseJSONObject(jsonObject));
			}
			break;
		case CSSelectSkillToActive.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSSelectSkillToActive.parseJSONObject(jsonObject));
			}
			break;
		case DStartPileDataPair.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DStartPileDataPair.parseJSONObject(jsonObject));
			}
			break;
		case SCSelectCards.TypeName:
			for (var listener : listeners) {
				listener.onMessage(SCSelectCards.parseJSONObject(jsonObject));
			}
			break;
		case DCardPileInfo.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DCardPileInfo.parseJSONObject(jsonObject));
			}
			break;
		case SCSelectSkillToActive.TypeName:
			for (var listener : listeners) {
				listener.onMessage(SCSelectSkillToActive.parseJSONObject(jsonObject));
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
		case CSSelectCard.TypeName:
			return new CSSelectCard();
		case DCardInfo.TypeName:
			return new DCardInfo();
		case DClientPlayerInfo.TypeName:
			return new DClientPlayerInfo();
		case DPlayer.TypeName:
			return new DPlayer();
		case DTargetInfo.TypeName:
			return new DTargetInfo();
		case DHumanInit.TypeName:
			return new DHumanInit();
		case DCardInfoList.TypeName:
			return new DCardInfoList();
		case CSCreateRoom.TypeName:
			return new CSCreateRoom();
		case DRoom.TypeName:
			return new DRoom();
		case DTargetSelect.TypeName:
			return new DTargetSelect();
		case DHumanOper.TypeName:
			return new DHumanOper();
		case DBoardData.TypeName:
			return new DBoardData();
		case Root.TypeName:
			return new Root();
		case DSkillInfo.TypeName:
			return new DSkillInfo();
		case DServer.TypeName:
			return new DServer();
		case CSSelectSkillToActive.TypeName:
			return new CSSelectSkillToActive();
		case DStartPileDataPair.TypeName:
			return new DStartPileDataPair();
		case SCSelectCards.TypeName:
			return new SCSelectCards();
		case DCardPileInfo.TypeName:
			return new DCardPileInfo();
		case SCSelectSkillToActive.TypeName:
			return new SCSelectSkillToActive();
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
	 * ----------------------------------------
	 * 棋盘基本信息
	 * ----------------------------------------
	 * 棋盘初始化信息 第一次进入游戏
	 */
	public static class DBoardSetup extends DSyncBase {
		public static final String TypeName = "DBoardSetup";
		
		private DBoardData data;
		private DCardInfoList cardInfos;

		public static class K {
			public static final String data = "data";
			public static final String cardInfos = "cardInfos";
		}

		public DBoardSetup() {
			init();
		}

		@Override
		protected void init() {
			data = null;
			cardInfos = null;
		}
		
		public DBoardData getData() {
			return data;
		}
		
		public void setData(DBoardData data) {
			this.data = data;
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
			jsonObject.put(K.data, getJSONObject(data));
			jsonObject.put(K.cardInfos, getJSONObject(cardInfos));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			data = DBoardData.parseJSONObject(jsonObject.getJSONObject(K.data));
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
			if (!this.data.equals(_value.data)) {
				return false;
			}
			if (!this.cardInfos.equals(_value.cardInfos)) {
				return false;
			}
			return true;
		}
		
		public DBoardSetup copy() {
			var _value = new DBoardSetup();
			_value.data = this.data;
			_value.cardInfos = this.cardInfos;
			return _value;
		}
		
		public DBoardSetup deepCopy() {
			var _value = new DBoardSetup();
			if (this.data != null) {
				_value.data = this.data.deepCopy();
			}
			if (this.cardInfos != null) {
				_value.cardInfos = this.cardInfos.deepCopy();
			}
			return _value;
		}
	}
	
	/**
	 * 客户端选择一张卡牌
	 */
	public static class CSSelectCard extends DSyncBase {
		public static final String TypeName = "CSSelectCard";
		
		/** 0表示取消 >0表示选择的卡 */
		private long cardId;

		public static class K {
			public static final String cardId = "cardId";
		}

		public CSSelectCard() {
			init();
		}

		@Override
		protected void init() {
			cardId = 0;
		}
		
		/** 0表示取消 >0表示选择的卡 */
		public long getCardId() {
			return cardId;
		}
		
		/** 0表示取消 >0表示选择的卡 */
		public void setCardId(long cardId) {
			this.cardId = cardId;
		}
		

		static CSSelectCard parseJSONObject(JSONObject jsonObject) {
			var _value = new CSSelectCard();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSSelectCard> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSSelectCard>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSSelectCard();
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
			jsonObject.put(K.cardId, cardId);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			cardId = jsonObject.getLongValue(K.cardId);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CSSelectCard)) {
				return false;
			}
			var _value = (CSSelectCard) obj;
			if (this.cardId != _value.cardId) {
				return false;
			}
			return true;
		}
		
		public CSSelectCard copy() {
			var _value = new CSSelectCard();
			_value.cardId = this.cardId;
			return _value;
		}
		
		public CSSelectCard deepCopy() {
			var _value = new CSSelectCard();
			_value.cardId = this.cardId;
			return _value;
		}
	}
	
	/**
	 * 卡牌更新或移动
	 */
	public static class DCardInfo extends DSyncBase {
		public static final String TypeName = "DCardInfo";
		
		/** 卡牌sn */
		private String sn;
		private long id;
		private DCardPileInfo pileInfo;

		public static class K {
			public static final String sn = "sn";
			public static final String id = "id";
			public static final String pileInfo = "pileInfo";
		}

		public DCardInfo() {
			init();
		}

		@Override
		protected void init() {
			sn = "";
			id = 0;
			pileInfo = null;
		}
		
		/** 卡牌sn */
		public String getSn() {
			return sn;
		}
		
		/** 卡牌sn */
		public void setSn(String sn) {
			this.sn = sn;
		}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		
		public DCardPileInfo getPileInfo() {
			return pileInfo;
		}
		
		public void setPileInfo(DCardPileInfo pileInfo) {
			this.pileInfo = pileInfo;
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
			jsonObject.put(K.sn, sn);
			jsonObject.put(K.id, id);
			jsonObject.put(K.pileInfo, getJSONObject(pileInfo));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			sn = jsonObject.getString(K.sn);
			id = jsonObject.getLongValue(K.id);
			pileInfo = DCardPileInfo.parseJSONObject(jsonObject.getJSONObject(K.pileInfo));
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
			if (!this.sn.equals(_value.sn)) {
				return false;
			}
			if (this.id != _value.id) {
				return false;
			}
			if (!this.pileInfo.equals(_value.pileInfo)) {
				return false;
			}
			return true;
		}
		
		public DCardInfo copy() {
			var _value = new DCardInfo();
			_value.sn = this.sn;
			_value.id = this.id;
			_value.pileInfo = this.pileInfo;
			return _value;
		}
		
		public DCardInfo deepCopy() {
			var _value = new DCardInfo();
			_value.sn = this.sn;
			_value.id = this.id;
			if (this.pileInfo != null) {
				_value.pileInfo = this.pileInfo.deepCopy();
			}
			return _value;
		}
	}
	
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
	
	/**
	 * ----------------------------------------
	 * 棋盘内交互信息
	 * ----------------------------------------
	 * 需求Player执行选择操作
	 */
	public static class DTargetInfo extends DSyncBase {
		public static final String TypeName = "DTargetInfo";
		
		private ETargetType type;
		/** 最少需求数量 */
		private int minNumber;
		/** 最大需求数量 -1表示任意数量 */
		private int maxNumber;
		/** 指定辅助牌类型 */
		private ETargetSlotAimType slotAimType;
		/** 提示语 */
		private String tip;
		/** 是否限定储备完成的牌 */
		private boolean onlyReady;
		/** 是否排除计划牌 */
		private boolean exceptPlan;
		/** 是否选对手的 */
		private boolean opponent;
		/** 是否排除手牌 */
		private boolean exceptHand;
		/** 严格限制张数，不足张数则不进行选择（默认为尽量达到需求即可） */
		private boolean strict;

		public static class K {
			public static final String type = "type";
			public static final String minNumber = "minNumber";
			public static final String maxNumber = "maxNumber";
			public static final String slotAimType = "slotAimType";
			public static final String tip = "tip";
			public static final String onlyReady = "onlyReady";
			public static final String exceptPlan = "exceptPlan";
			public static final String opponent = "opponent";
			public static final String exceptHand = "exceptHand";
			public static final String strict = "strict";
		}

		public DTargetInfo() {
			init();
		}

		@Override
		protected void init() {
			type = ETargetType.None;
			minNumber = 0;
			maxNumber = 0;
			slotAimType = ETargetSlotAimType.None;
			tip = "";
			onlyReady = false;
			exceptPlan = false;
			opponent = false;
			exceptHand = false;
			strict = false;
		}
		
		public ETargetType getType() {
			return type;
		}
		
		public void setType(ETargetType type) {
			this.type = type;
		}
		
		/** 最少需求数量 */
		public int getMinNumber() {
			return minNumber;
		}
		
		/** 最少需求数量 */
		public void setMinNumber(int minNumber) {
			this.minNumber = minNumber;
		}
		
		/** 最大需求数量 -1表示任意数量 */
		public int getMaxNumber() {
			return maxNumber;
		}
		
		/** 最大需求数量 -1表示任意数量 */
		public void setMaxNumber(int maxNumber) {
			this.maxNumber = maxNumber;
		}
		
		/** 指定辅助牌类型 */
		public ETargetSlotAimType getSlotAimType() {
			return slotAimType;
		}
		
		/** 指定辅助牌类型 */
		public void setSlotAimType(ETargetSlotAimType slotAimType) {
			this.slotAimType = slotAimType;
		}
		
		/** 提示语 */
		public String getTip() {
			return tip;
		}
		
		/** 提示语 */
		public void setTip(String tip) {
			this.tip = tip;
		}
		
		/** 是否限定储备完成的牌 */
		public boolean getOnlyReady() {
			return onlyReady;
		}
		
		/** 是否限定储备完成的牌 */
		public void setOnlyReady(boolean onlyReady) {
			this.onlyReady = onlyReady;
		}
		
		/** 是否排除计划牌 */
		public boolean getExceptPlan() {
			return exceptPlan;
		}
		
		/** 是否排除计划牌 */
		public void setExceptPlan(boolean exceptPlan) {
			this.exceptPlan = exceptPlan;
		}
		
		/** 是否选对手的 */
		public boolean getOpponent() {
			return opponent;
		}
		
		/** 是否选对手的 */
		public void setOpponent(boolean opponent) {
			this.opponent = opponent;
		}
		
		/** 是否排除手牌 */
		public boolean getExceptHand() {
			return exceptHand;
		}
		
		/** 是否排除手牌 */
		public void setExceptHand(boolean exceptHand) {
			this.exceptHand = exceptHand;
		}
		
		/** 严格限制张数，不足张数则不进行选择（默认为尽量达到需求即可） */
		public boolean getStrict() {
			return strict;
		}
		
		/** 严格限制张数，不足张数则不进行选择（默认为尽量达到需求即可） */
		public void setStrict(boolean strict) {
			this.strict = strict;
		}
		

		static DTargetInfo parseJSONObject(JSONObject jsonObject) {
			var _value = new DTargetInfo();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DTargetInfo> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DTargetInfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DTargetInfo();
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
			jsonObject.put(K.minNumber, minNumber);
			jsonObject.put(K.maxNumber, maxNumber);
			jsonObject.put(K.slotAimType, slotAimType.ordinal());
			jsonObject.put(K.tip, tip);
			jsonObject.put(K.onlyReady, onlyReady);
			jsonObject.put(K.exceptPlan, exceptPlan);
			jsonObject.put(K.opponent, opponent);
			jsonObject.put(K.exceptHand, exceptHand);
			jsonObject.put(K.strict, strict);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			type = ETargetType.values()[(jsonObject.getIntValue(K.type))];
			minNumber = jsonObject.getIntValue(K.minNumber);
			maxNumber = jsonObject.getIntValue(K.maxNumber);
			slotAimType = ETargetSlotAimType.values()[(jsonObject.getIntValue(K.slotAimType))];
			tip = jsonObject.getString(K.tip);
			onlyReady = jsonObject.getBooleanValue(K.onlyReady);
			exceptPlan = jsonObject.getBooleanValue(K.exceptPlan);
			opponent = jsonObject.getBooleanValue(K.opponent);
			exceptHand = jsonObject.getBooleanValue(K.exceptHand);
			strict = jsonObject.getBooleanValue(K.strict);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DTargetInfo)) {
				return false;
			}
			var _value = (DTargetInfo) obj;
			if (!this.type.equals(_value.type)) {
				return false;
			}
			if (this.minNumber != _value.minNumber) {
				return false;
			}
			if (this.maxNumber != _value.maxNumber) {
				return false;
			}
			if (!this.slotAimType.equals(_value.slotAimType)) {
				return false;
			}
			if (!this.tip.equals(_value.tip)) {
				return false;
			}
			if (this.onlyReady != _value.onlyReady) {
				return false;
			}
			if (this.exceptPlan != _value.exceptPlan) {
				return false;
			}
			if (this.opponent != _value.opponent) {
				return false;
			}
			if (this.exceptHand != _value.exceptHand) {
				return false;
			}
			if (this.strict != _value.strict) {
				return false;
			}
			return true;
		}
		
		public DTargetInfo copy() {
			var _value = new DTargetInfo();
			_value.type = this.type;
			_value.minNumber = this.minNumber;
			_value.maxNumber = this.maxNumber;
			_value.slotAimType = this.slotAimType;
			_value.tip = this.tip;
			_value.onlyReady = this.onlyReady;
			_value.exceptPlan = this.exceptPlan;
			_value.opponent = this.opponent;
			_value.exceptHand = this.exceptHand;
			_value.strict = this.strict;
			return _value;
		}
		
		public DTargetInfo deepCopy() {
			var _value = new DTargetInfo();
			_value.type = this.type;
			_value.minNumber = this.minNumber;
			_value.maxNumber = this.maxNumber;
			_value.slotAimType = this.slotAimType;
			_value.tip = this.tip;
			_value.onlyReady = this.onlyReady;
			_value.exceptPlan = this.exceptPlan;
			_value.opponent = this.opponent;
			_value.exceptHand = this.exceptHand;
			_value.strict = this.strict;
			return _value;
		}
	}
	
	public static class DHumanInit extends DSyncBase {
		public static final String TypeName = "DHumanInit";
		
		private String controllerToken;
		private String playerName;
		private String heroCard;
		private List<DStartPileDataPair> pile;

		public static class K {
			public static final String controllerToken = "controllerToken";
			public static final String playerName = "playerName";
			public static final String heroCard = "heroCard";
			public static final String pile = "pile";
		}

		public DHumanInit() {
			init();
		}

		@Override
		protected void init() {
			controllerToken = "";
			playerName = "";
			heroCard = "";
			pile = new ArrayList<>();
		}
		
		public String getControllerToken() {
			return controllerToken;
		}
		
		public void setControllerToken(String controllerToken) {
			this.controllerToken = controllerToken;
		}
		
		public String getPlayerName() {
			return playerName;
		}
		
		public void setPlayerName(String playerName) {
			this.playerName = playerName;
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
			jsonObject.put(K.playerName, playerName);
			jsonObject.put(K.heroCard, heroCard);
			jsonObject.put(K.pile, getJSONArray(pile));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			controllerToken = jsonObject.getString(K.controllerToken);
			playerName = jsonObject.getString(K.playerName);
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
			if (!this.playerName.equals(_value.playerName)) {
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
			_value.playerName = this.playerName;
			_value.heroCard = this.heroCard;
			_value.pile = new ArrayList<>(this.pile);
			return _value;
		}
		
		public DHumanInit deepCopy() {
			var _value = new DHumanInit();
			_value.controllerToken = this.controllerToken;
			_value.playerName = this.playerName;
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
	 * 卡牌更新或移动
	 */
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
	 * ----------------------------------------
	 * 其他
	 * ----------------------------------------
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
	
	/**
	 * 选择操作返回
	 */
	public static class DTargetSelect extends DSyncBase {
		public static final String TypeName = "DTargetSelect";
		
		/** 确认与否 */
		private boolean confirm;
		/** 选择的卡牌id */
		private List<Long> selectedCard;
		/** 选择的储备位 */
		private List<Integer> slotIndex;

		public static class K {
			public static final String confirm = "confirm";
			public static final String selectedCard = "selectedCard";
			public static final String slotIndex = "slotIndex";
		}

		public DTargetSelect() {
			init();
		}

		@Override
		protected void init() {
			confirm = false;
			selectedCard = new ArrayList<>();
			slotIndex = new ArrayList<>();
		}
		
		/** 确认与否 */
		public boolean getConfirm() {
			return confirm;
		}
		
		/** 确认与否 */
		public void setConfirm(boolean confirm) {
			this.confirm = confirm;
		}
		
		/** 选择的卡牌id */
		public int getSelectedCardCount() {
			return this.selectedCard.size();
		}
		
		/** 选择的卡牌id */
		public List<Long> getSelectedCardList() {
			return new ArrayList<>(selectedCard);
		}
		
		/** 选择的卡牌id */
		public void setSelectedCardList(List<Long> _value) {
			this.selectedCard.clear();
			this.selectedCard.addAll(_value);
		}

		/** 选择的卡牌id */
		public void addSelectedCard(long _value) {
			this.selectedCard.add(_value);
		}
		
		/** 选择的卡牌id */
		public void addAllSelectedCard(List<Long> _value) {
			this.selectedCard.addAll(_value);
		}
		
		/** 选择的卡牌id */
		public void clearSelectedCard() {
			this.selectedCard.clear();
		}
		
		/** 选择的储备位 */
		public int getSlotIndexCount() {
			return this.slotIndex.size();
		}
		
		/** 选择的储备位 */
		public List<Integer> getSlotIndexList() {
			return new ArrayList<>(slotIndex);
		}
		
		/** 选择的储备位 */
		public void setSlotIndexList(List<Integer> _value) {
			this.slotIndex.clear();
			this.slotIndex.addAll(_value);
		}

		/** 选择的储备位 */
		public void addSlotIndex(int _value) {
			this.slotIndex.add(_value);
		}
		
		/** 选择的储备位 */
		public void addAllSlotIndex(List<Integer> _value) {
			this.slotIndex.addAll(_value);
		}
		
		/** 选择的储备位 */
		public void clearSlotIndex() {
			this.slotIndex.clear();
		}
		

		static DTargetSelect parseJSONObject(JSONObject jsonObject) {
			var _value = new DTargetSelect();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DTargetSelect> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DTargetSelect>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DTargetSelect();
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
			jsonObject.put(K.confirm, confirm);
			jsonObject.put(K.selectedCard, JSONObject.toJSONString(selectedCard));
			jsonObject.put(K.slotIndex, JSONObject.toJSONString(slotIndex));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			confirm = jsonObject.getBooleanValue(K.confirm);
			selectedCard = JSONObject.parseArray(jsonObject.getString(K.selectedCard), Long.class);
			slotIndex = JSONObject.parseArray(jsonObject.getString(K.slotIndex), Integer.class);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DTargetSelect)) {
				return false;
			}
			var _value = (DTargetSelect) obj;
			if (this.confirm != _value.confirm) {
				return false;
			}
			if (!this.selectedCard.equals(_value.selectedCard)) {
				return false;
			}
			if (!this.slotIndex.equals(_value.slotIndex)) {
				return false;
			}
			return true;
		}
		
		public DTargetSelect copy() {
			var _value = new DTargetSelect();
			_value.confirm = this.confirm;
			_value.selectedCard = new ArrayList<>(this.selectedCard);
			_value.slotIndex = new ArrayList<>(this.slotIndex);
			return _value;
		}
		
		public DTargetSelect deepCopy() {
			var _value = new DTargetSelect();
			_value.confirm = this.confirm;
			_value.selectedCard = new ArrayList<>(this.selectedCard);
			_value.slotIndex = new ArrayList<>(this.slotIndex);
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
	
	/**
	 * 棋盘上卡牌以外的信息 sc
	 */
	public static class DBoardData extends DSyncBase {
		public static final String TypeName = "DBoardData";
		
		private String opponentPlayerName;
		/** 当前回合玩家名字 */
		private String curTurnPlayerName;
		/** 当前行动玩家名字 */
		private String curActionPlayerName;
		/** 当前行动点 */
		private int actionPoint;

		public static class K {
			public static final String opponentPlayerName = "opponentPlayerName";
			public static final String curTurnPlayerName = "curTurnPlayerName";
			public static final String curActionPlayerName = "curActionPlayerName";
			public static final String actionPoint = "actionPoint";
		}

		public DBoardData() {
			init();
		}

		@Override
		protected void init() {
			opponentPlayerName = "";
			curTurnPlayerName = "";
			curActionPlayerName = "";
			actionPoint = 0;
		}
		
		public String getOpponentPlayerName() {
			return opponentPlayerName;
		}
		
		public void setOpponentPlayerName(String opponentPlayerName) {
			this.opponentPlayerName = opponentPlayerName;
		}
		
		/** 当前回合玩家名字 */
		public String getCurTurnPlayerName() {
			return curTurnPlayerName;
		}
		
		/** 当前回合玩家名字 */
		public void setCurTurnPlayerName(String curTurnPlayerName) {
			this.curTurnPlayerName = curTurnPlayerName;
		}
		
		/** 当前行动玩家名字 */
		public String getCurActionPlayerName() {
			return curActionPlayerName;
		}
		
		/** 当前行动玩家名字 */
		public void setCurActionPlayerName(String curActionPlayerName) {
			this.curActionPlayerName = curActionPlayerName;
		}
		
		/** 当前行动点 */
		public int getActionPoint() {
			return actionPoint;
		}
		
		/** 当前行动点 */
		public void setActionPoint(int actionPoint) {
			this.actionPoint = actionPoint;
		}
		

		static DBoardData parseJSONObject(JSONObject jsonObject) {
			var _value = new DBoardData();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DBoardData> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DBoardData>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DBoardData();
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
			jsonObject.put(K.opponentPlayerName, opponentPlayerName);
			jsonObject.put(K.curTurnPlayerName, curTurnPlayerName);
			jsonObject.put(K.curActionPlayerName, curActionPlayerName);
			jsonObject.put(K.actionPoint, actionPoint);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			opponentPlayerName = jsonObject.getString(K.opponentPlayerName);
			curTurnPlayerName = jsonObject.getString(K.curTurnPlayerName);
			curActionPlayerName = jsonObject.getString(K.curActionPlayerName);
			actionPoint = jsonObject.getIntValue(K.actionPoint);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DBoardData)) {
				return false;
			}
			var _value = (DBoardData) obj;
			if (!this.opponentPlayerName.equals(_value.opponentPlayerName)) {
				return false;
			}
			if (!this.curTurnPlayerName.equals(_value.curTurnPlayerName)) {
				return false;
			}
			if (!this.curActionPlayerName.equals(_value.curActionPlayerName)) {
				return false;
			}
			if (this.actionPoint != _value.actionPoint) {
				return false;
			}
			return true;
		}
		
		public DBoardData copy() {
			var _value = new DBoardData();
			_value.opponentPlayerName = this.opponentPlayerName;
			_value.curTurnPlayerName = this.curTurnPlayerName;
			_value.curActionPlayerName = this.curActionPlayerName;
			_value.actionPoint = this.actionPoint;
			return _value;
		}
		
		public DBoardData deepCopy() {
			var _value = new DBoardData();
			_value.opponentPlayerName = this.opponentPlayerName;
			_value.curTurnPlayerName = this.curTurnPlayerName;
			_value.curActionPlayerName = this.curActionPlayerName;
			_value.actionPoint = this.actionPoint;
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
	 * 技能信息
	 */
	public static class DSkillInfo extends DSyncBase {
		public static final String TypeName = "DSkillInfo";
		
		/** 技能sn */
		private String sn;
		/** 技能id */
		private long skillId;
		/** 卡牌id -1表示非卡牌skill */
		private long ownerId;
		private String tip;

		public static class K {
			public static final String sn = "sn";
			public static final String skillId = "skillId";
			public static final String ownerId = "ownerId";
			public static final String tip = "tip";
		}

		public DSkillInfo() {
			init();
		}

		@Override
		protected void init() {
			sn = "";
			skillId = 0;
			ownerId = 0;
			tip = "";
		}
		
		/** 技能sn */
		public String getSn() {
			return sn;
		}
		
		/** 技能sn */
		public void setSn(String sn) {
			this.sn = sn;
		}
		
		/** 技能id */
		public long getSkillId() {
			return skillId;
		}
		
		/** 技能id */
		public void setSkillId(long skillId) {
			this.skillId = skillId;
		}
		
		/** 卡牌id -1表示非卡牌skill */
		public long getOwnerId() {
			return ownerId;
		}
		
		/** 卡牌id -1表示非卡牌skill */
		public void setOwnerId(long ownerId) {
			this.ownerId = ownerId;
		}
		
		public String getTip() {
			return tip;
		}
		
		public void setTip(String tip) {
			this.tip = tip;
		}
		

		static DSkillInfo parseJSONObject(JSONObject jsonObject) {
			var _value = new DSkillInfo();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DSkillInfo> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DSkillInfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DSkillInfo();
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
			jsonObject.put(K.sn, sn);
			jsonObject.put(K.skillId, skillId);
			jsonObject.put(K.ownerId, ownerId);
			jsonObject.put(K.tip, tip);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			sn = jsonObject.getString(K.sn);
			skillId = jsonObject.getLongValue(K.skillId);
			ownerId = jsonObject.getLongValue(K.ownerId);
			tip = jsonObject.getString(K.tip);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DSkillInfo)) {
				return false;
			}
			var _value = (DSkillInfo) obj;
			if (!this.sn.equals(_value.sn)) {
				return false;
			}
			if (this.skillId != _value.skillId) {
				return false;
			}
			if (this.ownerId != _value.ownerId) {
				return false;
			}
			if (!this.tip.equals(_value.tip)) {
				return false;
			}
			return true;
		}
		
		public DSkillInfo copy() {
			var _value = new DSkillInfo();
			_value.sn = this.sn;
			_value.skillId = this.skillId;
			_value.ownerId = this.ownerId;
			_value.tip = this.tip;
			return _value;
		}
		
		public DSkillInfo deepCopy() {
			var _value = new DSkillInfo();
			_value.sn = this.sn;
			_value.skillId = this.skillId;
			_value.ownerId = this.ownerId;
			_value.tip = this.tip;
			return _value;
		}
	}
	
	/**
	 * ----------------------------------------
	 * 服务器信息
	 * ----------------------------------------
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
	
	public static class CSSelectSkillToActive extends DSyncBase {
		public static final String TypeName = "CSSelectSkillToActive";
		
		private long skillId;

		public static class K {
			public static final String skillId = "skillId";
		}

		public CSSelectSkillToActive() {
			init();
		}

		@Override
		protected void init() {
			skillId = 0;
		}
		
		public long getSkillId() {
			return skillId;
		}
		
		public void setSkillId(long skillId) {
			this.skillId = skillId;
		}
		

		static CSSelectSkillToActive parseJSONObject(JSONObject jsonObject) {
			var _value = new CSSelectSkillToActive();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSSelectSkillToActive> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSSelectSkillToActive>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSSelectSkillToActive();
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
			jsonObject.put(K.skillId, skillId);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
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
			if (!(obj instanceof CSSelectSkillToActive)) {
				return false;
			}
			var _value = (CSSelectSkillToActive) obj;
			if (this.skillId != _value.skillId) {
				return false;
			}
			return true;
		}
		
		public CSSelectSkillToActive copy() {
			var _value = new CSSelectSkillToActive();
			_value.skillId = this.skillId;
			return _value;
		}
		
		public CSSelectSkillToActive deepCopy() {
			var _value = new CSSelectSkillToActive();
			_value.skillId = this.skillId;
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
	
	/**
	 * 通知客户端可以选择其中一张卡牌
	 */
	public static class SCSelectCards extends DSyncBase {
		public static final String TypeName = "SCSelectCards";
		
		private List<Long> cardIds;
		/** 可以取消 */
		private boolean cancel;

		public static class K {
			public static final String cardIds = "cardIds";
			public static final String cancel = "cancel";
		}

		public SCSelectCards() {
			init();
		}

		@Override
		protected void init() {
			cardIds = new ArrayList<>();
			cancel = false;
		}
		
		public int getCardIdsCount() {
			return this.cardIds.size();
		}
		
		public List<Long> getCardIdsList() {
			return new ArrayList<>(cardIds);
		}
		
		public void setCardIdsList(List<Long> _value) {
			this.cardIds.clear();
			this.cardIds.addAll(_value);
		}

		public void addCardIds(long _value) {
			this.cardIds.add(_value);
		}
		
		public void addAllCardIds(List<Long> _value) {
			this.cardIds.addAll(_value);
		}
		
		public void clearCardIds() {
			this.cardIds.clear();
		}
		
		/** 可以取消 */
		public boolean getCancel() {
			return cancel;
		}
		
		/** 可以取消 */
		public void setCancel(boolean cancel) {
			this.cancel = cancel;
		}
		

		static SCSelectCards parseJSONObject(JSONObject jsonObject) {
			var _value = new SCSelectCards();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<SCSelectCards> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<SCSelectCards>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new SCSelectCards();
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
			jsonObject.put(K.cardIds, JSONObject.toJSONString(cardIds));
			jsonObject.put(K.cancel, cancel);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			cardIds = JSONObject.parseArray(jsonObject.getString(K.cardIds), Long.class);
			cancel = jsonObject.getBooleanValue(K.cancel);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SCSelectCards)) {
				return false;
			}
			var _value = (SCSelectCards) obj;
			if (!this.cardIds.equals(_value.cardIds)) {
				return false;
			}
			if (this.cancel != _value.cancel) {
				return false;
			}
			return true;
		}
		
		public SCSelectCards copy() {
			var _value = new SCSelectCards();
			_value.cardIds = new ArrayList<>(this.cardIds);
			_value.cancel = this.cancel;
			return _value;
		}
		
		public SCSelectCards deepCopy() {
			var _value = new SCSelectCards();
			_value.cardIds = new ArrayList<>(this.cardIds);
			_value.cancel = this.cancel;
			return _value;
		}
	}
	
	/**
	 * 一张卡所在的位置
	 */
	public static class DCardPileInfo extends DSyncBase {
		public static final String TypeName = "DCardPileInfo";
		
		/** 拥有者token */
		private String playerToken;
		/** 牌库类型 */
		private ECardPileType pileType;
		/** 储备区牌类型 */
		private ESlotType slotType;
		/** 当前slot是计划区 */
		private boolean asPlanSlot;
		/** 当前slot已经准备完成 */
		private boolean ready;
		/** 处于牌堆中的那个位置 0为最底部 */
		private int pileIndex;
		/** 牌堆中牌的总数 */
		private int pileSize;

		public static class K {
			public static final String playerToken = "playerToken";
			public static final String pileType = "pileType";
			public static final String slotType = "slotType";
			public static final String asPlanSlot = "asPlanSlot";
			public static final String ready = "ready";
			public static final String pileIndex = "pileIndex";
			public static final String pileSize = "pileSize";
		}

		public DCardPileInfo() {
			init();
		}

		@Override
		protected void init() {
			playerToken = "";
			pileType = ECardPileType.None;
			slotType = ESlotType.None;
			asPlanSlot = false;
			ready = false;
			pileIndex = 0;
			pileSize = 0;
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
		

		static DCardPileInfo parseJSONObject(JSONObject jsonObject) {
			var _value = new DCardPileInfo();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DCardPileInfo> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DCardPileInfo>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DCardPileInfo();
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
			jsonObject.put(K.playerToken, playerToken);
			jsonObject.put(K.pileType, pileType.ordinal());
			jsonObject.put(K.slotType, slotType.ordinal());
			jsonObject.put(K.asPlanSlot, asPlanSlot);
			jsonObject.put(K.ready, ready);
			jsonObject.put(K.pileIndex, pileIndex);
			jsonObject.put(K.pileSize, pileSize);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			playerToken = jsonObject.getString(K.playerToken);
			pileType = ECardPileType.values()[(jsonObject.getIntValue(K.pileType))];
			slotType = ESlotType.values()[(jsonObject.getIntValue(K.slotType))];
			asPlanSlot = jsonObject.getBooleanValue(K.asPlanSlot);
			ready = jsonObject.getBooleanValue(K.ready);
			pileIndex = jsonObject.getIntValue(K.pileIndex);
			pileSize = jsonObject.getIntValue(K.pileSize);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DCardPileInfo)) {
				return false;
			}
			var _value = (DCardPileInfo) obj;
			if (!this.playerToken.equals(_value.playerToken)) {
				return false;
			}
			if (!this.pileType.equals(_value.pileType)) {
				return false;
			}
			if (!this.slotType.equals(_value.slotType)) {
				return false;
			}
			if (this.asPlanSlot != _value.asPlanSlot) {
				return false;
			}
			if (this.ready != _value.ready) {
				return false;
			}
			if (this.pileIndex != _value.pileIndex) {
				return false;
			}
			if (this.pileSize != _value.pileSize) {
				return false;
			}
			return true;
		}
		
		public DCardPileInfo copy() {
			var _value = new DCardPileInfo();
			_value.playerToken = this.playerToken;
			_value.pileType = this.pileType;
			_value.slotType = this.slotType;
			_value.asPlanSlot = this.asPlanSlot;
			_value.ready = this.ready;
			_value.pileIndex = this.pileIndex;
			_value.pileSize = this.pileSize;
			return _value;
		}
		
		public DCardPileInfo deepCopy() {
			var _value = new DCardPileInfo();
			_value.playerToken = this.playerToken;
			_value.pileType = this.pileType;
			_value.slotType = this.slotType;
			_value.asPlanSlot = this.asPlanSlot;
			_value.ready = this.ready;
			_value.pileIndex = this.pileIndex;
			_value.pileSize = this.pileSize;
			return _value;
		}
	}
	
	/**
	 * 通知客户端选择一项技能触发
	 */
	public static class SCSelectSkillToActive extends DSyncBase {
		public static final String TypeName = "SCSelectSkillToActive";
		
		private List<DSkillInfo> skillInfos;

		public static class K {
			public static final String skillInfos = "skillInfos";
		}

		public SCSelectSkillToActive() {
			init();
		}

		@Override
		protected void init() {
			skillInfos = new ArrayList<>();
		}
		
		public int getSkillInfosCount() {
			return this.skillInfos.size();
		}
		
		public List<DSkillInfo> getSkillInfosList() {
			return new ArrayList<>(skillInfos);
		}
		
		public void setSkillInfosList(List<DSkillInfo> _value) {
			this.skillInfos.clear();
			this.skillInfos.addAll(_value);
		}

		public void addSkillInfos(DSkillInfo _value) {
			this.skillInfos.add(_value);
		}
		
		public void addAllSkillInfos(List<DSkillInfo> _value) {
			this.skillInfos.addAll(_value);
		}
		
		public void clearSkillInfos() {
			this.skillInfos.clear();
		}
		

		static SCSelectSkillToActive parseJSONObject(JSONObject jsonObject) {
			var _value = new SCSelectSkillToActive();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<SCSelectSkillToActive> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<SCSelectSkillToActive>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new SCSelectSkillToActive();
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
			jsonObject.put(K.skillInfos, getJSONArray(skillInfos));
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			skillInfos = DSkillInfo.parseJSONArray(jsonObject.getJSONArray(K.skillInfos));
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SCSelectSkillToActive)) {
				return false;
			}
			var _value = (SCSelectSkillToActive) obj;
			if (!this.skillInfos.equals(_value.skillInfos)) {
				return false;
			}
			return true;
		}
		
		public SCSelectSkillToActive copy() {
			var _value = new SCSelectSkillToActive();
			_value.skillInfos = new ArrayList<>(this.skillInfos);
			return _value;
		}
		
		public SCSelectSkillToActive deepCopy() {
			var _value = new SCSelectSkillToActive();
			_value.skillInfos = new ArrayList<>();
			for(var _f: this.skillInfos) {
				if (_f != null) {
					_value.skillInfos.add(_f.deepCopy());
				} else {
					_value.skillInfos.add(null);
				}
			}
			return _value;
		}
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
		/** 威慑牌区 */
		ThreatenPile,
	}
	/**
	 * TargetInfo的类型
	 */
	public static enum ETargetType {
		/** 只需确认 */
		None,
		/** 确认/取消 */
		Confirm,
		/** 空白储备位 */
		EmptySlot,
		/** 任意储备 */
		Store,
		/** 限定储备位上的储备 */
		StoreInSlot,
		/** 手牌 */
		HandPile,
		/** 牌库 */
		DrawPile,
		/** 弃牌区 */
		DiscardPile,
	}
	public static enum ERoomStage {
		Normal,
		Battle,
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
	/**
	 * 工具卡牌的类型
	 */
	public static enum ETargetSlotAimType {
		None,
		/** 侵占 */
		Occupy,
		/** 储备 */
		Store,
	}
}

