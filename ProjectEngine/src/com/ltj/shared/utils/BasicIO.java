package com.ltj.shared.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.ltj.java.utils.JoglTextResourceReader;
import com.ltj.shared.engine.Area;
import com.ltj.shared.engine.AreaMode;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.HudElement;
import com.ltj.shared.engine.OrthoRenderObject;
import com.ltj.shared.engine.ParticleEmitter;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Skybox;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.json.JSONArray;
import com.ltj.shared.engine.json.JSONException;
import com.ltj.shared.engine.json.JSONObject;
import com.ltj.shared.engine.primitives.BoxCollider;
import com.ltj.shared.engine.primitives.ObjectPool;
import com.ltj.shared.engine.primitives.Position;
import com.ltj.shared.engine.primitives.Rectangle;
import com.ltj.shared.engine.primitives.RunTimeGlobals;
import com.ltj.shared.engine.primitives.SaveStateConstants;

public class BasicIO {
	private static LinkedHashMap<Integer,RenderObject> childrenToAdd;



	private static LinkedHashMap<Integer,ArrayList<RenderObject>> parentsToAdd;



	public static void parseToDME(final String path, final String name){
		new Thread(new Runnable() {
			@Override
			public void run() {
				writeToPath(getJSON(),path + File.separator + name + ".dme");
			}

		}).start();
	}
	
	public static void parseToSave(final String path, final String name){
		new Thread(new Runnable() {
			@Override
			public void run() {
				StringEncoder enc = StringEncoder.getInstance();
				
				String json = "{\"Constants\":" + SaveStateConstants.toJSON() + "}";
				String encoded = enc.encrypt(json);
				writeToPath(encoded,path + name + ".sav");
			}

		}).start();
	}
	
	public static void loadFromDME(String path, String name) throws ClassNotFoundException, JSONException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
	
		parentsToAdd = new LinkedHashMap<Integer, ArrayList<RenderObject>>();
		childrenToAdd = new LinkedHashMap<Integer, RenderObject>();
		
		//load json
		String file = JoglTextResourceReader.readTextFileFromResource(path + File.separator + name);
	
		JSONObject json = new JSONObject(file);
		
		JSONObject cam = json.getJSONObject("Camera");
		Camera.setDistance(cam.getFloat("distance"));
		Camera.setLookAt(cam.getFloat("x"), cam.getFloat("y"));
		JSONObject skybox = cam.getJSONObject("skybox");
		if (skybox != null){
			int platform = skybox.getInt("platform");
			JSONArray paths = skybox.getJSONArray("paths");
			Camera.addSkyBox(new Skybox(paths.getString(0), paths.getString(1), paths.getString(2),
					paths.getString(3), paths.getString(4), paths.getString(5), platform));
		}
		
		JSONArray particleEmitters = json.getJSONArray("particleEmitters");
		for (int i = 0;i < particleEmitters.length(); i++){
			JSONObject emitter = particleEmitters.getJSONObject(i);
			Engine.addParticleEmitter(reconstructEmitter(emitter));
		}
		
		
		JSONArray gameObjects = json.getJSONArray("gameObjects");
		for (int i = 0; i < gameObjects.length(); i++){
			JSONObject renderObject = gameObjects.getJSONObject(i);
			Engine.addRenderable(reconstructObject(renderObject));
		}
		
		
		JSONObject globals = json.getJSONObject("Globals");
		
		JSONArray booleans = globals.getJSONArray("Bool");
		if (booleans != null){
			for (int i = 0; i < booleans.length(); i++){
				JSONObject bool = booleans.getJSONObject(i);
				RunTimeGlobals.add(bool.getString("name"), bool.getBoolean("value"));
			}
		}
		
		JSONArray floats = globals.getJSONArray("Float");
		if (floats != null){
			for (int i = 0; i < floats.length(); i++){
				JSONObject f = floats.getJSONObject(i);
				RunTimeGlobals.add(f.getString("name"), f.getFloat("value"));
			}
		}
		
