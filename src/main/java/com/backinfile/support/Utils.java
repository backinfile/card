package com.backinfile.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
	public static final String UTF8 = "utf-8";
	private static final Random RANDOM = new Random();
	public static final String LETTER_AND_NUMBER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	public static final String NUMBER = "1234567890";

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.equals("");
	}

	public static <T> ArrayList<T> subList(List<T> list, int fromIndex, int toIndex) {
		return new ArrayList<T>(list.subList(fromIndex, toIndex));
	}

	public static int indexOf(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (value == array[i]) {
				return i;
			}
		}
		return -1;
	}

	public static int[] str2IntArray(String str) {
		String[] strs = str.split(",");
		int[] result = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			result[i] = Integer.valueOf(strs[i]);
		}
		return result;
	}

	public static void setRndSeed(long seed) {
		RANDOM.setSeed(seed);
	}

	public static double nextDouble() {
		return RANDOM.nextDouble();
	}

	public static int nextInt(int a, int b) {
		return RANDOM.nextInt(b - a) + a;
	}

	public static double nextDouble(double a, double b) {
		return RANDOM.nextDouble() * (b - a) + a;
	}

	public static String getBlankString(int n) {
		var sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

	public static String getRandomToken() {
		var rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			int index = rnd.nextInt(LETTER_AND_NUMBER.length());
			sb.append(LETTER_AND_NUMBER.charAt(index));
		}
		return sb.toString();
	}

	public static String getRandomNumber(int n) {
		var rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int index = rnd.nextInt(NUMBER.length());
			sb.append(NUMBER.charAt(index));
		}
		return sb.toString();
	}

}
