package com.backinfile.card;

import com.alibaba.fastjson.JSON;
import com.backinfile.support.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class LocalData {
	public String token;
	public String name;

	public static LocalData load() {
		FileHandle file = Gdx.files.internal(Res.PATH_LOCAL_DATA);
		if (file.exists()) {
			return (LocalData) JSON.parseObject(file.readString(), LocalData.class);
		} else {
			LocalData localData = new LocalData();
			localData.token = Utils.getRandomToken();
			localData.name = "knight" + Utils.getRandomNumber(3);
			localData.save();
			return localData;
		}
	}

	public void save() {
		FileHandle file = Gdx.files.local(Res.PATH_LOCAL_DATA);
		file.writeString(JSON.toJSONString(this), false);
	}
}