		JSONArray ints = globals.getJSONArray("Int");
		if (ints != null){
			for (int i = 0; i < ints.length(); i++){
				JSONObject in = ints.getJSONObject(i);
				RunTimeGlobals.add(in.getString("name"), in.getInt("value"));
			}
		}
		
		JSONArray objects = globals.getJSONArray("RenderObject");
		if (objects != null){
			for (int i = 0; i < objects.length(); i++){
				JSONObject obj = objects.getJSONObject(i);
				RunTimeGlobals.add(obj.getString("name"), Engine.getAllObjects().get(obj.getInt("value")));
			}
		}
		
		JSONArray pools = globals.getJSONArray("ObjectPool");
		if (pools != null){
			for (int i = 0; i < pools.length(); i++){
				JSONObject pool = pools.getJSONObject(i);
				ObjectPool op = new ObjectPool(pool.getInt("id"), pool.getInt("count"));
				RunTimeGlobals.add(pool.getString("name"), op);
			}
		}
		
		String areaModeString = json.getString("AreaMode");
		AreaMode areaMode = AreaMode.valueOf(areaModeString);

		Engine.setAreaMode(areaMode);
		
		if (areaMode == AreaMode.HIDE){
			
			JSONArray areaSize = json.getJSONArray("AreaSize");
			Engine.setAreaSize(areaSize.getInt(0), areaSize.getInt(1));
			
			JSONArray areas = json.getJSONArray("Areas");
			for (int i = 0; i < areas.length();i++){
				JSONObject area = areas.getJSONObject(i);
				Area a = Engine.addArea(area.getInt("x"), area.getInt("y"));
				
				JSONArray objs = area.getJSONArray("Objects");
				for (int j = 0; j < objs.length();j++){
					a.addObjectWorldSpace(Engine.getAllObjects().get(objs.getInt(j)));
				}
			}
			
			//set current area
			JSONArray currentArea = json.getJSONArray("CurrentArea");
			Engine.setCurrentArea(currentArea.getInt(0),currentArea.getInt(1));
		} else if (areaMode == AreaMode.DYNAMIC_LOAD){
			//TODO
		}
		
		
		JSONArray orthos = json.getJSONArray("orthoObjects");
		if (orthos != null){
			for (int i = 0; i < orthos.length(); i++){
				JSONObject ortho = orthos.getJSONObject(i);
				Engine.addOrthoRenderObject(reconstructOrtho(ortho));
			}
		}
		
