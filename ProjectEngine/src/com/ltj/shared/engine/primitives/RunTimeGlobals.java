package com.ltj.shared.engine.primitives;

import java.util.HashMap;
import java.util.Map.Entry;

import com.ltj.shared.engine.RenderObject;

public class RunTimeGlobals {
	private RunTimeGlobals(){}

	private static HashMap<String,Boolean> boolMap = new HashMap<String,Boolean>();
	private static HashMap<String,Float> floatMap = new HashMap<String,Float>();
	private static HashMap<String,Integer> intMap = new HashMap<String,Integer>();
	private static HashMap<String,RenderObject> objectMap = new HashMap<String,RenderObject>();
	private static HashMap<String,ObjectPool> poolMap = new HashMap<String, ObjectPool>();
	
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
	public static void createPool(String key, String value, int count){
		poolMap.put(key, new ObjectPool(count, objectMap.get(value)));
	}
	
	public static void add(String key, ObjectPool value){
		poolMap.put(key, value);
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
	
	public static ObjectPool getObjectPool(String key){
		return poolMap.get(key);
	}
	
	public static String toJSON(){
		String globalsJSON = "{ \"Bool\":" ;
		globalsJSON = concatHashMapToJSON(globalsJSON, boolMap) + "\"Float\":";
		globalsJSON = concatHashMapToJSON(globalsJSON, floatMap) + "\"Int\":";
		globalsJSON = concatHashMapToJSON(globalsJSON, intMap) + "\"RenderObject\":";
		
		if (objectMap.isEmpty()){
			globalsJSON += "\"null\"";
		} else{
			globalsJSON += "[";
			for (Entry<String,RenderObject> entry : objectMap.entrySet()){
				globalsJSON += "{\"name\":\""+  entry.getKey()+ "\",\"value\":" +  entry.getValue().getId() + "},";
			}
			globalsJSON = globalsJSON.substring(0,globalsJSON.length()-1);
			globalsJSON += "]";
		}
		globalsJSON += ",\"ObjectPool\":";

		if (poolMap.isEmpty()){
			globalsJSON += "\"null\"";
		} else {
			globalsJSON += "[";
			for (Entry<String, ObjectPool> entry : poolMap.entrySet()){
				globalsJSON += "{\"name\":\""+  entry.getKey()+ "\"," +  entry.getValue().toJSON() + "},";
			}
			globalsJSON = globalsJSON.substring(0,globalsJSON.length()-1);
			globalsJSON += "]";
		}

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
