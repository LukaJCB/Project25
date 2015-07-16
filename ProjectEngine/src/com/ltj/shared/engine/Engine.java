package com.ltj.shared.engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.ltj.shared.engine.primitives.Rectangle;
import com.ltj.shared.engine.primitives.SpatialHashMap;


public abstract class Engine {

	public static final int DESKTOP = 0;
	public static final int ANDROID = 1;
	private static int platform;
	
	private static Rectangle collisionZone;


	private static int gameObjectIds,emitterIds;
	private static LinkedHashMap<Integer,RenderObject> allObjects = new LinkedHashMap<Integer,RenderObject>();
	private static LinkedHashMap<Integer,ParticleEmitter> allParticleEmitters = new LinkedHashMap<Integer,ParticleEmitter>();
	private static ArrayList<OrthoRenderObject> allOrthoRenderObjects = new ArrayList<OrthoRenderObject>();
	private static ArrayList<RenderObject> dynamicObjects = new ArrayList<RenderObject>();
	private static SpatialHashMap shMap;
	private static HeadsUpDisplay hud = new HeadsUpDisplay();
	private static boolean started;
	private static boolean zoneChanged;
	
	static int[] currentArea = new int[2];
	static HashMap<int[], Area> areas = new HashMap<int[], Area>();
	static float areaWidth, areaHeight;

	
	
	public static ArrayList<OrthoRenderObject> getAllOrthoRenderObjects() {
		return allOrthoRenderObjects;
	}

	public static void flush(LinkedHashMap<Integer,RenderObject> objects) {
		for (RenderObject r: allObjects.values()){
			r.clear();
		}
		Camera.flush();
		allObjects = objects;
	}

	
	public static void addRenderable(RenderObject r){
		if (!started){
			allObjects.put(gameObjectIds, r);
			r.setId(gameObjectIds);
			gameObjectIds++;
		} else {
			dynamicObjects.add(r);
		}
	}

	public static void addRenderableList(List<RenderObject> list){
		for (RenderObject r : list){
			addRenderable(r);
		}
		
	}



	public static void addParticleEmitter(ParticleEmitter p){
		allParticleEmitters.put(emitterIds, p);
		p.setId(emitterIds);
		emitterIds++;
	}
	

	public static void addOrthoRenderObject(OrthoRenderObject o){
		allOrthoRenderObjects.add(o);
	}


	public static LinkedHashMap<Integer, RenderObject> getAllObjects() {
		return allObjects;
	}
	
	public static LinkedHashMap<Integer, ParticleEmitter> getAllParticleEmitters() {
		return allParticleEmitters;
	}

	public static void update() {
		Iterator<RenderObject> it = allObjects.values().iterator();
		while (it.hasNext()){
			RenderObject r = it.next();
			if (r.isDestroyed()){
				r.clear();
				it.remove();
			} else {
				r.update();
			}
		}
		
		//collision detection
		checkCollisions();
		
		//animation
		for (RenderObject r : allObjects.values()){
			r.animate();
		}
		
		Camera.calcPVMatrix();
		//add objects that try to get inserted this frame
		if (!dynamicObjects.isEmpty()){
			for (int i = dynamicObjects.size()-1;i > -1;i--){
				RenderObject r = dynamicObjects.get(i);
				if (r.isLoaded()){
					r.finishLoading();
					addRenderableDynamic(dynamicObjects.remove(i));
					break;
				}
			}
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
		hud.setDimensions(width, height);
		
	}
	private static void checkCollisions() {
		
		shMap.clear();
		for (RenderObject r: allObjects.values()){
			shMap.insert(r);
		}
		shMap.collideAll();
//		for (int i = 0;i < allObjects.size(); i++){
//			for (int j = i+1; j < allObjects.size(); j++){
//				allObjects.get(i).checkCollision(allObjects.get(j));
//			}
//		}
		
	}
	
	private static void addRenderableDynamic(RenderObject r){
		allObjects.put(gameObjectIds, r);
		r.setId(gameObjectIds);
		gameObjectIds++;
		r.start();
	}

	public static void onKeyInput(KeyEvent e){
		for (RenderObject r : allObjects.values()){
			if (r.getBehaviour() != null){
				r.getBehaviour().onKeyInput(e);
			}
		}
	}
	
	public static void onKeyReleased(KeyEvent e){
		for (RenderObject r : allObjects.values()){
			if (r.getBehaviour() != null){
				r.getBehaviour().onKeyRelease(e);
			}
		}
	}
	
	public static void start(){
		started = true;
		for (RenderObject r : allObjects.values()){
			r.start();
		}
		shMap = new SpatialHashMap(5,5,collisionZone.getWidth(),collisionZone.getHeight(),collisionZone.getX(),collisionZone.getY());
		if (!dynamicObjects.isEmpty()){
			for (RenderObject r: dynamicObjects){
				addRenderableDynamic(r);
			}
			dynamicObjects.clear();
		}
		
	}

	public static Rectangle getCollisionZone() {
		return collisionZone;
	}

	public static void setCollisionZone(Rectangle collisionZone) {
		Engine.collisionZone = collisionZone;
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
	

	
	public static HeadsUpDisplay getHud(){
		return hud;
	}

	public static int getPlatform(){
		return platform;
	}
	

}
