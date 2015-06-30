package com.ltj.shared.engine;

import java.util.ArrayList;

public abstract class AbstractSprite implements RenderObject {


	private GameObjectController controller = new GameObjectController(this);
	private ArrayList<GameObject> childList;
	private String tag;
	private boolean destroyed;
	protected SpriteRenderer renderer;
	
	@SuppressWarnings("unchecked")
	protected void prepareClone(AbstractSprite o){
		o.setTag(this.getTag());
		if (this.getColliders() != null){
			for (Collider c :this.getColliders()){
				o.addCollider(c);
			}
		}
		if(this.getBehaviourName()!= null){
			o.addBehaviourName(this.getBehaviourName());
			try {
				Class<?> c = Class.forName(o.getBehaviourName());
				o.addBehaviour((Behaviour<? extends GameObject>) c.newInstance());

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		o.start();
	}
	
	public void update() {
		controller.update();
	}

	public void start() {
		controller.start();
	}

	public void checkCollision(RenderObject object) {
		controller.checkCollision(object);
	}

	public void addBehaviour(Behaviour<? extends GameObject> b) {
		controller.addBehaviour(b);
	}

	public Behaviour<? extends GameObject> getBehaviour() {
		return controller.getBehaviour();
	}

	public void addCollider(Collider c) {
		controller.addCollider(c);
	}

	public Collider getCollider(int index) {
		return controller.getCollider(index);
	}

	public ArrayList<Collider> getColliders() {
		return controller.getColliders();
	}

	public void onCollisionEnter(GameObject collider) {
		controller.onCollisionEnter(collider);
	}

	public void onCollision(GameObject collider) {
		controller.onCollision(collider);
	}

	public void onCollisionExit(GameObject collider) {
		controller.onCollisionExit(collider);
	}

	public void onChildCollisionEnter(GameObject child, GameObject collider) {
		controller.onChildCollisionEnter(child, collider);
	}

	public void onChildCollision(GameObject child, GameObject collider) {
		controller.onChildCollision(child, collider);
	}

	public void onChildCollisionExit(GameObject child, GameObject collider) {
		controller.onChildCollisionExit(child, collider);
	}

	public void addBehaviourName(String name) {
		controller.addBehaviourName(name);
	}

	public String getBehaviourName() {
		return controller.getBehaviourName();
	}

	public boolean equals(Object obj) {
		return controller.equals(obj);
	}

	public void translate(float dx, float dy) {
		renderer.translate(dx, dy);
		if (childList != null){
			for (GameObject r : childList){
				r.translate(dx, dy);
			}
		}
	}




	public void rotate(float deg) {
		renderer.rotate(deg);
		if (childList != null){
			for (GameObject r : childList){
				r.rotate(deg);
			}
		}
	}


	public void setPosition(float x, float y) {
		renderer.setPosition(x, y);
	}

	public void setRotation(float deg) {
		renderer.setRotation(deg);
	}

	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
		if (childList != null){
			for (GameObject r : childList){
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



	


	@Override
	public void render() {
		renderer.render();
	}

	@Override
	public boolean isRendererDisabled() {
		return renderer.isDisabled();
	}

	@Override
	public void setRendererDisabled(boolean rendererDisabled) {
		renderer.setDisabled(rendererDisabled);
	}

	@Override
	public boolean isControllerDisabled() {
		return controller.isDisabled();
	}
	
	@Override
	public void destroy() {
		destroyed = true;
		clear();
	}

	
	@Override
	public boolean isDestroyed() {
		return destroyed;
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
	public String getTag() {
		return tag;
	}




	@Override
	public void setControllerDisabled(boolean controllerDisabled) {
		controller.setDisabled(controllerDisabled);
	}

	@Override
	public void clear() {
		controller.clear();
		renderer.clear();
	}

	@Override
	public RenderObject getParent() {
		return controller.getParent();
	}

	public void setParent(RenderObject parent) {
		controller.setParent(parent);
	}

	@Override
	public void addChild(GameObject child) {
		if (childList == null){
			childList = new ArrayList<GameObject>();	
		} 
		childList.add(child);
		
	}

}
