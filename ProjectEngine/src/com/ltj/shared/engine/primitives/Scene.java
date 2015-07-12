package com.ltj.shared.engine.primitives;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;


import android.util.SparseArray;

import com.ltj.java.utils.JoglTextResourceReader;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.engine.Sprite;
import com.ltj.shared.engine.json.JSONArray;
import com.ltj.shared.engine.json.JSONException;
import com.ltj.shared.engine.json.JSONObject;



public class Scene {

	private static ArrayList<RenderObject>  allObjects;
	
	private static SparseArray<ArrayList<RenderObject>> parentsToAdd;
	private static SparseArray<RenderObject> childrenToAdd;
	public void start(){
	
	}

	public void init(){
		Engine.flush(allObjects);
		Camera.flush();
	}
	
	public static void load() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, JSONException{
		allObjects = new ArrayList<RenderObject>();
		parentsToAdd = new SparseArray<ArrayList<RenderObject>>();
		childrenToAdd = new SparseArray<RenderObject>();
		//load json
		JSONObject json = new JSONObject(JoglTextResourceReader.readTextFileFromResource("res/raw/test.json"));
		JSONArray gameObjects = json.getJSONArray("gameObjects");
		for (int i = 0; i < gameObjects.length(); i++){
			
			JSONObject renderObject = gameObjects.getJSONObject(i);
			RenderObject r = reconstructObject(renderObject);
			
			allObjects.add(r);
			System.out.println(r.toJSON());
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static RenderObject reconstructObject(JSONObject obj) throws ClassNotFoundException, JSONException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
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
		r.setControllerDisabled(obj.getBoolean("controller_disabled"));
		
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
				r.setParent(Engine.getAllObjects().get(obj.getInt("parent")));
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
		
		return r;
	}
	
}
