package com.backinfile.card.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.backinfile.support.Log;
import com.backinfile.support.Utils;
import com.backinfile.support.reflection.Timing;

public class LocalString {
	private static Map<String, LocalCardString> cardStringMap = new HashMap<>();
	private static Map<String, LocalSkillString> skillStringMap = new HashMap<>();
	private static Map<String, LocalActionString> actionStringMap = new HashMap<>();
	private static Map<String, LocalUIString> uiStringMap = new HashMap<>();
	private static List<LocalImagePathString> imagePathStringList = new ArrayList<>();

	@Timing("local string init")
	public static void init(String localStringConf) {
		JSONObject localString = JSONObject.parseObject(localStringConf);
		for (var cardString : localString.getJSONArray("cards").toJavaList(LocalCardString.class)) {
			cardString.sn = cardString.sn.toLowerCase();

			if (cardString.frontImages != null) {
				for (var imagePathString : cardString.frontImages) {
					imagePathStringList.add(imagePathString);
				}
			}
			if (cardString.backImages != null) {
				for (var imagePathString : cardString.backImages) {
					imagePathStringList.add(imagePathString);
				}
			}

			if (!Utils.isNullOrEmpty(cardString.sn)) {
				cardStringMap.put(cardString.sn, cardString);
				Log.res.info("read card string: {}", cardString.sn);
			}
		}
		for (var skillString : localString.getJSONArray("skills").toJavaList(LocalSkillString.class)) {
			skillString.sn = skillString.sn.toLowerCase();
			if (!Utils.isNullOrEmpty(skillString.sn)) {
				skillStringMap.put(skillString.sn, skillString);
			}
		}
		for (var actionString : localString.getJSONArray("actions").toJavaList(LocalActionString.class)) {
			actionString.sn = actionString.sn.toLowerCase();
			if (!Utils.isNullOrEmpty(actionString.sn)) {
				actionStringMap.put(actionString.sn, actionString);
			}
		}
		for (var uiString : localString.getJSONArray("ui").toJavaList(LocalUIString.class)) {
			uiString.sn = uiString.sn.toLowerCase();
			if (!Utils.isNullOrEmpty(uiString.sn)) {
				uiStringMap.put(uiString.sn, uiString);
			}
			if (uiString.image != null) {
				imagePathStringList.add(uiString.image);
			}
			if (uiString.images != null) {
				for (var imagePathString : uiString.images) {
					imagePathStringList.add(imagePathString);
				}
			}
		}
	}

	public static LocalCardString getCardString(String sn) {
		var cardString = cardStringMap.get(sn.toLowerCase());
		if (cardString != null) {
			return cardString;
		}
		Log.res.warn("mising card string sn: {}", sn.toLowerCase());
		return LocalCardString.EMPTY_STRING;
	}

	public static LocalSkillString getSkillString(String sn) {
		var localSkillString = skillStringMap.get(sn.toLowerCase());
		if (localSkillString != null) {
			return localSkillString;
		}
//		Log.res.warn("mising skill string sn: {}", sn.toLowerCase());
		return LocalSkillString.EMPTY_STRING;
	}

	public static LocalActionString getActionString(String sn) {
		var localActionString = actionStringMap.get(sn.toLowerCase());
		if (localActionString != null) {
			return localActionString;
		}
//		Log.res.warn("mising action string sn: {}", sn.toLowerCase());
		return LocalActionString.EMPTY_STRING;
	}

	public static LocalUIString getUIString(String sn) {
		var localUIString = uiStringMap.get(sn.toLowerCase());
		if (localUIString != null) {
			return localUIString;
		}
		Log.res.warn("mising ui string sn: {}", sn.toLowerCase());
		return LocalUIString.EMPTY_STRING;
	}

	public static Collection<LocalCardString> getAllCardStrings() {
		return cardStringMap.values();
	}

	public static Collection<LocalImagePathString> getAllImagePathStrings() {
		return imagePathStringList;
	}

	public static class LocalCardString {
		public static final LocalCardString EMPTY_STRING = new LocalCardString();

		public String sn = "[SN]";
		public String name = "[NAME]";

		public LocalImagePathString[] frontImages = null; // 卡图
		public LocalImagePathString[] backImages = null; // 卡背
	}

	public static class LocalImagePathString {
		public String path = "[path]";
		public int[] locate; // 指定切分
	}

	public static class LocalActionString {
		public static final LocalActionString EMPTY_STRING = new LocalActionString();
		public String sn = "[SN]";
		public String tip = "[TIP]";
		public String[] tips = new String[] { "[TIP0]", "[TIP1]", "[TIP2]" };
	}

	public static class LocalSkillString {
		public static final LocalSkillString EMPTY_STRING = new LocalSkillString();
		public String sn = "[SN]";
		public String name = "[NAME]";
		public String tip = "[TIP]";
	}

	public static class LocalUIString {
		public static final LocalUIString EMPTY_STRING = new LocalUIString();
		public String sn = "[SN]";
		public String str = "[STR]";
		public LocalImagePathString image;
		public LocalImagePathString[] images;
		public String[] strs = new String[] { "[STR0]", "[STR1]", "[STR2]" };
	}
}
