package com.leverx.sample.util;

import java.util.UUID;

import java.util.Random;

public class ValueGenerator {
	
	private static Random rand = new Random();
	
	private ValueGenerator() {}
	
	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}
	
	public static int getRandomInt(int minInc, int maxInc) {
		return rand.ints(minInc, (maxInc + 1)).findFirst().getAsInt();
	}

}
