package com.ltj.shared.engine;

import java.util.ArrayList;

public class GameObjectController {


	private Behaviour<? extends GameObject> behaviour;
	private String behaviourName;
	private boolean disabled;
	private RenderObject parent;
	private ArrayList<Collider> colliders;
	private GameObject controllerObject;

	public GameObjectController(RenderObject obj){
		this.controllerObject = obj;
	}
	
	
	public void start(){
		if (behaviour != null && !disabled){
			behaviour.start();
		} 
	}

	public void update() {
		if (behaviour != null && !disabled){
			behaviour.update();
		}
	}

	public void checkCollision(RenderObject object) {
		if (disabled || object == controllerObject || object == parent || object.getParent() == controllerObject){
			//objects are related
			return;
		}

		if (this.colliders != null && object.getColliders() != null){ 
			for (Collider collider : colliders){
				for (Collider objCollider : object.getColliders()){
					if (collider.getBottom(controllerObject.getY(),controllerObject.getHeight()) < objCollider.getTop(object.getY(),object.getHeight()) &&
							collider.getTop(controllerObject.getY(),controllerObject.getHeight()) > objCollider.getBottom( object.getY(),object.getHeight()) &&
							collider.getRight(controllerObject.getX(),controllerObject.getWidth()) > objCollider.getLeft(object.getX(), object.getWidth()) &&
							collider.getLeft(controllerObject.getX(),controllerObject.getWidth()) < objCollider.getRight(object.getX(), object.getWidth())){

						onCollision(object);
						object.onCollision(controllerObject);
						return;
					}
				}
			}

		} 

	}

	public void addBehaviour(Behaviour<? extends GameObject> b){
		behaviour = b;
	}
	
	public Behaviour<? extends GameObject> getBehaviour() {
		return behaviour;
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

	public void onCollisionEnter(GameObject collider) {
		if (parent != null){
			parent.onChildCollisionEnter(controllerObject,collider);
		}
		if (behaviour != null){
			behaviour.onCollisionEnter(collider);
		}
	
	}

	public void onCollision(GameObject collider) {

		if (parent != null){
			parent.onChildCollision(controllerObject,collider);
		}
		if (behaviour != null){
			behaviour.onCollision(collider);
		}
	
	}

	public void onCollisionExit(GameObject collider) {
		if (parent != null){
			parent.onChildCollisionExit(controllerObject,collider);
		}
		if (behaviour != null){
			behaviour.onCollisionExit(collider);
		}
	
	}

	public void onChildCollisionEnter(GameObject child,GameObject collider) {
		if (behaviour != null){
			behaviour.onChildCollisionEnter(collider);
		}
		
	}

	public void onChildCollision(GameObject child,GameObject collider) {
		if (behaviour != null){
			behaviour.onChildCollision(child,collider);
		}
		
	}
 
	public void onChildCollisionExit(GameObject child,GameObject collider){
		if (behaviour != null){
			behaviour.onChildCollisionExit(child,collider);
		}
	}

	public void addBehaviourName(String name){
		behaviourName = name;
	}

	public String getBehaviourName() {
		return behaviourName;
	}
	
	public void setDisabled(boolean controllerDisabled) {
		this.disabled = controllerDisabled;
	}
	
	public boolean isDisabled(){
		return disabled;
	}

	
	public void clear() {
		behaviour.allocateObject(null);
		behaviour = null;
		controllerObject = null;
	}

	
	public RenderObject getParent() {
		return parent;
	}

	public void setParent(RenderObject parent) {
		this.parent = parent;
		parent.addChild(controllerObject);
	}

}
