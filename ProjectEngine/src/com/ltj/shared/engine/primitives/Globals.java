package com.ltj.shared.engine.primitives;

import java.util.HashMap;

public class Globals {
	private Globals(){}

	private static HashMap<String,Boolean> boolMap = new HashMap<String,Boolean>();
	private static HashMap<String,Float> floatMap = new HashMap<String,Float>();
	private static HashMap<String,Integer> intMap = new HashMap<String,Integer>();
	
	public static void add(String key, boolean value){
		boolMap.put(key, value);
	}
	public static void add(String key, float value){
		floatMap.put(key, value);
	}
	public static void add(String key, int value){
		intMap.put(key, value);
	}
	public static boolean getBool(String key){
		return boolMap.get(key);
	}
	public static float getFloat(String key){
		if (floatMap.get(key) != null){
			return floatMap.get(key);
		}
		return 0;
	}
	public static float getInt(String key){
		return intMap.get(key);
	}
}
