package com.ltj.shared.engine.primitives;

import java.util.HashMap;
import java.util.Map.Entry;


public class SaveStateConstants {
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
	

	
	
	
	public static String toJSON(){
		String globalsJSON = "{ \"Bool\":" ;
		globalsJSON = concatHashMapToJSON(globalsJSON, boolMap) + "\"Float\":";
		globalsJSON = concatHashMapToJSON(globalsJSON, floatMap) + "\"Int\":";
		globalsJSON = concatHashMapToJSON(globalsJSON, intMap);
		
		
		globalsJSON = globalsJSON.substring(0,globalsJSON.length()-1);
		globalsJSON += "}";
		return globalsJSON;
	}
	
	private static String concatHashMapToJSON(String json, HashMap<String, ?> map){
		if (map.isEmpty()){
			json += "\"null\",";
			return json;
		}
		
		json += "[";
		for (Entry<String,?> entry : map.entrySet()){
			json += "{\"name\":\""+  entry.getKey()+ "\",\"value\":" +  entry.getValue() + "},";
		}
		json = json.substring(0,json.length()-1);
		json += "],";
		return json;
		
	}
}
