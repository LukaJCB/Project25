package com.ltj.shared.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public abstract class Updater {

	private static HashMap<String, RenderObject> ids = new HashMap<String, RenderObject>();
	public static void addId(String id, RenderObject r) {
		ids.put(id, r);
	}

	public static void removeId(String id){
		ids.remove(id);
	}

	public static RenderObject getObjectByID(String id) {
		return ids.get(id);
	}



	private LinkedList<RenderObject> allObjects;
	private ArrayList<ModeSevenObject> allMSObjects;


	public Updater(){
		//initialize Lists
		allObjects = new LinkedList<RenderObject>();
		allMSObjects = new ArrayList<ModeSevenObject>();
	}



	public List<RenderObject> getAllObjects() {
		return allObjects;
	}
	
	public void addRenderable(RenderObject r){
		allObjects.add(r);
	}

	public void addMSRenderable(ModeSevenObject r){
		allObjects.add(r);
		allMSObjects.add(r);
	}
	
	public void update() {
		for (RenderObject r :allObjects){
			r.update();
		}
		
		checkCollisions();
		Camera.calcPVMatrix();
	}
	private void checkCollisions() {
//		QuadTree qTree = new QuadTree(0,new Rectangle(-5, -5, 10, 10));
//		for (RenderObject r: allObjects){
//			qTree.insert(r);
//		}
//		qTree.collideAll();
		for (int i = 0;i < allObjects.size(); i++){
			for (int j = i+1; j < allObjects.size(); j++){
				if (allObjects.get(i).collidesWith(allObjects.get(j))){
					allObjects.get(i).onCollision(allObjects.get(j));
					allObjects.get(j).onCollision(allObjects.get(i));
				}
			}
		}
	}
	
	public void start(){
		for (RenderObject r : allObjects){
			r.start();
		}
	}



	public List<ModeSevenObject> getAllMSObjects() {
		return allMSObjects;
	}
	
	

}
