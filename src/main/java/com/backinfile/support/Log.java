package com.backinfile.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
	public static Logger core = LoggerFactory.getLogger("CORE");

	public static Logger timer = LoggerFactory.getLogger("TIMER");
	public static Logger reflection = LoggerFactory.getLogger("REFLECTION");
	public static Logger invoke = LoggerFactory.getLogger("INVOKE");

	public static Logger res = LoggerFactory.getLogger("RES");
	public static Logger client = LoggerFactory.getLogger("CLIENT");
	public static Logger server = LoggerFactory.getLogger("SERVER");
	public static Logger net = LoggerFactory.getLogger("NET");
	public static Logger view = LoggerFactory.getLogger("VIEW");

	public static Logger game = LoggerFactory.getLogger("GAME");
	public static Logger level = LoggerFactory.getLogger("LEVEL");
	public static Logger gameLog = LoggerFactory.getLogger("GAMELOG");
}
