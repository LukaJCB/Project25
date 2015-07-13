package com.ltj.shared.engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import com.ltj.shared.engine.primitives.Globals;
import com.ltj.shared.engine.primitives.Rectangle;
import com.ltj.shared.engine.primitives.SpatialHashMap;


public abstract class Engine {

	public static final int DESKTOP = 0;
	public static final int ANDROID = 1;
	
	
	private static Rectangle collisionZone;


	private static int gameObjectIds;
	private static ArrayList<RenderObject> allObjects = new ArrayList<RenderObject>();
	private static ArrayList<RenderObject> dynamicObjects = new ArrayList<RenderObject>();
	private static SpatialHashMap shMap;
	private static ArrayList<ParticleEmitter> allParticleEmitters = new ArrayList<ParticleEmitter>();
	private static ArrayList<OrthoRenderObject> allOrthoRenderObjects = new ArrayList<OrthoRenderObject>();
	private static HeadsUpDisplay hud = new HeadsUpDisplay();
	private static boolean started;
	private static boolean zoneChanged;
	
	static int[] currentArea = new int[2];
	static HashMap<int[], Area> areas = new HashMap<int[], Area>();
	static float areaWidth, areaHeight;
	private static int resyncIds;

	
	
	public static ArrayList<OrthoRenderObject> getAllOrthoRenderObjects() {
		return allOrthoRenderObjects;
	}

	public static void flush(ArrayList<RenderObject> objects) {
		for (RenderObject r: allObjects){
			r.clear();
		}
		Camera.flush();
		allObjects = objects;
	}

	
	public static void addRenderable(RenderObject r){
		if (!started){
			allObjects.add(r);
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
				
				resyncIds++;
				gameObjectIds--;
			} else {
				r.update();
				if (resyncIds > 0){
					r.setId(r.getId()- resyncIds);
				}
			}
		}
		//reset ids
		resyncIds = 0;
		
		//collision detection
		checkCollisions();
		
		//animation
		for (RenderObject r : allObjects){
			r.animate();
		}
		
		Camera.calcPVMatrix();
		//add objects that try to get inserted this frame
		if (!dynamicObjects.isEmpty()){
			for (RenderObject r: dynamicObjects){
				addRenderableDynamic(r);
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
		hud.setDimensions(width, height);
		
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
	
	private static void addRenderableDynamic(RenderObject r){
		if (r.isLoaded()){
			allObjects.add(r);
			r.setId(gameObjectIds);
			gameObjectIds++;
			r.start();
		}
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
	
	public static String parseAll(){
		String json = "{ \"gameObjects\":[";
		for (RenderObject r: allObjects){
			json += r.toJSON() + ",";
		}
		json = json.substring(0,json.length()-1);
		json +=   "],\"particleEmitters\":[";
		for (ParticleEmitter pe : allParticleEmitters){
			json += pe.toJSON() + ",";
		}
		json = json.substring(0,json.length()-1);
		json += "],\"Globals\":" + Globals.toJSON(); 
		json +=   ",\"orthoObjects\":[";
		for (OrthoRenderObject or: allOrthoRenderObjects){
			json += or.toJSON() + ",";
		}
		json = json.substring(0,json.length()-1);
		json += "], \"Hud\":" + hud.toJSON() + ",\"Camera\":" + Camera.toJSON() + ",\"collisionZone\":" + collisionZone.toJSON();   
		json += "}";
		return json;
	}
	
	public static HeadsUpDisplay getHud(){
		return hud;
	}
	

}
