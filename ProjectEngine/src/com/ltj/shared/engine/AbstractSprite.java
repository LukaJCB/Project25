package com.ltj.shared.engine;

import java.util.ArrayList;

/**
 * This class acts as a Facade for the GameObjectController and Renderers.
 * @author Luka
 *
 */
@SuppressWarnings("deprecation")
public abstract class AbstractSprite implements RenderObject {


	private GameObjectController controller = new GameObjectController(this);
	private ArrayList<GameObject> childList;
	private String tag;
	private boolean destroyed, inactive;
	protected SpriteRenderer renderer;
	
	@SuppressWarnings("unchecked")
	protected void finishClone(AbstractSprite o){
		if (this.getColliders() != null){
			for (Collider c :this.getColliders()){
				o.addCollider(c);
			}
		}
		
		o.setTag(this.getTag());
		o.setPosition(getX(), getY());
		o.setRotation(getRotation());
		o.scale(getWidth(), getHeight());
		
		if(this.getBehaviourName()!= null){
			o.addBehaviourName(this.getBehaviourName());
			try {
				//get new instance of same behaviour class
				Class<?> c = Class.forName(o.getBehaviourName());
				Behaviour<GameObject> b = (Behaviour<GameObject>) c.newInstance();
				o.addBehaviour(b);
				b.allocateObject(o);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if (this.getBehaviour() != null) {
			try {
				//get new instance of same behaviour class
				Class<?> c = this.getBehaviour().getClass();
				Behaviour<GameObject> b = (Behaviour<GameObject>) c.newInstance();
				o.addBehaviour(b);
				b.allocateObject(o);

			}  catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		Updater.addRenderable(o);
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
		//translate children as well
		if (childList != null){
			for (GameObject r : childList){
				r.translate(dx, dy);
			}
		}
	}




	public void rotate(float deg) {
		renderer.rotate(deg);
		if (getColliders() != null){
			//adjust height and width of AABBs
			double radians = Math.toRadians(deg);
			float newWidth = (float) (Math.abs(getWidth() * Math.cos(radians) + getHeight() * Math.sin(radians)));
			float newHeight = (float) (Math.abs(getHeight() * Math.cos(radians) + getWidth() * Math.sin(radians)));

			for (Collider c : getColliders()){
				c.setScaling(newWidth/getWidth(), newHeight/getHeight());
			}
		}
		//rotate children as well
		if (childList != null){
			for (GameObject r : childList){
				r.rotate(deg);
			}
		}
	}


	public void setPosition(float x, float y) {
		//check pos of children
		if (childList != null){
			for (GameObject r : childList){
				float xOffset = r.getX() - getX();
				float yOffset = r.getY() - getY();
				r.setPosition(x+ xOffset, y+ yOffset);
			}
		}
		renderer.setPosition(x, y);
		
	}

	public void setRotation(float deg) {
		renderer.setRotation(deg);
		if (childList != null){
			for (GameObject r : childList){
				r.setRotation(deg);
			}
		}
	}

	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
		//scale children as well
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



	


	public void animate() {
		renderer.animate();
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
		if (!destroyed){
			destroyed = true;
			if (childList != null){
				for (GameObject g : childList){
					g.destroy();
				}
			}
		}
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
	public ArrayList<GameObject> getChildList() {
		return childList;
	}

	@Override
	public void addChild(GameObject child) {
		if (childList == null){
			childList = new ArrayList<GameObject>();	
		} 
		childList.add(child);
		
	}

	@Override
	public boolean isInactive() {
		return inactive;
	}
	
	@Override
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
		renderer.setDisabled(inactive);
		controller.setDisabled(inactive);
		if (childList != null){
			for (GameObject o : childList){
				o.setInactive(inactive);
			}
		}
	}

}
