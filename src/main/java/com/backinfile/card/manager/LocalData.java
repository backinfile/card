package com.backinfile.card.manager;

import com.alibaba.fastjson.JSON;
import com.backinfile.support.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LocalData {
	private static LocalData localData = null;

	public static LocalData instance() {
		if (localData == null) {
			load();
		}
		return localData;
	}

	public String token;
	public String name;

	public static void load() {
		FileHandle file = Gdx.files.internal(Res.PATH_LOCAL_DATA);
		if (file.exists()) {
			localData = (LocalData) JSON.parseObject(file.readString(), LocalData.class);
		} else {
			localData = new LocalData();
			localData.token = Utils.getRandomToken();
			localData.name = "knight" + Utils.getRandomNumber(3);
			localData.save();
		}
	}

	public void save() {
		FileHandle file = Gdx.files.local(Res.PATH_LOCAL_DATA);
		file.writeString(JSON.toJSONString(this), false);
	}
}
