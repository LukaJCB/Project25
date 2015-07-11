package com.ltj.shared.engine.primitives;

import java.util.ArrayList;

import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;



public class Scene {

	private ArrayList<RenderObject>  allObjects;
	
	public void start(){
	
	}

	public void init(){
		Engine.flush(allObjects);
		Camera.flush();
	}
	
	public void load(){
		//TODO
	}
	
}
