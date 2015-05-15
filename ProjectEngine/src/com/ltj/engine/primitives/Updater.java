package com.ltj.engine.primitives;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Updater {

	private static HashMap<String,RenderObject> tags = new HashMap<String,RenderObject>();



	public static void addId(String tag, RenderObject r){
		tags.put(tag, r);
	}
	
	public static void removeId(String tag){
		tags.remove(tag);
	}
	
	public static RenderObject getObjectByID(String tag){
		return tags.get(tag);
	}

	private ArrayList<RenderObject> allObjects;
	private ArrayList<ModeSevenObject> allMSObjects;

	
	
	public Updater(){
		//initialize Lists
		allObjects = new ArrayList<RenderObject>();
		allMSObjects = new ArrayList<ModeSevenObject>();
	}

	public List<RenderObject> getAllObjects() {
		return allObjects;
	}
	
	public void addRenderable(RenderObject r){
		allObjects.add(r);
	}
	
	public void addRenderableList(List<RenderObject> list){
		allObjects.addAll(list);
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
