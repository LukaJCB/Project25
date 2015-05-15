package com.ltj.engine;


import java.util.HashMap;

import com.ltj.engine.primitives.RenderObject;

public abstract class Behaviour<T extends RenderObject> {
	
	private static HashMap<String, Behaviour<? extends RenderObject>> behaviours = new HashMap<String,Behaviour<? extends RenderObject>>();
	
	public static void addBehaviour(String name, Behaviour<? extends RenderObject> b){
		behaviours.put(name, b);
	}
	
	protected T gameObject;
	
	public void allocateObject(T renderObject){
		this.gameObject = renderObject;
	}
	
	
	public abstract void start();
	public abstract void update();


	public void onCollision(RenderObject collider) {
		
	}
	
}
