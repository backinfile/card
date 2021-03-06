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
	
	public void removeListener(DSyncListener listener) {
		listeners.remove(listener);
	}
	
	public static abstract class DSyncListener {
		public void onMessage(CSJoinRoom data) {
		}
		
		public void onMessage(DBoardInit data) {
		}
		
		public void onMessage(DBoardSetup data) {
		}
		
		public void onMessage(DCombination data) {
		}
		
		public void onMessage(DCardInfo data) {
		}
		
		public void onMessage(DClientPlayerInfo data) {
		}
		
		public void onMessage(SCGameOver data) {
		}
		
		public void onMessage(CSSelectOption data) {
		}
		
		public void onMessage(DPlayer data) {
		}
		
		public void onMessage(CSSelectConfirm data) {
		}
		
		public void onMessage(DHumanInit data) {
		}
		
		public void onMessage(DBoardHumanData data) {
		}
		
		public void onMessage(DCardInfoList data) {
		}
		
		public void onMessage(CSCreateRoom data) {
		}
		
		public void onMessage(SCSelectEmptySlot data) {
		}
		
		public void onMessage(SCSelectOption data) {
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
		
		public void onMessage(CSSelectCards data) {
		}
		
		public void onMessage(DServer data) {
		}
		
		public void onMessage(DPileNumber data) {
		}
		
		public void onMessage(SCSelectConfirm data) {
		}
		
		public void onMessage(CSSelectSkillToActive data) {
		}
		
		public void onMessage(SCGameLog data) {
		}
		
		public void onMessage(DStartPileDataPair data) {
		}
		
		public void onMessage(SCSelectCards data) {
		}
		
		public void onMessage(CSSelectEmptySlot data) {
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
		case DCombination.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DCombination.parseJSONObject(jsonObject));
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
		case SCGameOver.TypeName:
			for (var listener : listeners) {
				listener.onMessage(SCGameOver.parseJSONObject(jsonObject));
			}
			break;
		case CSSelectOption.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSSelectOption.parseJSONObject(jsonObject));
			}
			break;
		case DPlayer.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DPlayer.parseJSONObject(jsonObject));
			}
			break;
		case CSSelectConfirm.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSSelectConfirm.parseJSONObject(jsonObject));
			}
			break;
		case DHumanInit.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DHumanInit.parseJSONObject(jsonObject));
			}
			break;
		case DBoardHumanData.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DBoardHumanData.parseJSONObject(jsonObject));
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
		case SCSelectEmptySlot.TypeName:
			for (var listener : listeners) {
				listener.onMessage(SCSelectEmptySlot.parseJSONObject(jsonObject));
			}
			break;
		case SCSelectOption.TypeName:
			for (var listener : listeners) {
				listener.onMessage(SCSelectOption.parseJSONObject(jsonObject));
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
		case CSSelectCards.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSSelectCards.parseJSONObject(jsonObject));
			}
			break;
		case DServer.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DServer.parseJSONObject(jsonObject));
			}
			break;
		case DPileNumber.TypeName:
			for (var listener : listeners) {
				listener.onMessage(DPileNumber.parseJSONObject(jsonObject));
			}
			break;
		case SCSelectConfirm.TypeName:
			for (var listener : listeners) {
				listener.onMessage(SCSelectConfirm.parseJSONObject(jsonObject));
			}
			break;
		case CSSelectSkillToActive.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSSelectSkillToActive.parseJSONObject(jsonObject));
			}
			break;
		case SCGameLog.TypeName:
			for (var listener : listeners) {
				listener.onMessage(SCGameLog.parseJSONObject(jsonObject));
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
		case CSSelectEmptySlot.TypeName:
			for (var listener : listeners) {
				listener.onMessage(CSSelectEmptySlot.parseJSONObject(jsonObject));
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
		case DCombination.TypeName:
			return new DCombination();
		case DCardInfo.TypeName:
			return new DCardInfo();
		case DClientPlayerInfo.TypeName:
			return new DClientPlayerInfo();
		case SCGameOver.TypeName:
			return new SCGameOver();
		case CSSelectOption.TypeName:
			return new CSSelectOption();
		case DPlayer.TypeName:
			return new DPlayer();
		case CSSelectConfirm.TypeName:
			return new CSSelectConfirm();
		case DHumanInit.TypeName:
			return new DHumanInit();
		case DBoardHumanData.TypeName:
			return new DBoardHumanData();
		case DCardInfoList.TypeName:
			return new DCardInfoList();
		case CSCreateRoom.TypeName:
			return new CSCreateRoom();
		case SCSelectEmptySlot.TypeName:
			return new SCSelectEmptySlot();
		case SCSelectOption.TypeName:
			return new SCSelectOption();
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
		case CSSelectCards.TypeName:
			return new CSSelectCards();
		case DServer.TypeName:
			return new DServer();
		case DPileNumber.TypeName:
			return new DPileNumber();
		case SCSelectConfirm.TypeName:
			return new SCSelectConfirm();
		case CSSelectSkillToActive.TypeName:
			return new CSSelectSkillToActive();
		case SCGameLog.TypeName:
			return new SCGameLog();
		case DStartPileDataPair.TypeName:
			return new DStartPileDataPair();
		case SCSelectCards.TypeName:
			return new SCSelectCards();
		case CSSelectEmptySlot.TypeName:
			return new CSSelectEmptySlot();
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	 * ??????????????????
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.seed, seed);
			jsonObject.put(K.humanInits, getJSONArray(humanInits));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	 * ??????????????????
	 * ----------------------------------------
	 * ????????????????????? ?????????????????????
	 */
	public static class DBoardSetup extends DSyncBase {
		public static final String TypeName = "DBoardSetup";
		
		private DCardInfoList cardInfos;

		public static class K {
			public static final String cardInfos = "cardInfos";
		}

		public DBoardSetup() {
			init();
		}

		@Override
		protected void init() {
			cardInfos = null;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.cardInfos, getJSONObject(cardInfos));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
			if (!this.cardInfos.equals(_value.cardInfos)) {
				return false;
			}
			return true;
		}
		
		public DBoardSetup copy() {
			var _value = new DBoardSetup();
			_value.cardInfos = this.cardInfos;
			return _value;
		}
		
		public DBoardSetup deepCopy() {
			var _value = new DBoardSetup();
			if (this.cardInfos != null) {
				_value.cardInfos = this.cardInfos.deepCopy();
			}
			return _value;
		}
	}
	
	public static class DCombination extends DSyncBase {
		public static final String TypeName = "DCombination";
		
		/** ?????????????????? */
		private List<Long> idList;

		public static class K {
			public static final String idList = "idList";
		}

		public DCombination() {
			init();
		}

		@Override
		protected void init() {
			idList = new ArrayList<>();
		}
		
		/** ?????????????????? */
		public int getIdListCount() {
			return this.idList.size();
		}
		
		/** ?????????????????? */
		public List<Long> getIdListList() {
			return new ArrayList<>(idList);
		}
		
		/** ?????????????????? */
		public void setIdListList(List<Long> _value) {
			this.idList.clear();
			this.idList.addAll(_value);
		}

		/** ?????????????????? */
		public void addIdList(long _value) {
			this.idList.add(_value);
		}
		
		/** ?????????????????? */
		public void addAllIdList(List<Long> _value) {
			this.idList.addAll(_value);
		}
		
		/** ?????????????????? */
		public void clearIdList() {
			this.idList.clear();
		}
		

		static DCombination parseJSONObject(JSONObject jsonObject) {
			var _value = new DCombination();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DCombination> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DCombination>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DCombination();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.idList, JSONObject.toJSONString(idList));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			idList = JSONObject.parseArray(jsonObject.getString(K.idList), Long.class);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DCombination)) {
				return false;
			}
			var _value = (DCombination) obj;
			if (!this.idList.equals(_value.idList)) {
				return false;
			}
			return true;
		}
		
		public DCombination copy() {
			var _value = new DCombination();
			_value.idList = new ArrayList<>(this.idList);
			return _value;
		}
		
		public DCombination deepCopy() {
			var _value = new DCombination();
			_value.idList = new ArrayList<>(this.idList);
			return _value;
		}
	}
	
	/**
	 * ?????????????????????
	 */
	public static class DCardInfo extends DSyncBase {
		public static final String TypeName = "DCardInfo";
		
		/** ??????sn */
		private String sn;
		private long id;
		private String ownerToken;
		private DCardPileInfo pileInfo;

		public static class K {
			public static final String sn = "sn";
			public static final String id = "id";
			public static final String ownerToken = "ownerToken";
			public static final String pileInfo = "pileInfo";
		}

		public DCardInfo() {
			init();
		}

		@Override
		protected void init() {
			sn = "";
			id = 0;
			ownerToken = "";
			pileInfo = null;
		}
		
		/** ??????sn */
		public String getSn() {
			return sn;
		}
		
		/** ??????sn */
		public void setSn(String sn) {
			this.sn = sn;
		}
		
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
		}
		
		public String getOwnerToken() {
			return ownerToken;
		}
		
		public void setOwnerToken(String ownerToken) {
			this.ownerToken = ownerToken;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.sn, sn);
			jsonObject.put(K.id, id);
			jsonObject.put(K.ownerToken, ownerToken);
			jsonObject.put(K.pileInfo, getJSONObject(pileInfo));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			sn = jsonObject.getString(K.sn);
			id = jsonObject.getLongValue(K.id);
			ownerToken = jsonObject.getString(K.ownerToken);
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
			if (!this.ownerToken.equals(_value.ownerToken)) {
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
			_value.ownerToken = this.ownerToken;
			_value.pileInfo = this.pileInfo;
			return _value;
		}
		
		public DCardInfo deepCopy() {
			var _value = new DCardInfo();
			_value.sn = this.sn;
			_value.id = this.id;
			_value.ownerToken = this.ownerToken;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.name, name);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	
	/**
	 * ???????????????????????????
	 */
	public static class SCGameOver extends DSyncBase {
		public static final String TypeName = "SCGameOver";
		

		public static class K {
		}

		public SCGameOver() {
			init();
		}

		@Override
		protected void init() {
		}
		

		static SCGameOver parseJSONObject(JSONObject jsonObject) {
			var _value = new SCGameOver();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<SCGameOver> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<SCGameOver>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new SCGameOver();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SCGameOver)) {
				return false;
			}
			var _value = (SCGameOver) obj;
			return true;
		}
		
		public SCGameOver copy() {
			var _value = new SCGameOver();
			return _value;
		}
		
		public SCGameOver deepCopy() {
			var _value = new SCGameOver();
			return _value;
		}
	}
	
	public static class CSSelectOption extends DSyncBase {
		public static final String TypeName = "CSSelectOption";
		
		private int index;

		public static class K {
			public static final String index = "index";
		}

		public CSSelectOption() {
			init();
		}

		@Override
		protected void init() {
			index = 0;
		}
		
		public int getIndex() {
			return index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
		

		static CSSelectOption parseJSONObject(JSONObject jsonObject) {
			var _value = new CSSelectOption();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSSelectOption> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSSelectOption>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSSelectOption();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.index, index);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			index = jsonObject.getIntValue(K.index);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CSSelectOption)) {
				return false;
			}
			var _value = (CSSelectOption) obj;
			if (this.index != _value.index) {
				return false;
			}
			return true;
		}
		
		public CSSelectOption copy() {
			var _value = new CSSelectOption();
			_value.index = this.index;
			return _value;
		}
		
		public CSSelectOption deepCopy() {
			var _value = new CSSelectOption();
			_value.index = this.index;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.name, name);
			jsonObject.put(K.roomToken, roomToken);
			jsonObject.put(K.state, state.ordinal());
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	
	public static class CSSelectConfirm extends DSyncBase {
		public static final String TypeName = "CSSelectConfirm";
		
		/** ?????? */
		private boolean confirm;

		public static class K {
			public static final String confirm = "confirm";
		}

		public CSSelectConfirm() {
			init();
		}

		@Override
		protected void init() {
			confirm = false;
		}
		
		/** ?????? */
		public boolean getConfirm() {
			return confirm;
		}
		
		/** ?????? */
		public void setConfirm(boolean confirm) {
			this.confirm = confirm;
		}
		

		static CSSelectConfirm parseJSONObject(JSONObject jsonObject) {
			var _value = new CSSelectConfirm();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSSelectConfirm> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSSelectConfirm>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSSelectConfirm();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.confirm, confirm);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			confirm = jsonObject.getBooleanValue(K.confirm);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CSSelectConfirm)) {
				return false;
			}
			var _value = (CSSelectConfirm) obj;
			if (this.confirm != _value.confirm) {
				return false;
			}
			return true;
		}
		
		public CSSelectConfirm copy() {
			var _value = new CSSelectConfirm();
			_value.confirm = this.confirm;
			return _value;
		}
		
		public CSSelectConfirm deepCopy() {
			var _value = new CSSelectConfirm();
			_value.confirm = this.confirm;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.controllerToken, controllerToken);
			jsonObject.put(K.playerName, playerName);
			jsonObject.put(K.heroCard, heroCard);
			jsonObject.put(K.pile, getJSONArray(pile));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	
	public static class DBoardHumanData extends DSyncBase {
		public static final String TypeName = "DBoardHumanData";
		
		private String token;
		private String name;
		/** ??????????????? */
		private int actionPoint;

		public static class K {
			public static final String token = "token";
			public static final String name = "name";
			public static final String actionPoint = "actionPoint";
		}

		public DBoardHumanData() {
			init();
		}

		@Override
		protected void init() {
			token = "";
			name = "";
			actionPoint = 0;
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
		
		/** ??????????????? */
		public int getActionPoint() {
			return actionPoint;
		}
		
		/** ??????????????? */
		public void setActionPoint(int actionPoint) {
			this.actionPoint = actionPoint;
		}
		

		static DBoardHumanData parseJSONObject(JSONObject jsonObject) {
			var _value = new DBoardHumanData();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DBoardHumanData> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DBoardHumanData>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DBoardHumanData();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.name, name);
			jsonObject.put(K.actionPoint, actionPoint);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			token = jsonObject.getString(K.token);
			name = jsonObject.getString(K.name);
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
			if (!(obj instanceof DBoardHumanData)) {
				return false;
			}
			var _value = (DBoardHumanData) obj;
			if (!this.token.equals(_value.token)) {
				return false;
			}
			if (!this.name.equals(_value.name)) {
				return false;
			}
			if (this.actionPoint != _value.actionPoint) {
				return false;
			}
			return true;
		}
		
		public DBoardHumanData copy() {
			var _value = new DBoardHumanData();
			_value.token = this.token;
			_value.name = this.name;
			_value.actionPoint = this.actionPoint;
			return _value;
		}
		
		public DBoardHumanData deepCopy() {
			var _value = new DBoardHumanData();
			_value.token = this.token;
			_value.name = this.name;
			_value.actionPoint = this.actionPoint;
			return _value;
		}
	}
	
	/**
	 * ?????????????????????
	 */
	public static class DCardInfoList extends DSyncBase {
		public static final String TypeName = "DCardInfoList";
		
		private DBoardData data;
		private List<DCardInfo> cards;

		public static class K {
			public static final String data = "data";
			public static final String cards = "cards";
		}

		public DCardInfoList() {
			init();
		}

		@Override
		protected void init() {
			data = null;
			cards = new ArrayList<>();
		}
		
		public DBoardData getData() {
			return data;
		}
		
		public void setData(DBoardData data) {
			this.data = data;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.data, getJSONObject(data));
			jsonObject.put(K.cards, getJSONArray(cards));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			data = DBoardData.parseJSONObject(jsonObject.getJSONObject(K.data));
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
			if (!this.data.equals(_value.data)) {
				return false;
			}
			if (!this.cards.equals(_value.cards)) {
				return false;
			}
			return true;
		}
		
		public DCardInfoList copy() {
			var _value = new DCardInfoList();
			_value.data = this.data;
			_value.cards = new ArrayList<>(this.cards);
			return _value;
		}
		
		public DCardInfoList deepCopy() {
			var _value = new DCardInfoList();
			if (this.data != null) {
				_value.data = this.data.deepCopy();
			}
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
	 * ??????
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	
	/**
	 * ???????????????????????????????????????
	 */
	public static class SCSelectEmptySlot extends DSyncBase {
		public static final String TypeName = "SCSelectEmptySlot";
		
		private ETargetSlotAimType aimType;
		private List<Integer> selectFrom;
		private boolean opponent;
		private String tip;
		/** ?????????????????? */
		private boolean cancel;
		private String cancelTip;

		public static class K {
			public static final String aimType = "aimType";
			public static final String selectFrom = "selectFrom";
			public static final String opponent = "opponent";
			public static final String tip = "tip";
			public static final String cancel = "cancel";
			public static final String cancelTip = "cancelTip";
		}

		public SCSelectEmptySlot() {
			init();
		}

		@Override
		protected void init() {
			aimType = ETargetSlotAimType.None;
			selectFrom = new ArrayList<>();
			opponent = false;
			tip = "";
			cancel = false;
			cancelTip = "";
		}
		
		public ETargetSlotAimType getAimType() {
			return aimType;
		}
		
		public void setAimType(ETargetSlotAimType aimType) {
			this.aimType = aimType;
		}
		
		public int getSelectFromCount() {
			return this.selectFrom.size();
		}
		
		public List<Integer> getSelectFromList() {
			return new ArrayList<>(selectFrom);
		}
		
		public void setSelectFromList(List<Integer> _value) {
			this.selectFrom.clear();
			this.selectFrom.addAll(_value);
		}

		public void addSelectFrom(int _value) {
			this.selectFrom.add(_value);
		}
		
		public void addAllSelectFrom(List<Integer> _value) {
			this.selectFrom.addAll(_value);
		}
		
		public void clearSelectFrom() {
			this.selectFrom.clear();
		}
		
		public boolean getOpponent() {
			return opponent;
		}
		
		public void setOpponent(boolean opponent) {
			this.opponent = opponent;
		}
		
		public String getTip() {
			return tip;
		}
		
		public void setTip(String tip) {
			this.tip = tip;
		}
		
		/** ?????????????????? */
		public boolean getCancel() {
			return cancel;
		}
		
		/** ?????????????????? */
		public void setCancel(boolean cancel) {
			this.cancel = cancel;
		}
		
		public String getCancelTip() {
			return cancelTip;
		}
		
		public void setCancelTip(String cancelTip) {
			this.cancelTip = cancelTip;
		}
		

		static SCSelectEmptySlot parseJSONObject(JSONObject jsonObject) {
			var _value = new SCSelectEmptySlot();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<SCSelectEmptySlot> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<SCSelectEmptySlot>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new SCSelectEmptySlot();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.aimType, aimType.ordinal());
			jsonObject.put(K.selectFrom, JSONObject.toJSONString(selectFrom));
			jsonObject.put(K.opponent, opponent);
			jsonObject.put(K.tip, tip);
			jsonObject.put(K.cancel, cancel);
			jsonObject.put(K.cancelTip, cancelTip);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			aimType = ETargetSlotAimType.values()[(jsonObject.getIntValue(K.aimType))];
			selectFrom = JSONObject.parseArray(jsonObject.getString(K.selectFrom), Integer.class);
			opponent = jsonObject.getBooleanValue(K.opponent);
			tip = jsonObject.getString(K.tip);
			cancel = jsonObject.getBooleanValue(K.cancel);
			cancelTip = jsonObject.getString(K.cancelTip);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SCSelectEmptySlot)) {
				return false;
			}
			var _value = (SCSelectEmptySlot) obj;
			if (!this.aimType.equals(_value.aimType)) {
				return false;
			}
			if (!this.selectFrom.equals(_value.selectFrom)) {
				return false;
			}
			if (this.opponent != _value.opponent) {
				return false;
			}
			if (!this.tip.equals(_value.tip)) {
				return false;
			}
			if (this.cancel != _value.cancel) {
				return false;
			}
			if (!this.cancelTip.equals(_value.cancelTip)) {
				return false;
			}
			return true;
		}
		
		public SCSelectEmptySlot copy() {
			var _value = new SCSelectEmptySlot();
			_value.aimType = this.aimType;
			_value.selectFrom = new ArrayList<>(this.selectFrom);
			_value.opponent = this.opponent;
			_value.tip = this.tip;
			_value.cancel = this.cancel;
			_value.cancelTip = this.cancelTip;
			return _value;
		}
		
		public SCSelectEmptySlot deepCopy() {
			var _value = new SCSelectEmptySlot();
			_value.aimType = this.aimType;
			_value.selectFrom = new ArrayList<>(this.selectFrom);
			_value.opponent = this.opponent;
			_value.tip = this.tip;
			_value.cancel = this.cancel;
			_value.cancelTip = this.cancelTip;
			return _value;
		}
	}
	
	/**
	 * ???????????????????????????
	 */
	public static class SCSelectOption extends DSyncBase {
		public static final String TypeName = "SCSelectOption";
		
		private String tip;
		private List<String> options;

		public static class K {
			public static final String tip = "tip";
			public static final String options = "options";
		}

		public SCSelectOption() {
			init();
		}

		@Override
		protected void init() {
			tip = "";
			options = new ArrayList<>();
		}
		
		public String getTip() {
			return tip;
		}
		
		public void setTip(String tip) {
			this.tip = tip;
		}
		
		public int getOptionsCount() {
			return this.options.size();
		}
		
		public List<String> getOptionsList() {
			return new ArrayList<>(options);
		}
		
		public void setOptionsList(List<String> _value) {
			this.options.clear();
			this.options.addAll(_value);
		}

		public void addOptions(String _value) {
			this.options.add(_value);
		}
		
		public void addAllOptions(List<String> _value) {
			this.options.addAll(_value);
		}
		
		public void clearOptions() {
			this.options.clear();
		}
		

		static SCSelectOption parseJSONObject(JSONObject jsonObject) {
			var _value = new SCSelectOption();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<SCSelectOption> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<SCSelectOption>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new SCSelectOption();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.tip, tip);
			jsonObject.put(K.options, JSONObject.toJSONString(options));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			tip = jsonObject.getString(K.tip);
			options = JSONObject.parseArray(jsonObject.getString(K.options), String.class);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SCSelectOption)) {
				return false;
			}
			var _value = (SCSelectOption) obj;
			if (!this.tip.equals(_value.tip)) {
				return false;
			}
			if (!this.options.equals(_value.options)) {
				return false;
			}
			return true;
		}
		
		public SCSelectOption copy() {
			var _value = new SCSelectOption();
			_value.tip = this.tip;
			_value.options = new ArrayList<>(this.options);
			return _value;
		}
		
		public SCSelectOption deepCopy() {
			var _value = new SCSelectOption();
			_value.tip = this.tip;
			_value.options = new ArrayList<>(this.options);
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.state, state.ordinal());
			jsonObject.put(K.hide, hide);
			jsonObject.put(K.battlePlayers, getJSONArray(battlePlayers));
			jsonObject.put(K.visitPlayers, getJSONArray(visitPlayers));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	 * ??????????????????
	 */
	public static class DTargetSelect extends DSyncBase {
		public static final String TypeName = "DTargetSelect";
		
		/** ???????????? */
		private boolean confirm;
		/** ???????????????id */
		private List<Long> selectedCard;
		/** ?????????????????? */
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
		
		/** ???????????? */
		public boolean getConfirm() {
			return confirm;
		}
		
		/** ???????????? */
		public void setConfirm(boolean confirm) {
			this.confirm = confirm;
		}
		
		/** ???????????????id */
		public int getSelectedCardCount() {
			return this.selectedCard.size();
		}
		
		/** ???????????????id */
		public List<Long> getSelectedCardList() {
			return new ArrayList<>(selectedCard);
		}
		
		/** ???????????????id */
		public void setSelectedCardList(List<Long> _value) {
			this.selectedCard.clear();
			this.selectedCard.addAll(_value);
		}

		/** ???????????????id */
		public void addSelectedCard(long _value) {
			this.selectedCard.add(_value);
		}
		
		/** ???????????????id */
		public void addAllSelectedCard(List<Long> _value) {
			this.selectedCard.addAll(_value);
		}
		
		/** ???????????????id */
		public void clearSelectedCard() {
			this.selectedCard.clear();
		}
		
		/** ?????????????????? */
		public int getSlotIndexCount() {
			return this.slotIndex.size();
		}
		
		/** ?????????????????? */
		public List<Integer> getSlotIndexList() {
			return new ArrayList<>(slotIndex);
		}
		
		/** ?????????????????? */
		public void setSlotIndexList(List<Integer> _value) {
			this.slotIndex.clear();
			this.slotIndex.addAll(_value);
		}

		/** ?????????????????? */
		public void addSlotIndex(int _value) {
			this.slotIndex.add(_value);
		}
		
		/** ?????????????????? */
		public void addAllSlotIndex(List<Integer> _value) {
			this.slotIndex.addAll(_value);
		}
		
		/** ?????????????????? */
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.confirm, confirm);
			jsonObject.put(K.selectedCard, JSONObject.toJSONString(selectedCard));
			jsonObject.put(K.slotIndex, JSONObject.toJSONString(slotIndex));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.type, type.ordinal());
			jsonObject.put(K.skillId, skillId);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	 * ?????????????????????????????? sc
	 */
	public static class DBoardData extends DSyncBase {
		public static final String TypeName = "DBoardData";
		
		private List<DBoardHumanData> playerDatas;
		/** ?????????????????? */
		private String curTurnPlayer;
		/** ?????????????????? */
		private String curActionPlayer;
		private List<DPileNumber> pileNumbers;

		public static class K {
			public static final String playerDatas = "playerDatas";
			public static final String curTurnPlayer = "curTurnPlayer";
			public static final String curActionPlayer = "curActionPlayer";
			public static final String pileNumbers = "pileNumbers";
		}

		public DBoardData() {
			init();
		}

		@Override
		protected void init() {
			playerDatas = new ArrayList<>();
			curTurnPlayer = "";
			curActionPlayer = "";
			pileNumbers = new ArrayList<>();
		}
		
		public int getPlayerDatasCount() {
			return this.playerDatas.size();
		}
		
		public List<DBoardHumanData> getPlayerDatasList() {
			return new ArrayList<>(playerDatas);
		}
		
		public void setPlayerDatasList(List<DBoardHumanData> _value) {
			this.playerDatas.clear();
			this.playerDatas.addAll(_value);
		}

		public void addPlayerDatas(DBoardHumanData _value) {
			this.playerDatas.add(_value);
		}
		
		public void addAllPlayerDatas(List<DBoardHumanData> _value) {
			this.playerDatas.addAll(_value);
		}
		
		public void clearPlayerDatas() {
			this.playerDatas.clear();
		}
		
		/** ?????????????????? */
		public String getCurTurnPlayer() {
			return curTurnPlayer;
		}
		
		/** ?????????????????? */
		public void setCurTurnPlayer(String curTurnPlayer) {
			this.curTurnPlayer = curTurnPlayer;
		}
		
		/** ?????????????????? */
		public String getCurActionPlayer() {
			return curActionPlayer;
		}
		
		/** ?????????????????? */
		public void setCurActionPlayer(String curActionPlayer) {
			this.curActionPlayer = curActionPlayer;
		}
		
		public int getPileNumbersCount() {
			return this.pileNumbers.size();
		}
		
		public List<DPileNumber> getPileNumbersList() {
			return new ArrayList<>(pileNumbers);
		}
		
		public void setPileNumbersList(List<DPileNumber> _value) {
			this.pileNumbers.clear();
			this.pileNumbers.addAll(_value);
		}

		public void addPileNumbers(DPileNumber _value) {
			this.pileNumbers.add(_value);
		}
		
		public void addAllPileNumbers(List<DPileNumber> _value) {
			this.pileNumbers.addAll(_value);
		}
		
		public void clearPileNumbers() {
			this.pileNumbers.clear();
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.playerDatas, getJSONArray(playerDatas));
			jsonObject.put(K.curTurnPlayer, curTurnPlayer);
			jsonObject.put(K.curActionPlayer, curActionPlayer);
			jsonObject.put(K.pileNumbers, getJSONArray(pileNumbers));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			playerDatas = DBoardHumanData.parseJSONArray(jsonObject.getJSONArray(K.playerDatas));
			curTurnPlayer = jsonObject.getString(K.curTurnPlayer);
			curActionPlayer = jsonObject.getString(K.curActionPlayer);
			pileNumbers = DPileNumber.parseJSONArray(jsonObject.getJSONArray(K.pileNumbers));
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
			if (!this.playerDatas.equals(_value.playerDatas)) {
				return false;
			}
			if (!this.curTurnPlayer.equals(_value.curTurnPlayer)) {
				return false;
			}
			if (!this.curActionPlayer.equals(_value.curActionPlayer)) {
				return false;
			}
			if (!this.pileNumbers.equals(_value.pileNumbers)) {
				return false;
			}
			return true;
		}
		
		public DBoardData copy() {
			var _value = new DBoardData();
			_value.playerDatas = new ArrayList<>(this.playerDatas);
			_value.curTurnPlayer = this.curTurnPlayer;
			_value.curActionPlayer = this.curActionPlayer;
			_value.pileNumbers = new ArrayList<>(this.pileNumbers);
			return _value;
		}
		
		public DBoardData deepCopy() {
			var _value = new DBoardData();
			_value.playerDatas = new ArrayList<>();
			for(var _f: this.playerDatas) {
				if (_f != null) {
					_value.playerDatas.add(_f.deepCopy());
				} else {
					_value.playerDatas.add(null);
				}
			}
			_value.curTurnPlayer = this.curTurnPlayer;
			_value.curActionPlayer = this.curActionPlayer;
			_value.pileNumbers = new ArrayList<>();
			for(var _f: this.pileNumbers) {
				if (_f != null) {
					_value.pileNumbers.add(_f.deepCopy());
				} else {
					_value.pileNumbers.add(null);
				}
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	 * ????????????
	 */
	public static class DSkillInfo extends DSyncBase {
		public static final String TypeName = "DSkillInfo";
		
		/** ??????sn */
		private String sn;
		/** ??????id */
		private long skillId;
		/** ??????id -1???????????????skill */
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
		
		/** ??????sn */
		public String getSn() {
			return sn;
		}
		
		/** ??????sn */
		public void setSn(String sn) {
			this.sn = sn;
		}
		
		/** ??????id */
		public long getSkillId() {
			return skillId;
		}
		
		/** ??????id */
		public void setSkillId(long skillId) {
			this.skillId = skillId;
		}
		
		/** ??????id -1???????????????skill */
		public long getOwnerId() {
			return ownerId;
		}
		
		/** ??????id -1???????????????skill */
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.sn, sn);
			jsonObject.put(K.skillId, skillId);
			jsonObject.put(K.ownerId, ownerId);
			jsonObject.put(K.tip, tip);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	 * ???????????????n?????????
	 */
	public static class CSSelectCards extends DSyncBase {
		public static final String TypeName = "CSSelectCards";
		
		/** ???????????? */
		private List<Long> cardIds;

		public static class K {
			public static final String cardIds = "cardIds";
		}

		public CSSelectCards() {
			init();
		}

		@Override
		protected void init() {
			cardIds = new ArrayList<>();
		}
		
		/** ???????????? */
		public int getCardIdsCount() {
			return this.cardIds.size();
		}
		
		/** ???????????? */
		public List<Long> getCardIdsList() {
			return new ArrayList<>(cardIds);
		}
		
		/** ???????????? */
		public void setCardIdsList(List<Long> _value) {
			this.cardIds.clear();
			this.cardIds.addAll(_value);
		}

		/** ???????????? */
		public void addCardIds(long _value) {
			this.cardIds.add(_value);
		}
		
		/** ???????????? */
		public void addAllCardIds(List<Long> _value) {
			this.cardIds.addAll(_value);
		}
		
		/** ???????????? */
		public void clearCardIds() {
			this.cardIds.clear();
		}
		

		static CSSelectCards parseJSONObject(JSONObject jsonObject) {
			var _value = new CSSelectCards();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSSelectCards> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSSelectCards>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSSelectCards();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.cardIds, JSONObject.toJSONString(cardIds));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			cardIds = JSONObject.parseArray(jsonObject.getString(K.cardIds), Long.class);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CSSelectCards)) {
				return false;
			}
			var _value = (CSSelectCards) obj;
			if (!this.cardIds.equals(_value.cardIds)) {
				return false;
			}
			return true;
		}
		
		public CSSelectCards copy() {
			var _value = new CSSelectCards();
			_value.cardIds = new ArrayList<>(this.cardIds);
			return _value;
		}
		
		public CSSelectCards deepCopy() {
			var _value = new CSSelectCards();
			_value.cardIds = new ArrayList<>(this.cardIds);
			return _value;
		}
	}
	
	/**
	 * ----------------------------------------
	 * ???????????????
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.onlinePlayerList, getJSONArray(onlinePlayerList));
			jsonObject.put(K.roomList, getJSONArray(roomList));
			jsonObject.put(K.myRoom, getJSONObject(myRoom));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	
	public static class DPileNumber extends DSyncBase {
		public static final String TypeName = "DPileNumber";
		
		private String playerToken;
		private ECardPileType pileType;
		private int number;

		public static class K {
			public static final String playerToken = "playerToken";
			public static final String pileType = "pileType";
			public static final String number = "number";
		}

		public DPileNumber() {
			init();
		}

		@Override
		protected void init() {
			playerToken = "";
			pileType = ECardPileType.None;
			number = 0;
		}
		
		public String getPlayerToken() {
			return playerToken;
		}
		
		public void setPlayerToken(String playerToken) {
			this.playerToken = playerToken;
		}
		
		public ECardPileType getPileType() {
			return pileType;
		}
		
		public void setPileType(ECardPileType pileType) {
			this.pileType = pileType;
		}
		
		public int getNumber() {
			return number;
		}
		
		public void setNumber(int number) {
			this.number = number;
		}
		

		static DPileNumber parseJSONObject(JSONObject jsonObject) {
			var _value = new DPileNumber();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<DPileNumber> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<DPileNumber>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new DPileNumber();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.playerToken, playerToken);
			jsonObject.put(K.pileType, pileType.ordinal());
			jsonObject.put(K.number, number);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			playerToken = jsonObject.getString(K.playerToken);
			pileType = ECardPileType.values()[(jsonObject.getIntValue(K.pileType))];
			number = jsonObject.getIntValue(K.number);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof DPileNumber)) {
				return false;
			}
			var _value = (DPileNumber) obj;
			if (!this.playerToken.equals(_value.playerToken)) {
				return false;
			}
			if (!this.pileType.equals(_value.pileType)) {
				return false;
			}
			if (this.number != _value.number) {
				return false;
			}
			return true;
		}
		
		public DPileNumber copy() {
			var _value = new DPileNumber();
			_value.playerToken = this.playerToken;
			_value.pileType = this.pileType;
			_value.number = this.number;
			return _value;
		}
		
		public DPileNumber deepCopy() {
			var _value = new DPileNumber();
			_value.playerToken = this.playerToken;
			_value.pileType = this.pileType;
			_value.number = this.number;
			return _value;
		}
	}
	
	/**
	 * ???????????????????????????
	 */
	public static class SCSelectConfirm extends DSyncBase {
		public static final String TypeName = "SCSelectConfirm";
		
		/** ?????????????????? */
		private boolean cancel;
		private String tip;
		private String confirmTip;
		private String cancelTip;

		public static class K {
			public static final String cancel = "cancel";
			public static final String tip = "tip";
			public static final String confirmTip = "confirmTip";
			public static final String cancelTip = "cancelTip";
		}

		public SCSelectConfirm() {
			init();
		}

		@Override
		protected void init() {
			cancel = false;
			tip = "";
			confirmTip = "";
			cancelTip = "";
		}
		
		/** ?????????????????? */
		public boolean getCancel() {
			return cancel;
		}
		
		/** ?????????????????? */
		public void setCancel(boolean cancel) {
			this.cancel = cancel;
		}
		
		public String getTip() {
			return tip;
		}
		
		public void setTip(String tip) {
			this.tip = tip;
		}
		
		public String getConfirmTip() {
			return confirmTip;
		}
		
		public void setConfirmTip(String confirmTip) {
			this.confirmTip = confirmTip;
		}
		
		public String getCancelTip() {
			return cancelTip;
		}
		
		public void setCancelTip(String cancelTip) {
			this.cancelTip = cancelTip;
		}
		

		static SCSelectConfirm parseJSONObject(JSONObject jsonObject) {
			var _value = new SCSelectConfirm();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<SCSelectConfirm> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<SCSelectConfirm>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new SCSelectConfirm();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.cancel, cancel);
			jsonObject.put(K.tip, tip);
			jsonObject.put(K.confirmTip, confirmTip);
			jsonObject.put(K.cancelTip, cancelTip);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			cancel = jsonObject.getBooleanValue(K.cancel);
			tip = jsonObject.getString(K.tip);
			confirmTip = jsonObject.getString(K.confirmTip);
			cancelTip = jsonObject.getString(K.cancelTip);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SCSelectConfirm)) {
				return false;
			}
			var _value = (SCSelectConfirm) obj;
			if (this.cancel != _value.cancel) {
				return false;
			}
			if (!this.tip.equals(_value.tip)) {
				return false;
			}
			if (!this.confirmTip.equals(_value.confirmTip)) {
				return false;
			}
			if (!this.cancelTip.equals(_value.cancelTip)) {
				return false;
			}
			return true;
		}
		
		public SCSelectConfirm copy() {
			var _value = new SCSelectConfirm();
			_value.cancel = this.cancel;
			_value.tip = this.tip;
			_value.confirmTip = this.confirmTip;
			_value.cancelTip = this.cancelTip;
			return _value;
		}
		
		public SCSelectConfirm deepCopy() {
			var _value = new SCSelectConfirm();
			_value.cancel = this.cancel;
			_value.tip = this.tip;
			_value.confirmTip = this.confirmTip;
			_value.cancelTip = this.cancelTip;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.skillId, skillId);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	
	/**
	 * ??????????????????
	 */
	public static class SCGameLog extends DSyncBase {
		public static final String TypeName = "SCGameLog";
		
		private String playerName;
		private EGameLogType type;
		private String log;

		public static class K {
			public static final String playerName = "playerName";
			public static final String type = "type";
			public static final String log = "log";
		}

		public SCGameLog() {
			init();
		}

		@Override
		protected void init() {
			playerName = "";
			type = EGameLogType.Turn;
			log = "";
		}
		
		public String getPlayerName() {
			return playerName;
		}
		
		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}
		
		public EGameLogType getType() {
			return type;
		}
		
		public void setType(EGameLogType type) {
			this.type = type;
		}
		
		public String getLog() {
			return log;
		}
		
		public void setLog(String log) {
			this.log = log;
		}
		

		static SCGameLog parseJSONObject(JSONObject jsonObject) {
			var _value = new SCGameLog();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<SCGameLog> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<SCGameLog>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new SCGameLog();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.playerName, playerName);
			jsonObject.put(K.type, type.ordinal());
			jsonObject.put(K.log, log);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			playerName = jsonObject.getString(K.playerName);
			type = EGameLogType.values()[(jsonObject.getIntValue(K.type))];
			log = jsonObject.getString(K.log);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof SCGameLog)) {
				return false;
			}
			var _value = (SCGameLog) obj;
			if (!this.playerName.equals(_value.playerName)) {
				return false;
			}
			if (!this.type.equals(_value.type)) {
				return false;
			}
			if (!this.log.equals(_value.log)) {
				return false;
			}
			return true;
		}
		
		public SCGameLog copy() {
			var _value = new SCGameLog();
			_value.playerName = this.playerName;
			_value.type = this.type;
			_value.log = this.log;
			return _value;
		}
		
		public SCGameLog deepCopy() {
			var _value = new SCGameLog();
			_value.playerName = this.playerName;
			_value.type = this.type;
			_value.log = this.log;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.card, card);
			jsonObject.put(K.count, count);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
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
	 * ?????????????????????????????????n?????????
	 */
	public static class SCSelectCards extends DSyncBase {
		public static final String TypeName = "SCSelectCards";
		
		private List<Long> cardIds;
		private String tip;
		/** ???????????? */
		private int minNumber;
		/** ???????????? */
		private int maxNumber;
		/** ?????????????????????????????????????????? */
		private List<DCombination> combinations;

		public static class K {
			public static final String cardIds = "cardIds";
			public static final String tip = "tip";
			public static final String minNumber = "minNumber";
			public static final String maxNumber = "maxNumber";
			public static final String combinations = "combinations";
		}

		public SCSelectCards() {
			init();
		}

		@Override
		protected void init() {
			cardIds = new ArrayList<>();
			tip = "";
			minNumber = 0;
			maxNumber = 0;
			combinations = new ArrayList<>();
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
		
		public String getTip() {
			return tip;
		}
		
		public void setTip(String tip) {
			this.tip = tip;
		}
		
		/** ???????????? */
		public int getMinNumber() {
			return minNumber;
		}
		
		/** ???????????? */
		public void setMinNumber(int minNumber) {
			this.minNumber = minNumber;
		}
		
		/** ???????????? */
		public int getMaxNumber() {
			return maxNumber;
		}
		
		/** ???????????? */
		public void setMaxNumber(int maxNumber) {
			this.maxNumber = maxNumber;
		}
		
		/** ?????????????????????????????????????????? */
		public int getCombinationsCount() {
			return this.combinations.size();
		}
		
		/** ?????????????????????????????????????????? */
		public List<DCombination> getCombinationsList() {
			return new ArrayList<>(combinations);
		}
		
		/** ?????????????????????????????????????????? */
		public void setCombinationsList(List<DCombination> _value) {
			this.combinations.clear();
			this.combinations.addAll(_value);
		}

		/** ?????????????????????????????????????????? */
		public void addCombinations(DCombination _value) {
			this.combinations.add(_value);
		}
		
		/** ?????????????????????????????????????????? */
		public void addAllCombinations(List<DCombination> _value) {
			this.combinations.addAll(_value);
		}
		
		/** ?????????????????????????????????????????? */
		public void clearCombinations() {
			this.combinations.clear();
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.cardIds, JSONObject.toJSONString(cardIds));
			jsonObject.put(K.tip, tip);
			jsonObject.put(K.minNumber, minNumber);
			jsonObject.put(K.maxNumber, maxNumber);
			jsonObject.put(K.combinations, getJSONArray(combinations));
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			cardIds = JSONObject.parseArray(jsonObject.getString(K.cardIds), Long.class);
			tip = jsonObject.getString(K.tip);
			minNumber = jsonObject.getIntValue(K.minNumber);
			maxNumber = jsonObject.getIntValue(K.maxNumber);
			combinations = DCombination.parseJSONArray(jsonObject.getJSONArray(K.combinations));
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
			if (!this.tip.equals(_value.tip)) {
				return false;
			}
			if (this.minNumber != _value.minNumber) {
				return false;
			}
			if (this.maxNumber != _value.maxNumber) {
				return false;
			}
			if (!this.combinations.equals(_value.combinations)) {
				return false;
			}
			return true;
		}
		
		public SCSelectCards copy() {
			var _value = new SCSelectCards();
			_value.cardIds = new ArrayList<>(this.cardIds);
			_value.tip = this.tip;
			_value.minNumber = this.minNumber;
			_value.maxNumber = this.maxNumber;
			_value.combinations = new ArrayList<>(this.combinations);
			return _value;
		}
		
		public SCSelectCards deepCopy() {
			var _value = new SCSelectCards();
			_value.cardIds = new ArrayList<>(this.cardIds);
			_value.tip = this.tip;
			_value.minNumber = this.minNumber;
			_value.maxNumber = this.maxNumber;
			_value.combinations = new ArrayList<>();
			for(var _f: this.combinations) {
				if (_f != null) {
					_value.combinations.add(_f.deepCopy());
				} else {
					_value.combinations.add(null);
				}
			}
			return _value;
		}
	}
	
	public static class CSSelectEmptySlot extends DSyncBase {
		public static final String TypeName = "CSSelectEmptySlot";
		
		/** -1???????????? */
		private int selected;

		public static class K {
			public static final String selected = "selected";
		}

		public CSSelectEmptySlot() {
			init();
		}

		@Override
		protected void init() {
			selected = 0;
		}
		
		/** -1???????????? */
		public int getSelected() {
			return selected;
		}
		
		/** -1???????????? */
		public void setSelected(int selected) {
			this.selected = selected;
		}
		

		static CSSelectEmptySlot parseJSONObject(JSONObject jsonObject) {
			var _value = new CSSelectEmptySlot();
			if (!jsonObject.isEmpty()) {
				_value.applyRecord(jsonObject);
			}
			return _value;
		}
		
		static List<CSSelectEmptySlot> parseJSONArray(JSONArray jsonArray) {
			var list = new ArrayList<CSSelectEmptySlot>();
			for (int i = 0; i < jsonArray.size(); i++) {
				var _value = new CSSelectEmptySlot();
				var jsonObject = jsonArray.getJSONObject(i);
				if (!jsonObject.isEmpty()) {
					_value.applyRecord(jsonObject);
				}
				list.add(_value);
			}
			return list;
		}

		@Override
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.selected, selected);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			selected = jsonObject.getIntValue(K.selected);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CSSelectEmptySlot)) {
				return false;
			}
			var _value = (CSSelectEmptySlot) obj;
			if (this.selected != _value.selected) {
				return false;
			}
			return true;
		}
		
		public CSSelectEmptySlot copy() {
			var _value = new CSSelectEmptySlot();
			_value.selected = this.selected;
			return _value;
		}
		
		public CSSelectEmptySlot deepCopy() {
			var _value = new CSSelectEmptySlot();
			_value.selected = this.selected;
			return _value;
		}
	}
	
	/**
	 * ????????????????????????
	 */
	public static class DCardPileInfo extends DSyncBase {
		public static final String TypeName = "DCardPileInfo";
		
		/** ????????????token */
		private String playerToken;
		/** ???????????? */
		private ECardPileType pileType;
		/** ?????????????????? */
		private ESlotType slotType;
		/** ??????slot???????????? */
		private boolean asPlanSlot;
		/** ??????slot?????????????????? */
		private boolean ready;
		/** ?????????????????? */
		private int slotIndex;
		/** ?????????????????????????????? 0???????????? */
		private int pileIndex;
		/** ????????????????????? */
		private int pileSize;

		public static class K {
			public static final String playerToken = "playerToken";
			public static final String pileType = "pileType";
			public static final String slotType = "slotType";
			public static final String asPlanSlot = "asPlanSlot";
			public static final String ready = "ready";
			public static final String slotIndex = "slotIndex";
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
			slotIndex = 0;
			pileIndex = 0;
			pileSize = 0;
		}
		
		/** ????????????token */
		public String getPlayerToken() {
			return playerToken;
		}
		
		/** ????????????token */
		public void setPlayerToken(String playerToken) {
			this.playerToken = playerToken;
		}
		
		/** ???????????? */
		public ECardPileType getPileType() {
			return pileType;
		}
		
		/** ???????????? */
		public void setPileType(ECardPileType pileType) {
			this.pileType = pileType;
		}
		
		/** ?????????????????? */
		public ESlotType getSlotType() {
			return slotType;
		}
		
		/** ?????????????????? */
		public void setSlotType(ESlotType slotType) {
			this.slotType = slotType;
		}
		
		/** ??????slot???????????? */
		public boolean getAsPlanSlot() {
			return asPlanSlot;
		}
		
		/** ??????slot???????????? */
		public void setAsPlanSlot(boolean asPlanSlot) {
			this.asPlanSlot = asPlanSlot;
		}
		
		/** ??????slot?????????????????? */
		public boolean getReady() {
			return ready;
		}
		
		/** ??????slot?????????????????? */
		public void setReady(boolean ready) {
			this.ready = ready;
		}
		
		/** ?????????????????? */
		public int getSlotIndex() {
			return slotIndex;
		}
		
		/** ?????????????????? */
		public void setSlotIndex(int slotIndex) {
			this.slotIndex = slotIndex;
		}
		
		/** ?????????????????????????????? 0???????????? */
		public int getPileIndex() {
			return pileIndex;
		}
		
		/** ?????????????????????????????? 0???????????? */
		public void setPileIndex(int pileIndex) {
			this.pileIndex = pileIndex;
		}
		
		/** ????????????????????? */
		public int getPileSize() {
			return pileSize;
		}
		
		/** ????????????????????? */
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.playerToken, playerToken);
			jsonObject.put(K.pileType, pileType.ordinal());
			jsonObject.put(K.slotType, slotType.ordinal());
			jsonObject.put(K.asPlanSlot, asPlanSlot);
			jsonObject.put(K.ready, ready);
			jsonObject.put(K.slotIndex, slotIndex);
			jsonObject.put(K.pileIndex, pileIndex);
			jsonObject.put(K.pileSize, pileSize);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			playerToken = jsonObject.getString(K.playerToken);
			pileType = ECardPileType.values()[(jsonObject.getIntValue(K.pileType))];
			slotType = ESlotType.values()[(jsonObject.getIntValue(K.slotType))];
			asPlanSlot = jsonObject.getBooleanValue(K.asPlanSlot);
			ready = jsonObject.getBooleanValue(K.ready);
			slotIndex = jsonObject.getIntValue(K.slotIndex);
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
			if (this.slotIndex != _value.slotIndex) {
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
			_value.slotIndex = this.slotIndex;
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
			_value.slotIndex = this.slotIndex;
			_value.pileIndex = this.pileIndex;
			_value.pileSize = this.pileSize;
			return _value;
		}
	}
	
	/**
	 * ???????????????????????????????????????
	 */
	public static class SCSelectSkillToActive extends DSyncBase {
		public static final String TypeName = "SCSelectSkillToActive";
		
		private List<DSkillInfo> skillInfos;
		private String tip;

		public static class K {
			public static final String skillInfos = "skillInfos";
			public static final String tip = "tip";
		}

		public SCSelectSkillToActive() {
			init();
		}

		@Override
		protected void init() {
			skillInfos = new ArrayList<>();
			tip = "";
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
		
		public String getTip() {
			return tip;
		}
		
		public void setTip(String tip) {
			this.tip = tip;
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
		public void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.skillInfos, getJSONArray(skillInfos));
			jsonObject.put(K.tip, tip);
		}

		@Override
		public void applyRecord(JSONObject jsonObject) {
			skillInfos = DSkillInfo.parseJSONArray(jsonObject.getJSONArray(K.skillInfos));
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
			if (!(obj instanceof SCSelectSkillToActive)) {
				return false;
			}
			var _value = (SCSelectSkillToActive) obj;
			if (!this.skillInfos.equals(_value.skillInfos)) {
				return false;
			}
			if (!this.tip.equals(_value.tip)) {
				return false;
			}
			return true;
		}
		
		public SCSelectSkillToActive copy() {
			var _value = new SCSelectSkillToActive();
			_value.skillInfos = new ArrayList<>(this.skillInfos);
			_value.tip = this.tip;
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
			_value.tip = this.tip;
			return _value;
		}
	}
	

	public static enum ECardPileType {
		None,
		/** ?????? */
		HandPile,
		/** ?????? */
		DrawPile,
		/** ????????? */
		DiscardPile,
		/** ???????????? */
		TrashPile,
		/** ????????? */
		MarkPile,
		/** ???????????? */
		SlotPile,
		/** ???????????? */
		HeroPile,
		/** ???????????? */
		ThreatenPile,
	}
	public static enum ERoomStage {
		Normal,
		Battle,
	}
	public static enum ESlotType {
		None,
		/** ????????? */
		Store,
		/** ????????? */
		Seal,
		/** ????????? */
		Ride,
		/** ????????? */
		Harass,
		/** ????????? */
		Charge,
		/** ????????? */
		Plan,
	}
	public static enum EGameLogType {
		Turn,
		Skill,
		Action,
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
	 * ----------------------------------------
	 * ?????????????????????
	 * ----------------------------------------
	 * ?????????????????????
	 */
	public static enum ETargetSlotAimType {
		None,
		/** ?????? */
		Occupy,
		/** ?????? */
		Store,
	}
}

