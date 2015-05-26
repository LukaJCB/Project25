package com.ltj.shared.engine;

import java.util.ArrayList;


public class HeadsUpDisplay {

	private float width,height;
	private ArrayList<HudElement> hudElements = new ArrayList<HudElement>();

	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void render(){
		for (HudElement r : hudElements){
			r.render(width, height);
		}
	}
	
	public void addHudElement(HudElement element){
		hudElements.add(element);
	}

	public void flush(){
		
	}
	
}
