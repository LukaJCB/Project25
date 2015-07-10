package com.ltj.shared.engine.primitives;

import java.util.HashMap;

import com.ltj.shared.engine.RenderObject;

public class Globals {
	private Globals(){}

	private static HashMap<String,Boolean> boolMap = new HashMap<String,Boolean>();
	private static HashMap<String,Float> floatMap = new HashMap<String,Float>();
	private static HashMap<String,Integer> intMap = new HashMap<String,Integer>();
	private static HashMap<String,RenderObject> objectMap = new HashMap<String,RenderObject>();
	
	public static void add(String key, boolean value){
		boolMap.put(key, value);
	}
	public static void add(String key, float value){
		floatMap.put(key, value);
	}
	public static void add(String key, int value){
		intMap.put(key, value);
	}
	
	public static void add(String key, RenderObject value){
		objectMap.put(key, value);
	}
	public static boolean getBool(String key){
		if (boolMap.get(key) != null){
			return boolMap.get(key);
		}
		return false;
	}
	public static float getFloat(String key){
		if (floatMap.get(key) != null){
			return floatMap.get(key);
		}
		return 0.0f;
	}
	public static int getInt(String key){
		if (intMap.get(key) != null){
			return intMap.get(key);
		} 
		return 0;
	}
	
	public static RenderObject getGameObject(String key){
		return objectMap.get(key);
	}

}
