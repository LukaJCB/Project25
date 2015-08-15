package com.ltj.shared.engine;

import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.ltj.shared.engine.primitives.Position;
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
	private static Position currentArea;
	private static HashMap<Position, Area> areas = new HashMap<Position, Area>();
	private static float areaWidth, areaHeight;
	private static AreaMode areaMode;

	
	
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
		if (areaMode != AreaMode.NONE){
			checkArea();
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
	
		
		if (areaMode == AreaMode.HIDE){
			//deactivate all areas
			for (Area a : areas.values()){
					a.setInactive(true);
			}
			//reactivate the areas where the cam is in.
			Position pos = new Position(0,0);
			for (int i = -1; i < 2;i++){
				pos.setX(currentArea.getX() +i);
				for (int j = -1; j < 2;j++){
					pos.setY(currentArea.getY()+j);
					if (areas.get(pos) != null){
						areas.get(pos).setInactive(false);
					}
				}
			}
			areas.get(currentArea).setCollisionZone();
		}
		
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
	}
	
	private static void checkArea(){
		if (!cameraInArea(areas.get(currentArea))){

			Position nextPosition = new Position(0,0);
			for (int i = currentArea.getX() - 1; i < currentArea.getX() + 2; i++){
				for (int j = currentArea.getY() - 1; j < currentArea.getY() + 2; j++){
					nextPosition.setX(i);
					nextPosition.setY(j);
					Area next = areas.get(nextPosition);
					if (next != null){
						if(cameraInArea(next)){
							int dx = nextPosition.getX() - currentArea.getX();
							int dy = nextPosition.getY() - currentArea.getY();
							currentArea = nextPosition;
							changeArea(dx, dy);
							return;
						}
					}
				}
			}
		}
	}
	
	private static void changeArea(int dx, int dy){
		
		areas.get(currentArea).setCollisionZone();
		Position pos = new Position(0, 0);
		if (dy != 0){
			//unload or destroy in Y Direction
			for (int i = currentArea.getX()- (dx +1);i < currentArea.getX() +2 -dx;i++){
				pos.setY(currentArea.getY() - dy *2);
				pos.setX(i);
				if (areas.get(pos) != null){
					if (areaMode == AreaMode.HIDE){
						areas.get(pos).setInactive(true);
					} else if (areaMode == AreaMode.DYNAMIC_LOAD){
						areas.get(pos).destroy();
					}
				}
				
				//activate or load next areas
				pos.setY(currentArea.getY()+ dy);
				if (areas.get(pos) != null){
					if (areaMode == AreaMode.HIDE){
						areas.get(pos).setInactive(false);
					} else if (areaMode == AreaMode.DYNAMIC_LOAD){
						loadArea(pos.getX(), pos.getY());
					}
				}
			}
		}
		if (dx != 0){
			//unload or destroy in X direction
			for (int j = currentArea.getY() - (dy +1); j < currentArea.getY()+2 - dy;j++){
				pos.setX(currentArea.getX() - dx * 2);
				pos.setY(j);
				if (areas.get(pos) != null){
					if (areaMode == AreaMode.HIDE){
						areas.get(pos).setInactive(true);
					} else if (areaMode == AreaMode.DYNAMIC_LOAD){
						areas.get(pos).destroy();
					}
				}
				//activate or load next areas
				pos.setX(currentArea.getX()+ dx);
				if (areas.get(pos) != null){
					if (areaMode == AreaMode.HIDE){
						areas.get(pos).setInactive(false);
					} else if (areaMode == AreaMode.DYNAMIC_LOAD){
						loadArea(pos.getX(), pos.getY());
					}
				}
			}
		}
		
		shMap.clear();
		shMap = new SpatialHashMap(5,5,collisionZone.getWidth(),collisionZone.getHeight(),collisionZone.getX(),collisionZone.getY());

		
	}
	
	private static boolean cameraInArea(Area a){
		return a.isInArea(Camera.getLookAt()[0],Camera.getLookAt()[1]);
	}
	
	public static void setAreaSize(float width, float height){
		areaWidth = width;
		areaHeight = height;
	}
	
	public static void setCurrentArea(int x , int y){
		currentArea = new Position(x, y);
		areas.get(currentArea).setCollisionZone();
	}

	public static Area addArea(int x, int y){
		Area a = new Area(areaWidth, areaHeight);

		a.translate(areaWidth*x, areaHeight*y);
		areas.put(new Position(x, y),a);
		return a;
	}
	public static Area getArea(int x, int y){
		
		return areas.get(new Position(x, y));
	}
	
	
	public static void loadArea(int x, int y){
		//TODO
	}
	
	public static void setAreaMode(AreaMode mode){
		areaMode = mode;
	}

	
	public static HeadsUpDisplay getHud(){
		return hud;
	}

	public static int getPlatform(){
		return platform;
	}

	public static HashMap<Position, Area> getAreas() {
		return areas;
	}

	public static Position getCurrentArea() {
		return currentArea;
	}

	public static AreaMode getAreaMode() {
		return areaMode;
	}

	public static String getAreaSizeJSON() {
		return "[" + areaWidth + "," + areaHeight + "]";
	}

	public static boolean isStarted() {
		return started;
	}


}
