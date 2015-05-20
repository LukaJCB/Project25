package com.ltj.shared.engine;

import java.util.ArrayList;

public abstract class AbstractSprite implements RenderObject {



	private Behaviour<? extends GameObject> behaviour;
	private String behaviourName;
	private boolean destroyed;
	private ArrayList<Collider> colliders;
	private ArrayList<RenderObject> childList;
	private RenderObject parent;
	private String tag;
	private boolean lastCollision;
	protected SpriteRenderer renderer;
	
	public void translate(float dx, float dy) {
		renderer.translate(dx, dy);
		if (childList != null){
			for (RenderObject r : childList){
				r.translate(dx, dy,getWidth(),getHeight());
			}
		}
	}

	public void translate(float dx, float dy, float width, float height){
		renderer.translate(dx, dy, width, height);
	}



	public void rotate(float deg) {
		renderer.rotate(deg);
		if (childList != null){
			for (RenderObject r : childList){
				r.rotate(deg);
			}
		}
	}





	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
		if (childList != null){
			for (RenderObject r : childList){
				r.scale(sx, sy);
			}
		}
	}





	public float getX() {
		return renderer.getX();
	}





	public float getY() {
		return renderer.getY();
	}





	public float getHeight() {
		return renderer.getHeight();
	}





	public float getWidth() {
		return renderer.getWidth();
	}





	public float getRotation() {
		return renderer.getRotation();
	}



	

	public void start(){
		if (behaviour != null){
			behaviour.start();
		}
	}

	@Override
	public void update() {
		if (behaviour != null){
			behaviour.update();
		}
	}

	public boolean collidesWith(RenderObject object) {
		if (this.colliders != null && object.getColliders() != null && object != parent && object.getParent() != this){
			for (Collider collider : colliders){
				for (Collider objCollider : object.getColliders()){
					if (collider.getBottom(getY(),getHeight()) < objCollider.getTop(object.getY(),object.getHeight()) &&
							collider.getTop(getY(),getHeight()) > objCollider.getBottom( object.getY(),object.getHeight()) &&
							collider.getRight(getX(),getWidth()) > objCollider.getLeft(object.getX(), object.getWidth()) &&
							collider.getLeft(getX(),getWidth()) < objCollider.getRight(object.getX(), object.getWidth())){
						if (!lastCollision){
							onCollisionEnter(object);
							lastCollision = true;
						}
						
						return true;
					}
				}
			}
		}
		if (lastCollision){
			onCollisionExit(object);
			lastCollision = false;
		}
		
		return false;
	}

	@Override
	public void addBehaviour(Behaviour<? extends GameObject> b){
		behaviour = b;
	}
	@Override
	public Behaviour<? extends GameObject> getBehaviour() {
		return behaviour;
	}

	@Override
	public void addCollider(Collider c){
		if (colliders == null){
			colliders = new ArrayList<Collider>();
		}
		colliders.add(c);
	}

	@Override
	public void render() {
		if (!destroyed){
			renderer.render();
		}
	}

	public void destroy() {
		destroyed = true;
	}

	
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public Collider getCollider(int index) {
		return colliders.get(index);
	}
	
	@Override
	public ArrayList<Collider> getColliders(){
		return colliders;
	}
	
	public void addBehaviourName(String name){
		behaviourName = name;
	}
	
	public String getBehaviourName() {
		return behaviourName;
	}

	@Override
	public void onCollisionEnter(GameObject collider) {
		if (parent != null){
			parent.onChildCollision(this,collider);
		}
		if (behaviour != null){
			behaviour.onCollision(collider);
		}

	}

	@Override
	public void onCollision(GameObject collider) {

		if (parent != null){
			parent.onChildCollisionEnter(this,collider);
		}
		if (behaviour != null){
			behaviour.onCollisionEnter(collider);
		}

	}


	@Override
	public void onChildCollisionEnter(GameObject child,GameObject collider) {
		if (behaviour != null){
			behaviour.onChildCollisionEnter(collider);
		}
		
	}

	
	@Override
	public void onChildCollision(GameObject child,GameObject collider) {
		if (behaviour != null){
			behaviour.onChildCollision(child,collider);
		}
		
	}


	public void onCollisionExit(GameObject collider) {
		if (parent != null){
			parent.onChildCollisionExit(this,collider);
		}
		if (behaviour != null){
			behaviour.onCollisionExit(collider);
		}

	}
	
	@Override 
	public void onChildCollisionExit(GameObject child,GameObject collider){
		
	}

	@Override
	public boolean compareTag(String string) {
		return this.tag != null && tag.equals(string);
	}

	@Override
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
	public RenderObject getParent() {
		return parent;
	}

	public void setParent(RenderObject parent) {
		this.parent = parent;
		parent.addChild(this);
	}

	@Override
	public void addChild(RenderObject child) {
		if (childList == null){
			childList = new ArrayList<RenderObject>();	
		} 
		childList.add(child);
		
	}

}
