package com.ltj.java.engine;



import java.util.ArrayList;

import com.jogamp.opengl.GL4;
import com.ltj.shared.engine.Behaviour;
import com.ltj.shared.engine.Collider;
import com.ltj.shared.engine.RenderObject;


public class SimpleSpriteJogl implements RenderObject {

	
	private Behaviour<? extends RenderObject> behaviour;
	private String behaviourName;
	private boolean destroyed;
	private ArrayList<Collider> colliders;
	private String tag;
	protected JoglSpriteRenderer renderer;
	public SimpleSpriteJogl (GL4 gl,String path){
		renderer = new JoglSpriteRenderer(gl, path);
	}
	

	public void translate(float dx, float dy) {
		renderer.translate(dx, dy);
	}





	public void rotate(float deg) {
		renderer.rotate(deg);
	}





	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
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

	
	public void update() {
		if (behaviour != null){
			behaviour.update();
		}
	}

	public boolean collidesWith(RenderObject object) {
		if (this.colliders != null && object.getColliders() != null){
			for (Collider collider : colliders){
				for (Collider objCollider : object.getColliders()){
					if (collider.getBottom(getY(),getHeight()) < objCollider.getTop(object.getY(),object.getHeight()) &&
							collider.getTop(getY(),getHeight()) > objCollider.getBottom( object.getY(),object.getHeight()) &&
							collider.getRight(getX(),getWidth()) > objCollider.getLeft(object.getX(), object.getWidth()) &&
							collider.getLeft(getX(),getWidth()) < objCollider.getRight(object.getX(), object.getWidth())){
						return true;
					}
				}
			}
		}
		return false;
	}

	public void addBehaviour(Behaviour<? extends RenderObject> b){
		behaviour = b;
	}
	
	
	public void addCollider(Collider c){
		if (colliders == null){
			colliders = new ArrayList<Collider>();
		}
		colliders.add(c);
	}

	
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

	
	public Collider getCollider(int index) {
		return colliders.get(index);
	}
	
	
	public ArrayList<Collider> getColliders(){
		return colliders;
	}
	
	public void addBehaviourName(String name){
		behaviourName = name;
	}
	
	public String getBehaviourName() {
		return behaviourName;
	}

	
	public void onCollision(RenderObject collider) {
		if (behaviour != null){
			behaviour.onCollision(collider);
		}
	}

	
	public boolean compareTag(String string) {
		return this.tag != null && tag.equals(string);
	}

	
	public void setTag(String tag) {
		this.tag = tag;
	}


}
