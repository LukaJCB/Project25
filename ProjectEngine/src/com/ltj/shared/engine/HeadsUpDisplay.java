package com.ltj.shared.engine;

import java.util.ArrayList;


public class HeadsUpDisplay {

	private ArrayList<HudElement> hudElements = new ArrayList<HudElement>();

	public void setDimensions(int width, int height) {
		for (HudElement r : hudElements){
			r.setScreenDimensions(width, height);
		}
	}
	
	public void render(){
		for (HudElement r : hudElements){
			r.render();
		}
	}
	
	public void addHudElement(HudElement element){
		hudElements.add(element);
	}

	public void flush(){
		for (HudElement r : hudElements){
			r.clear();
		}
	}
	
}