		JSONArray huds = json.getJSONArray("Hud");
		if (huds != null){
			for (int i = 0; i < huds.length(); i++){
				JSONObject hud = huds.getJSONObject(i);
				Engine.getHud().addHudElement(hud.getString("name"), reconstructHudElement(hud.getJSONObject("value")));
			}
		}
	
		
		JSONObject zone = json.getJSONObject("collisionZone");
		Engine.setCollisionZone(new Rectangle(zone.getFloat("x"), zone.getFloat("y"), zone.getFloat("width"),zone.getFloat("height")));
		
	}

	private static String getJSON(){
		String json = "{\"Camera\":" + Camera.toJSON()
				+ ",\"particleEmitters\": [";
		if (!Engine.getAllParticleEmitters().isEmpty()) {
			for (ParticleEmitter pe : Engine.getAllParticleEmitters()
					.values()) {
				json += pe.toJSON() + ",";
			}
			json = json.substring(0, json.length() - 1);
		}
		
		
		json += "],\"gameObjects\":[";
		if (!Engine.getAllObjects().isEmpty()) {
			for (RenderObject r : Engine.getAllObjects().values()) {
				json += r.toJSON() + ",";
			}
			json = json.substring(0, json.length() - 1);
		}

		json += "],\"Globals\":" + RunTimeGlobals.toJSON();
		json += ",\"AreaMode\":\"";
		if (Engine.getAreaMode() == null){
			json += AreaMode.NONE;
		} else {
			json += Engine.getAreaMode();
		}
		json += "\"";
		if (Engine.getAreaMode() == AreaMode.DYNAMIC_LOAD){
			
			//save area size
			json+= ",\"AreaSize\":" + Engine.getAreaSizeJSON();
			
			//save the current areas by reference
			json += ",\"CurrentAreas\":[";
			
			Position p = new Position(0,0);
			for (int i = 0; i < 3;i++){
				p.setX(Engine.getCurrentArea().getX()+i -1);
				for(int j = 0; j < 3;j++){
					p.setY(Engine.getCurrentArea().getY() +j -1);
					json += "{" + "\"x\":" + p.getX() + ",\"y\":" + p.getY() + ",\"Objects\":";
					json += Engine.getAreas().get(p).toJSON() + ",";
					
				}
			}
			json = json.substring(0, json.length() - 1);
			json+= "]";
			
			//TODO add all areas with full data somehow.
			
		} else if (Engine.getAreaMode() == AreaMode.HIDE){
			
			//save area size
			json+= ",\"AreaSize\":" + Engine.getAreaSizeJSON();
			
			//save all Areas by reference
			json += ",\"Areas\":[";

			for (Entry<Position, Area> e :Engine.getAreas().entrySet()){
				json += "{" + "\"x\":" + e.getKey().getX() + ",\"y\":" + e.getKey().getY() + ",\"Objects\":";
				json += e.getValue().toJSON() + "},";
			}
			json = json.substring(0, json.length() - 1);
			
			//save the currentArea
			json += "],\"CurrentArea\":[" + Engine.getCurrentArea().getX() + "," + Engine.getCurrentArea().getY() + "]";
		}
		
		
		json += ",\"orthoObjects\":[";
		if (!Engine.getAllOrthoRenderObjects().isEmpty()) {
			for (OrthoRenderObject or : Engine
					.getAllOrthoRenderObjects()) {
				json += or.toJSON() + ",";
			}
			json = json.substring(0, json.length() - 1);
		}
		json += "], \"Hud\":" + Engine.getHud().toJSON()
				+ ",\"collisionZone\":"
				+ Engine.getCollisionZone().toJSON();
		json += "}";
		
		
		return json;
	}

	private static void writeToPath(String source, String path){
		PrintStream out = null;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(path);
			out = new PrintStream(fileOut);
			out.print(source);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null){
				out.close();
			}
			if (fileOut != null){
				try {fileOut.close();} catch (IOException e) {}
			}
		}
	}
	
	private static ParticleEmitter reconstructEmitter(JSONObject obj) throws ClassNotFoundException, JSONException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> c = Class.forName(obj.getString("type"));
		
		ParticleEmitter emitter = (ParticleEmitter) c.getConstructor(int.class,int.class,float.class,float.class,float.class)
				.newInstance(obj.getInt("maxParticles"),obj.getInt("runningTime"), obj.getFloat("red"), obj.getFloat("green"),obj.getFloat("blue"));
		emitter.setPosition(obj.getFloat("x"), obj.getFloat("y"), obj.getFloat("z"));
		return emitter;
	}
	
	private static OrthoRenderObject reconstructOrtho(JSONObject obj){
		OrthoRenderObject o = new OrthoRenderObject(obj.getString("path"), obj.getInt("platform"));
		o.setPosition(obj.getFloat("x"), obj.getFloat("y"));
		o.setRotation(obj.getFloat("rotation"));
		o.setScaling(obj.getFloat("width"), obj.getFloat("height"));
		
		return o;
	}
	
	private static HudElement reconstructHudElement(JSONObject obj){
		HudElement e = new HudElement(obj.getString("path"), obj.getInt("platform"));
		e.setPosition(obj.getFloat("x"), obj.getFloat("y"));
		e.setRotation(obj.getFloat("rotation"));
		e.setScaling(obj.getFloat("width"), obj.getFloat("height"));
		
		return e;
	}

	@SuppressWarnings("unchecked")
	private static RenderObject reconstructObject(JSONObject obj) throws ClassNotFoundException, JSONException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		RenderObject r;
		//get class
		Class<?> c = Class.forName(obj.getString("type"));
		
		if (c.getSimpleName().equals("EmptyObject")){
			r = (RenderObject) c.newInstance(); 
		} else {
			Constructor<?> t = c.getConstructor(String.class, int.class, int.class);
			r = (RenderObject) t.newInstance(obj.getString("path"), obj.getInt("columns"),obj.getInt("rows"));
		}
		
		r.setId(obj.getInt("id"));
		
		//check if this object is a parent of someone
		if (parentsToAdd.get(r.getId()) != null){
			for (RenderObject child : parentsToAdd.get(r.getId())){
				child.setParent(r);
			}
			parentsToAdd.get(r.getId()).clear();
		}
		
		//check if this object is a child of someone
		if (childrenToAdd.get(r.getId()) != null){
			r.setParent(childrenToAdd.get(r.getId()));
			
		}
		
		r.setPosition(obj.getFloat("x"), obj.getFloat("y"));
		r.setScale(obj.getFloat("width"), obj.getFloat("height"));
		r.setMirroring(obj.getBoolean("mirroredX"), obj.getBoolean("mirroredY"));
		r.setRotation(obj.getFloat("rotation"));
		r.setZ(obj.getFloat("z"));
		r.setRendererDisabled(obj.getBoolean("renderer_disabled"));
		r.setModeSevenEnabled(obj.getBoolean("modeSEnabled"));
		r.setTag(obj.getString("tag"));
		r.setInactive(obj.getBoolean("inactive"));
		r.setInactiveOnLoad(obj.getBoolean("inactiveOnLoad"));
		r.setControllerDisabled(obj.getBoolean("controller_disabled"));
		if (obj.getFloat("repeatX") != 1 || obj.getFloat("repeatY") != 1){
			r.setRepeat(obj.getFloat("repeatX"), obj.getFloat("repeatY"));
		} else {
			r.setTexture(obj.getInt("textureCol"),obj.getInt("textureRow"));
		}
		
		
		//set up behaviour
		if (!obj.getString("behaviour").equals("null")){
			Class<?> behaviourClass = Class.forName(obj.getString("behaviour"));
			Behaviour<Sprite> behaviour = (Behaviour<Sprite>) behaviourClass.newInstance();
			r.addBehaviour(behaviour);
			behaviour.allocateObject(r);
		}
		
		//set up colliders
		JSONArray colliders = obj.getJSONArray("colliders");
		if (colliders != null){
			for (int i = 0; i < colliders.length(); i++){
				JSONObject collider = colliders.getJSONObject(i);
				BoxCollider boxCollider = new BoxCollider();
				boxCollider.setScaling(collider.getFloat("xScaling"), collider.getFloat("yScaling"));
				boxCollider.setOffet(collider.getFloat("xOffset"), collider.getFloat("yOffset"));
				r.addCollider(boxCollider);
			}
		}
		
		//set up animations
		JSONArray animations = obj.getJSONArray("animator");
		if (animations != null){
			for (int i = 0; i < animations.length();i++){
				JSONObject anim = animations.getJSONObject(i);
				r.addAnimation(anim.getString("name"), anim.getInt("animationTime"), 
						anim.getInt("texRow"), anim.getBoolean("looping"), anim.getInt("numCols"));
			}
		}
		
		//set up parent
		int parentId = obj.getInt("parent");
		if (parentId != -1){
			if ( parentId < r.getId()){
				r.setParent(Engine.getAllObjects().get(parentId));
			} else {
				if (parentsToAdd.get(parentId) == null){
					parentsToAdd.put(parentId, new ArrayList<RenderObject>());
				}
				parentsToAdd.get(parentId).add(r);
			}
		}


		//set up children
		JSONArray children = obj.getJSONArray("children");
		if (children != null){
			for (int i = 0; i < children.length();i++){
				int id = children.getInt(i);
				if (id < r.getId()){
					Engine.getAllObjects().get(id).setParent(r);
				} else {
					childrenToAdd.put(id, r);
				}
			}
		}
		
		//set up particleEmitters
		JSONArray emitters = obj.getJSONArray("emitters");
		if (emitters != null){
			for (int i = 0; i < emitters.length();i++){
				r.addParticleEmitter(Engine.getAllParticleEmitters().get(emitters.getInt(i)));
			}
		}
		
		return r;
	}
}
