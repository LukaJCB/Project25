package com.ltj.shared.engine.primitives;

import java.util.ArrayList;

import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Updater;



public class Scene {

	private ArrayList<RenderObject>  allObjects;
	
	public void start(){
	
	}

	public void init(){
		Updater.flush(allObjects);
		Camera.flush();
	}
	
	public void load(){
		//TODO
	}
	
}
