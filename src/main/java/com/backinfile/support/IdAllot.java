package com.backinfile.support;

public class IdAllot {
	public static long idStart = 0;

	public static long applyId() {
		return idStart++;
	}

	public static void reset() {
		idStart = 0;
	}
}
