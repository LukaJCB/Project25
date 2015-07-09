package com.ltj.shared.engine;


import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public abstract class Behaviour<T extends Sprite> {
	
	private static HashMap<String, Behaviour<? extends Sprite>> behaviours = new HashMap<String,Behaviour<? extends Sprite>>();
	
	public static void addBehaviour(String name, Behaviour<? extends Sprite> b){
		behaviours.put(name, b);
	}
	
	protected T gameObject;
	
	public final void allocateObject(T renderObject){
		this.gameObject = renderObject;
	}
	
	
	public final void sendMessage(Sprite obj,String method, Object... params){
		try {
			Method m = obj.getClass().getMethod("getBehaviour");
			
			//get behaviour object
			Behaviour<? extends Sprite> b = (Behaviour<? extends Sprite>) m.invoke(obj);
			Method msg;
			
			//get method object
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
			//execute method on behaviour object
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
	public final RenderObject getObjectByID(String id){
		return Engine.getObjectByID(id);
	}
	
	public final HudElement getHudElementByID(String id){
		return HeadsUpDisplay.get(id);
	}
	
	public final RenderObject createNewGameObject(RenderObject obj, float x, float y, float rot){
		//clone object and set Pos and Rotation
		RenderObject o = obj.cloneObject();
		o.setPosition(x, y);
		o.setRotation(rot);
		Engine.addRenderable(o);
		return o;
	}


	public abstract void start();
	public abstract void update();


	public void onCollisionEnter(Sprite collider) {
		
		
	}


	public void onCollision(Sprite collider) {
		
	}
	
	public void onChildCollisionEnter(Sprite collider) {
		
	}


	public void onChildCollision(Sprite child, Sprite collider){
		
	}
	
	
	public void onChildCollisionExit(Sprite child, Sprite collider) {
		
	}


	public void onCollisionExit(Sprite collider) {
		
	}

	public void onKeyInput(KeyEvent e){
		
	}
	
	public void onKeyRelease(KeyEvent e){
		
	}

	
}
