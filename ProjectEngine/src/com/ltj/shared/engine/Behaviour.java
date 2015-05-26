package com.ltj.shared.engine;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public abstract class Behaviour<T extends RenderObject> {
	
	private static HashMap<String, Behaviour<? extends GameObject>> behaviours = new HashMap<String,Behaviour<? extends GameObject>>();
	
	public static void addBehaviour(String name, Behaviour<? extends GameObject> b){
		behaviours.put(name, b);
	}
	
	protected T gameObject;
	
	public final void allocateObject(T renderObject){
		this.gameObject = renderObject;
	}
	
	
	public final void sendMessage(GameObject obj,String method, Object... params){
		try {
			Method m = obj.getClass().getMethod("getBehaviour");
			Behaviour<? extends GameObject> b = (Behaviour<? extends GameObject>) m.invoke(obj);
			Method msg;
			if (params.length > 0){
				Class<?>[] classes = new Class<?>[params.length];
				for (int i = 0; i < params.length;i++){
					classes[i] = params[i].getClass();
				}
				msg = b.getClass().getMethod(method,classes);
			} else {
				msg = b.getClass().getMethod(method);
			}
			msg.setAccessible(true);
			msg.invoke(b, params);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public final GameObject getObjectByID(String id){
		return Updater.getObjectByID(id);
	}
	
	public final HudElement getHudElementByID(String id){
		return HeadsUpDisplay.get(id);
	}
	
	public final GameObject createNewGameObject(GameObject obj, float x, float y, float rot){
		GameObject o = obj.cloneObject();
		o.translate(x, y);
		o.rotate(rot);
		Updater.addRenderable((RenderObject) o);
		return o;
	}


	public abstract void start();
	public abstract void update();


	public void onCollisionEnter(GameObject collider) {
		
		
	}


	public void onCollision(GameObject collider) {
		
	}
	
	public void onChildCollisionEnter(GameObject collider) {
		
	}


	public void onChildCollision(GameObject child, GameObject collider){
		
	}
	
	
	public void onChildCollisionExit(GameObject child, GameObject collider) {
		
	}


	public void onCollisionExit(GameObject collider) {
		
	}


	
}
