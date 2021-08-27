package com.backinfile.card.gen;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.backinfile.dSync.model.DSyncBaseHandler;
import com.backinfile.dSync.model.DSyncException;
import com.backinfile.dSync.model.Mode;

public class ClientMessage extends DSyncBaseHandler {
	private Root root;
	private List<DSyncListener> listeners = new ArrayList<>();

	public ClientMessage(Mode mode) {
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
		if (obj instanceof Root) {
			for (var listenr : listeners) {
				listenr.onDataChange((Root) (obj));
			}
			return;
		}
		if (obj instanceof DHumanOper) {
			for (var listenr : listeners) {
				listenr.onDataChange((DHumanOper) (obj));
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
		public void onDataChange(Root data) {
		}
		
		public void onDataChange(DHumanOper data) {
		}
		
	}

	@Override
	protected DSyncBase newDSyncInstance(String typeName) {
		switch (typeName) {
		case Root.TypeName:
			return new Root();
		case DHumanOper.TypeName:
			return new DHumanOper();
		default:
			return null;
		}
	}

	/**
	 * --------------------
	 * 客户端->服务器
	 * --------------------
	 */
	public static class Root extends DSyncBase {
		public static final String TypeName = "Root";
		
		private String token;
		private DHumanOper oper;

		public static class K {
			public static final String token = "token";
			public static final String oper = "oper";
		}

		public Root() {
			init();
		}

		public static Root newInstance(ClientMessage _handler) {
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
			token = "";
			oper = null;
		}
		
		public String getToken() {
			return token;
		}
		
		public void setToken(String token) {
			this.token = token;
			onChanged();
		}
		public DHumanOper getOper() {
			return oper;
		}
		
		public void setOper(DHumanOper oper) {
			this.oper = oper;
			onChanged();
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.token, token);
			jsonObject.put(K.oper, oper.get_dSync_id());
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			token = jsonObject.getString(K.token);
			oper = (DHumanOper) handler.get(jsonObject.getLongValue(K.oper));
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
			if (!this.token.equals(_value.token)) {
				return false;
			}
			if (!this.oper.equals(_value.oper)) {
				return false;
			}
			return true;
		}
		
		public Root copy() {
			var _value = new Root();
			_value._dSync_id = -1;
			_value.token = this.token;
			_value.oper = this.oper;
			return _value;
		}
		
		
		public Root deepCopy() {
			var _value = new Root();
			_value._dSync_id = -1;
			_value.token = this.token;
			_value.oper = this.oper.deepCopy();
			return _value;
		}
	}
	
	public static class DHumanOper extends DSyncBase {
		public static final String TypeName = "DHumanOper";
		
		private EOperType type;
		private long id;

		public static class K {
			public static final String type = "type";
			public static final String id = "id";
		}

		public DHumanOper() {
			init();
		}

		public static DHumanOper newInstance(ClientMessage _handler) {
			if (_handler.mode == Mode.Client) {
				throw new DSyncException("Client模式下，不能创建DSync数据对象");
			}
			DHumanOper _struct = new DHumanOper();
			if (_handler.mode == Mode.Server) {
				_handler.put(_struct);
			}
			return _struct;
		}

		@Override
		protected void init() {
			type = EOperType.Reset;
			id = 0;
		}
		
		public EOperType getType() {
			return type;
		}
		
		public void setType(EOperType type) {
			this.type = type;
			onChanged();
		}
		public long getId() {
			return id;
		}
		
		public void setId(long id) {
			this.id = id;
			onChanged();
		}

		@Override
		protected void getRecord(JSONObject jsonObject) {
			jsonObject.put(DSyncBase.K.TypeName, TypeName);
			jsonObject.put(K.type, type.ordinal());
			jsonObject.put(K.id, id);
		}

		@Override
		protected void applyRecord(JSONObject jsonObject) {
			type = EOperType.values()[(jsonObject.getIntValue(K.type))];
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
			if (!(obj instanceof DHumanOper)) {
				return false;
			}
			var _value = (DHumanOper) obj;
			if (!this.type.equals(_value.type)) {
				return false;
			}
			if (this.id != _value.id) {
				return false;
			}
			return true;
		}
		
		public DHumanOper copy() {
			var _value = new DHumanOper();
			_value._dSync_id = -1;
			_value.type = this.type;
			_value.id = this.id;
			return _value;
		}
		
		
		public DHumanOper deepCopy() {
			var _value = new DHumanOper();
			_value._dSync_id = -1;
			_value.type = this.type;
			_value.id = this.id;
			return _value;
		}
	}
	


	public static enum EOperType {
		Reset,
		Skill,
	}
}

