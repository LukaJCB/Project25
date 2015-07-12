package com.ltj.shared.engine;

import java.util.ArrayList;

/**
 * This class acts as a Facade for the GameObjectController and Renderers.
 * @author Luka
 *
 */
@SuppressWarnings("deprecation")
public abstract class AbstractSprite implements RenderObject,SingleSprite,SpriteSheet {


	private GameObjectController controller = new GameObjectController(this);
	private ArrayList<Sprite> childList;
	private String tag = "";
	private boolean destroyed, inactive;
	protected SpriteRenderer renderer;
	protected String path;
	private boolean mirroredX, mirroredY;
	private int id;
	
	public int getNumCols() {
		return renderer.getNumCols();
	}

	public int getNumRows() {
		return renderer.getNumRows();
	}

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
			
			try {
				//get new instance of same behaviour class
				Class<?> c = Class.forName(this.getBehaviourName());
				Behaviour<Sprite> b = (Behaviour<Sprite>) c.newInstance();
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
				Behaviour<Sprite> b = (Behaviour<Sprite>) c.newInstance();
				o.addBehaviour(b);
				b.allocateObject(o);

			}  catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		Engine.addRenderable(o);
	}
	
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
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

	public void addBehaviour(Behaviour<? extends Sprite> b) {
		controller.addBehaviour(b);
	}

	public Behaviour<? extends Sprite> getBehaviour() {
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

	public void onCollisionEnter(Sprite collider) {
		controller.onCollisionEnter(collider);
	}

	public void onCollision(Sprite collider) {
		controller.onCollision(collider);
	}

	public void onCollisionExit(Sprite collider) {
		controller.onCollisionExit(collider);
	}

	
	public void onChildCollisionEnter(Sprite child, Sprite collider) {
		controller.onChildCollisionEnter(child, collider);
	}

	public void onChildCollision(Sprite child, Sprite collider) {
		controller.onChildCollision(child, collider);
	}

	public void onChildCollisionExit(Sprite child, Sprite collider) {
		controller.onChildCollisionExit(child, collider);
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
			for (Sprite r : childList){
				r.translate(dx, dy);
			}
		}
	}




	public void rotate(float deg) {
		renderer.rotate(deg);
		if (getColliders() != null){
			//adjust height and width of AABBs
			double radians = Math.toRadians(getRotation());
			float newWidth = (float) (Math.abs(getWidth() * Math.cos(radians) + getHeight() * Math.sin(radians)));
			float newHeight = (float) (Math.abs(getHeight() * Math.cos(radians) + getWidth() * Math.sin(radians)));

			for (Collider c : getColliders()){
				c.setScaling(newWidth/getWidth(), newHeight/getHeight());
			}
		}
		//rotate children as well
		if (childList != null){
			for (Sprite r : childList){
				r.rotate(deg);
			}
		}
	}


	public void setPosition(float x, float y) {
		//check pos of children
		if (childList != null){
			for (Sprite r : childList){
				float xOffset = r.getX() - getX();
				float yOffset = r.getY() - getY();
				r.setPosition(x+ xOffset, y+ yOffset);
			}
		}
		renderer.setPosition(x, y);
		
	}

	public void setRotation(float deg) {
		renderer.setRotation(deg);
		if (getColliders() != null){
			//adjust height and width of AABBs
			double radians = Math.toRadians(deg);
			float newWidth = (float) (Math.abs(getWidth() * Math.cos(radians) + getHeight() * Math.sin(radians)));
			float newHeight = (float) (Math.abs(getHeight() * Math.cos(radians) + getWidth() * Math.sin(radians)));

			for (Collider c : getColliders()){
				c.setScaling(newWidth/getWidth(), newHeight/getHeight());
			}
		}
		if (childList != null){
			for (Sprite r : childList){
				r.setRotation(deg);
			}
		}
	}

	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
		
		//scale children as well
		if (childList != null){
			for (Sprite r : childList){
				r.scale(sx, sy);
			}
		}
		
	}
	
	@Override
	public void setScale(float width, float height){
		renderer.setScale(width, height);
		//scale children as well
		if (childList != null){
			for (Sprite r : childList){
				r.setScale(width,height);
			}
		}
	}
	
	
	@Override
	public void setMirroring(boolean x, boolean y){
		if (x != mirroredX){
			mirrorX();
		}
		if (y != mirroredY){
			mirrorY();
		}
	}

	@Override
	public void mirrorX(){
		mirroredX = !mirroredX;
		setScale(-renderer.getWidth(),getHeight());
	}
	
	@Override
	public void mirrorY(){
		mirroredY = !mirroredY;
		setScale(getWidth(),-renderer.getHeight());
	}


	public float getX() {
		return renderer.getX();
	}


	public float getY() {
		return renderer.getY();
	}


	public float getHeight() {
		if (mirroredY){
			return -renderer.getHeight();
		}
		return renderer.getHeight();
	}


	public float getWidth() {
		if (mirroredX){
			return -renderer.getWidth();
		}
		return renderer.getWidth();
	}


	public float getRotation() {
		return renderer.getRotation();
	}


	@Override
	public void setModeSevenEnabled(boolean modeSEnabled) {
		renderer.setModeSevenEnabled(modeSEnabled);
	}

	@Override
	public void setModeSeven() {
		renderer.setModeSeven();
	}

	@Override
	public boolean isLoaded() {
		//TODO
		return false;
	}

	@Override
	public void setNormalMode() {
		renderer.setNormalMode();
	}

	@Override
	public float getZ() {
		return renderer.getZ();
	}

	@Override
	public void setZ(float z) {
		renderer.setZ(z);
	}

	public void animate() {
		renderer.animate();
	}

	
	@Override
	public void startAnimation(String name) {
		renderer.startAnimation(name);
	}
	@Override
	public void addAnimation(String name, int animationTime, int texRow,
			boolean looping, int numCols) {
		renderer.addAnimation(name, animationTime, texRow, looping, numCols);
	}
	@Override
	public void stopAnimation() {
		renderer.stopAnimation();
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
				for (Sprite g : childList){
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

	@Override
	public void setParent(RenderObject parent) {
		controller.setParent(parent);
	}

	@Override
	public ArrayList<Sprite> getChildList() {
		return childList;
	}

	@Override
	public void addChild(Sprite child) {
		if (childList == null){
			childList = new ArrayList<Sprite>();	
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
			for (Sprite o : childList){
				o.setInactive(inactive);
			}
		}
	}

	@Override
	public void setTexture(int column, int row) {
		renderer.setTexture(column, row);
	}

	@Override
	public void setRepeat(float horizontal, float vertical) {
		renderer.setRepeatTexture(horizontal, vertical);
	}

	public String toJSON() {
		String s = "\"id\":" + id + "," + renderer.toJSON() + "\"tag\":\"" + tag  + "\",\"inactive\":" + inactive 
				+ ",\"mirroredX\":" + mirroredX + ",\"mirroredY\":" + mirroredY + controller.toJSON() + "\"children\":";
		if (childList != null){
			s+= "[";
			for (Sprite sprite : childList){
				s+= ((RenderObject) sprite).getId() + ",";
			}
			s = s.substring(0,s.length()-1);
			s+= "]";
		} else {
			s+= "\"null\"";
		}
		return s;
	}


	

}
