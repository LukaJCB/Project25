package com.ltj.shared.engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import com.ltj.shared.engine.primitives.Rectangle;
import com.ltj.shared.engine.primitives.SpatialHashMap;


public abstract class Updater {

	private static HashMap<String,RenderObject> ids = new HashMap<String,RenderObject>();
	private static Rectangle collisionZone;


	public static void addId(String id, RenderObject r){
		ids.put(id, r);
	}
	
	public static void removeId(String id){
		ids.remove(id);
	}
	
	public static RenderObject getObjectByID(String id){
		return ids.get(id);
	}

	private static ArrayList<RenderObject> allObjects = new ArrayList<RenderObject>();
	private static ArrayList<RenderObject> dynamicObjects = new ArrayList<RenderObject>();
	private static ArrayList<ModeSevenObject> allMSObjects = new ArrayList<ModeSevenObject>();
	private static SpatialHashMap shMap;
	private static ArrayList<ParticleEmitter> allParticleEmitters = new ArrayList<ParticleEmitter>();
	private static ArrayList<OrthoRenderObject> allOrthoRenderObjects = new ArrayList<OrthoRenderObject>();
	private static boolean started;
	private static boolean zoneChanged;
	
	static int[] currentArea = new int[2];
	static HashMap<int[], Area> areas = new HashMap<int[], Area>();
	static float areaWidth, areaHeight;

	
	
	public static ArrayList<OrthoRenderObject> getAllOrthoRenderObjects() {
		return allOrthoRenderObjects;
	}

	public static void flush(ArrayList<RenderObject> objects,ArrayList<ModeSevenObject> mSObjects ) {
		for (RenderObject r: allObjects){
			r.clear();
		}
		Camera.flush();
		allObjects = objects;
		allMSObjects = mSObjects;
	}

	
	public static void addRenderable(RenderObject r){
		if (!started){
			allObjects.add(r);
		} else {
			dynamicObjects.add(r);
		}
	}

	public static void addRenderableList(List<RenderObject> list){
		if (!started){
			allObjects.addAll(list);
		} else {
			for (RenderObject r : list){
				dynamicObjects.add(r);
			}
		}
	}

	public static void addMSRenderable(ModeSevenObject r){
		if (!started){
			allObjects.add(r);
			allMSObjects.add(r);
		}
	}

	public static void addParticleEmitter(ParticleEmitter p){
		allParticleEmitters.add(p);
	}
	

	public static void addOrthoRenderObject(OrthoRenderObject o){
		allOrthoRenderObjects.add(o);
	}


	public static List<RenderObject> getAllObjects() {
		return allObjects;
	}
	
	public static List<ParticleEmitter> getAllParticleEmitters() {
		
		return allParticleEmitters;
	}

	public static void update() {
		ListIterator<RenderObject> i = allObjects.listIterator();
		while (i.hasNext()){
			RenderObject r = i.next();
			if (r.isDestroyed()){
				r.clear();
				i.remove();
			} else {
				r.update();
			}
		}
		checkCollisions();
		
		for (RenderObject r : allObjects){
			r.animate();
		}
		
		Camera.calcPVMatrix();
		if (!dynamicObjects.isEmpty()){
			for (RenderObject r: dynamicObjects){
				allObjects.add(r);
				r.start();
			}
			dynamicObjects.clear();
		}
		
		if (zoneChanged){
			zoneChanged = false;
			shMap.clear();
			shMap = new SpatialHashMap(5,5,collisionZone.getWidth(),collisionZone.getHeight(),collisionZone.getX(),collisionZone.getY());
		}
	}
	
	public static void setDimensions(int width, int height) {
		for (OrthoRenderObject r : allOrthoRenderObjects){
			
			r.setScreenDimensions(width, height);
		}
		
	}
	private static void checkCollisions() {
		
		shMap.clear();
		for (RenderObject r: allObjects){
			shMap.insert(r);
		}
		shMap.collideAll();
//		for (int i = 0;i < allObjects.size(); i++){
//			for (int j = i+1; j < allObjects.size(); j++){
//				allObjects.get(i).checkCollision(allObjects.get(j));
//			}
//		}
		
	}
	
	public static void onKeyInput(KeyEvent e){
		for (RenderObject r : allObjects){
			if (r.getBehaviour() != null){
				r.getBehaviour().onKeyInput(e);
			}
		}
	}
	
	public static void onKeyReleased(KeyEvent e){
		for (RenderObject r : allObjects){
			if (r.getBehaviour() != null){
				r.getBehaviour().onKeyRelease(e);
			}
		}
	}
	
	public static void start(){
		started = true;
		for (RenderObject r : allObjects){
			r.start();
		}
		shMap = new SpatialHashMap(5,5,collisionZone.getWidth(),collisionZone.getHeight(),collisionZone.getX(),collisionZone.getY());
		if (!dynamicObjects.isEmpty()){
			for (RenderObject r: dynamicObjects){
				allObjects.add(r);
				r.start();
			}
			dynamicObjects.clear();
		}
	}



	public static List<ModeSevenObject> getAllMSObjects() {
		return allMSObjects;
	}

	public static Rectangle getCollisionZone() {
		return collisionZone;
	}

	public static void setCollisionZone(Rectangle collisionZone) {
		Updater.collisionZone = collisionZone;
		if (started){
			zoneChanged = true;
		}
	}
	
	static void checkArea(){
		if (!cameraInArea(areas.get(currentArea))){
			int[] nextPosition = new int[2];
			for (int i = currentArea[0] - 1; i < currentArea[0] + 2; i++){
				for (int j = currentArea[1] - 1; j < currentArea[1] + 2; j++){
					nextPosition[0] = i;
					nextPosition[1] = j;
					Area next = areas.get(nextPosition);
					if (next != null && cameraInArea(next)){
						currentArea = nextPosition;
						areas.get(currentArea).setCollisionZone();
						return;
					}
				}
			}
		}
	}
	
	static boolean cameraInArea(Area a){
		return a.isInArea(Camera.getLookAt()[0],Camera.getLookAt()[1]);
	}
	
	static void setAreaSize(float width, float height){
		areaWidth = width;
		areaHeight = height;
	}
	
	static void addArea(int x, int y){
		Area a = new Area(areaWidth, areaHeight);
		a.translate(areaWidth*x + areaWidth/2, areaHeight * y + areaHeight/2);
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		areas.put(pos,a);
	}
	static Area getArea(int x, int y){
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		return areas.get(pos);
	}
	
	
	static void loadArea(int x, int y){
		//TODO
	}
	

}
