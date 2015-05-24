package com.ltj.shared.engine.primitives;

import java.util.ArrayList;

import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.ModeSevenObject;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Updater;



public class Scene {

	private ArrayList<RenderObject>  allObjects;
	private ArrayList<ModeSevenObject> allMSObjects;
	
	public void start(){
	
	}

	public void init(){
		Updater.flush(allObjects, allMSObjects);
		Camera.flush();
	}
	
	public void load(){
		//TODO
	}
	
}
