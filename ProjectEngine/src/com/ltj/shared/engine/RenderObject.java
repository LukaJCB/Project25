package com.ltj.shared.engine;


import java.util.ArrayList;


public interface RenderObject extends Sprite {
	void start();
	void update();
	void render();
	void addBehaviour(Behaviour<? extends Sprite> b);
	void addCollider(Collider c);
	ArrayList<Collider> getColliders();
	void onCollisionEnter(Sprite collider);
	void onCollision(Sprite collider);
	void onCollisionExit(Sprite collider);
	void onChildCollisionEnter(Sprite child, Sprite collider);
	void onChildCollision(Sprite child, Sprite collider);
	void onChildCollisionExit(Sprite child, Sprite collider);
	void clear();
	void animate();
	void setNormalMode();
	void setModeSeven();
	void setTag(String tag);
	String getTag();
	void checkCollision(RenderObject collider);
	boolean isDestroyed();
	Behaviour<? extends Sprite> getBehaviour();
	RenderObject cloneObject();
	boolean isRendererDisabled();
	boolean isControllerDisabled();
	boolean isInactive();
	boolean isLoaded();
	String toJSON();
	void setId(int id);
	void addAnimation(String name, int animationTime, int texRow,
			boolean looping, int numCols);
	void setParent(RenderObject parent);
	void setRepeat(float horizontal, float vertical);
	void setTexture(int col, int row);
	void finishLoading();
	void setLoaded(boolean b);
	
	
	
}
