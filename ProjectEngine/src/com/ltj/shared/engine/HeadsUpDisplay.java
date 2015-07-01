package com.ltj.shared.engine;

import java.util.HashMap;


public class HeadsUpDisplay {

	private static HashMap<String,HudElement> hudElements = new HashMap<String,HudElement>();

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
	}
	
}
