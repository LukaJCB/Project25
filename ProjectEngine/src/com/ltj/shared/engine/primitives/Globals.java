package com.ltj.shared.engine.primitives;

import java.util.HashMap;

import com.ltj.shared.engine.GameObject;

public class Globals {
	private Globals(){}

	private static HashMap<String,Boolean> boolMap = new HashMap<String,Boolean>();
	private static HashMap<String,Float> floatMap = new HashMap<String,Float>();
	private static HashMap<String,Integer> intMap = new HashMap<String,Integer>();
	private static HashMap<String,GameObject> objectMap = new HashMap<String,GameObject>();
	
	public static void add(String key, boolean value){
		boolMap.put(key, value);
	}
	public static void add(String key, float value){
		floatMap.put(key, value);
	}
	public static void add(String key, int value){
		intMap.put(key, value);
	}
	
	public static void add(String key, GameObject value){
		objectMap.put(key, value);
	}
	public static boolean getBool(String key){
		return boolMap.getOrDefault(key,false);
	}
	public static float getFloat(String key){
		return floatMap.getOrDefault(key, 0.0f);
	}
	public static int getInt(String key){
		return intMap.getOrDefault(key,0);
	}
	
	public static GameObject getGameObject(String key){
		return objectMap.get(key);
	}

}
