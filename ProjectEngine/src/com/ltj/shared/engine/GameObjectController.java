package com.ltj.shared.engine;

import java.util.ArrayList;

public class GameObjectController {


	private Behaviour<? extends Sprite> behaviour;
	private boolean disabled;
	private RenderObject parent;
	private ArrayList<Collider> colliders;
	private RenderObject controllerObject;

	public GameObjectController(RenderObject obj){
		this.controllerObject = obj;
	}
	
	
	public void start(){
		if (behaviour != null){
			behaviour.start();
		} 
	}

	public void update() {
		if (behaviour != null && !disabled){
			behaviour.update();
		}
	}

	public void checkCollision(RenderObject object) {
		if (disabled || object == controllerObject || object == parent || object.getParent() == controllerObject 
				||controllerObject == null || object == null || controllerObject.isDestroyed() || object.isDestroyed()){
			//objects are related, inactive or destroyed
			return;
		}

		if (this.colliders != null && object.getColliders() != null){ 
			for (Collider collider : colliders){
				for (Collider objCollider : object.getColliders()){
					if (collider.getBottom(controllerObject.getY(),controllerObject.getHeight()) < objCollider.getTop(object.getY(),object.getHeight()) &&
							collider.getTop(controllerObject.getY(),controllerObject.getHeight()) > objCollider.getBottom( object.getY(),object.getHeight()) &&
							collider.getRight(controllerObject.getX(),controllerObject.getWidth()) > objCollider.getLeft(object.getX(), object.getWidth()) &&
							collider.getLeft(controllerObject.getX(),controllerObject.getWidth()) < objCollider.getRight(object.getX(), object.getWidth())){

						//objects collide
						onCollision(object);
						object.onCollision(controllerObject);
						return;
					}
				}
			}

		} 

	}

	public void addBehaviour(Behaviour<? extends Sprite> b){
		behaviour = b;
	}
	
	public Behaviour<? extends Sprite> getBehaviour() {
		return behaviour;
	}

	public String getBehaviourName() {
		if (behaviour != null){
			return behaviour.getClass().getName();
		}
		return "null";
	}


	public void addCollider(Collider c){
		if (colliders == null){
			colliders = new ArrayList<Collider>();
		}
		colliders.add(c);
	}

	public Collider getCollider(int index) {
		return colliders.get(index);
	}

	public ArrayList<Collider> getColliders(){
		return colliders;
	}

	public void onCollisionEnter(Sprite collider) {
		if (parent != null){
			parent.onChildCollisionEnter(controllerObject,collider);
		}
		if (behaviour != null){
			behaviour.onCollisionEnter(collider);
		}
	
	}

	public void onCollision(Sprite collider) {

		if (parent != null){
			parent.onChildCollision(controllerObject,collider);
		}
		if (behaviour != null){
			behaviour.onCollision(collider);
		}
	
	}

	@Deprecated
	public void onCollisionExit(Sprite collider) {
		if (parent != null){
			parent.onChildCollisionExit(controllerObject,collider);
		}
		if (behaviour != null){
			behaviour.onCollisionExit(collider);
		}
	
	}
	@Deprecated
	public void onChildCollisionEnter(Sprite child,Sprite collider) {
		if (behaviour != null){
			behaviour.onChildCollisionEnter(collider);
		}
		
	}
	@Deprecated
	public void onChildCollision(Sprite child,Sprite collider) {
		if (behaviour != null){
			behaviour.onChildCollision(child,collider);
		}
		
	}
	@Deprecated
	public void onChildCollisionExit(Sprite child,Sprite collider){
		if (behaviour != null){
			behaviour.onChildCollisionExit(child,collider);
		}
	}

	

	public void setDisabled(boolean controllerDisabled) {
		this.disabled = controllerDisabled;
	}
	
	public boolean isDisabled(){
		return disabled;
	}

	
	public void clear() {
		if (behaviour != null){
			behaviour.allocateObject(null);
		}
		behaviour = null;
		controllerObject = null;
	}

	
	public RenderObject getParent() {
		return parent;
	}

	public void setParent(RenderObject parent) {
		this.parent = parent;
		//register child in parent
		parent.addChild(controllerObject);
	}


	public String toJSON() {
		String p;
		if (parent != null){
			p =  ""+parent.getId();
		} else {
			p = "-1";
		}
		String json = ",\"controller_disabled\":" + disabled + ",\"parent\":" + p
				+ ",\"behaviour\":\"" + getBehaviourName()+ "\",\"colliders\":";
		if (colliders != null){
			json += "[";
			for (Collider c: colliders){
				json += "{" + c.toJSON() +"},";
			}
			json = json.substring(0,json.length()-1);
			json += "],";
		} else {
			json += "\"null\",";
		}
		return json;
	}

}
