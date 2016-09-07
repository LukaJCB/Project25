package com.ltj.shared.engine;

import java.util.LinkedHashMap;
import java.util.Map.Entry;



public class HeadsUpDisplay {

	private static LinkedHashMap<String,HudElement> hudElements = new LinkedHashMap<String,HudElement>();

	public static HudElement get(Object key) {
		return hudElements.get(key);
	}

	public void setDimensions(int width, int height) {
		for (HudElement r : hudElements.values()){
			
			r.setScreenDimensions(width, height);
		}
	}
	
	public void render(){
		for (HudElement r : hudElements.values()){
			r.render();
		}
	}
	
	public void addHudElement(String key, HudElement element){
		hudElements.put(key,element);
	}

	public void flush(){
		for (HudElement r : hudElements.values()){
			r.clear();
		}
		hudElements.clear();
	}
	
	public String toJSON(){
		if (hudElements.isEmpty()){
			return "\"null\"";
		}
		
		String hudJSON = "[";
		for (Entry<String, HudElement> hud : hudElements.entrySet()){
			hudJSON += "{\"name\":\""+ hud.getKey()+ "\",\"value\":" + hud.getValue().toJSON() + "},";
		}
		hudJSON = hudJSON.substring(0,hudJSON.length()-1);
		hudJSON += "]";
		return hudJSON;
		
	}
	
}
