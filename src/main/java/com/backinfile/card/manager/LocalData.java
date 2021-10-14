package com.backinfile.card.manager;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.backinfile.card.model.cards.Chap2HeroCard;
import com.backinfile.support.Log;
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

	public static String LOCALDATA_VERSION = "0.1";

	public String version;
	public String token;
	public String name;
	public int AILevel; // 0木桩 1训练机器人 2简单敌人 3噩梦敌人
	public HashMap<String, Integer> startPile;
	public String startHeroCard;

	public static void load() {
		FileHandle file = Gdx.files.internal(Res.PATH_LOCAL_DATA);
		if (file.exists()) {
			localData = (LocalData) JSON.parseObject(file.readString(), LocalData.class);
			if (LOCALDATA_VERSION.equals(localData.version)) {
				Log.res.info("read local data");
				return;
			}
		}

		// 初始化
		localData = new LocalData();
		localData.version = LOCALDATA_VERSION;
		localData.token = Utils.getRandomToken();
		localData.name = LocalString.getUIString("boardUIView").str;
		localData.AILevel = 2;
		localData.startPile = GameUtils.getDefaultStartPile();
		localData.startHeroCard = CardManager.getRandomCard(c -> c instanceof Chap2HeroCard);
		localData.save();
	}

	public void save() {
		FileHandle file = Gdx.files.local(Res.PATH_LOCAL_DATA);
		file.writeString(JSON.toJSONString(this), false);
		Log.res.info("save local data");
	}
}
