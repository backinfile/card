package com.backinfile.card.server.proto;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.backinfile.card.Settings;
import com.backinfile.support.Log;
import com.backinfile.support.ReflectionUtils;
import com.backinfile.support.Utils;

public class ProtoManager {
	private static Map<String, Class<?>> msgClassMap = new HashMap<>();
	private static Map<String, Method> msgCallerMap = new HashMap<>();
	private static int maxMsgClassNameLength = 10;

	public static void init() {
		initMsgClass();
		initMsgCaller();
	}

	private static void initMsgCaller() {
		List<Method> methods = new ArrayList<>();
		for (var clazz : ReflectionUtils.getClassesExtendsClass(Settings.PackageName, IMsgHandler.class)) {
			methods.addAll(Arrays.asList(clazz.getMethods()));
		}
		for (var method : methods) {
			if (Modifier.isStatic(method.getModifiers())) {
				continue;
			}
			if (!method.getName().equals("handleMsg")) {
				continue;
			}
			if (method.getParameterTypes().length != 1) {
				continue;
			}
			var parameter = method.getParameterTypes()[0];
			if (msgClassMap.containsKey(parameter.getSimpleName())) {
				msgCallerMap.put(parameter.getSimpleName(), method);
			}
		}
	}

	private static void initMsgClass() {
		for (var className : ReflectionUtils.getClasseNames(Settings.PackageName)) {
			var simpleName = getSimpleClassName(className);
			if (!simpleName.startsWith("SC") && !simpleName.startsWith("CS")) {
				continue;
			}
			if (simpleName.length() > maxMsgClassNameLength) {
				maxMsgClassNameLength = simpleName.length() + 1;
			}
			try {
				msgClassMap.put(simpleName, Class.forName(className));
			} catch (ClassNotFoundException e) {
				Log.res.error("error in load msg class {} \n {}", className, e.getMessage());
			}
		}
	}

	private static String getSimpleClassName(String fullName) {
		{
			var index = fullName.lastIndexOf("$");
			if (index >= 0) {
				return fullName.substring(index + 1, fullName.length());
			}
		}
		{
			var index = fullName.lastIndexOf(".");
			if (index >= 0) {
				return fullName.substring(index + 1, fullName.length());
			}
		}
		return fullName;
	}

	private static Object parseMsg(String str) {
		for (var entry : msgClassMap.entrySet()) {
			var className = entry.getKey();
			var clazz = entry.getValue();
			if (str.startsWith(className)) {
				return JSON.parseObject(str.substring(maxMsgClassNameLength), clazz);
			}
		}
		return null;
	}

	public static String packMsg(Object msg) {
		var simpleName = msg.getClass().getSimpleName();
		return simpleName + Utils.getBlankString(maxMsgClassNameLength - simpleName.length()) + JSON.toJSONString(msg);
	}

	public static void callMsgHandler(IMsgHandler msgHandler, String msgString) {
		Object msg = null;
		try {
			msg = parseMsg(msgString);
		} catch (Exception e) {
			Log.reflection.error("error in parseMsg \n" + msgString, e);
			return;
		}
		Method method = msgCallerMap.get(msg.getClass().getSimpleName());
		if (method != null) {
			try {
				method.invoke(msgHandler, msg);
			} catch (Exception e) {
				Log.reflection.error("error in invoke msgHandler", e);
			}
		}
	}
}
