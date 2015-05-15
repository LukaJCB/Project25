package com.ltj.shared.engine;


import java.util.ArrayList;


public interface RenderObject {
	void start();
	void update();
	void render();
	void destroy();
	void translate(float dx,float dy);
	void translate(float dx, float dy, float width, float height);
	void scale(float sx,float sy);
	void rotate(float deg);
	float getX();
	float getY();
	float getHeight();
	float getWidth();
	boolean collidesWith(RenderObject collider);
	boolean isDestroyed();
	void addBehaviour(Behaviour<? extends RenderObject> b);
	void addCollider(Collider c);
	Collider getCollider(int index);
	ArrayList<Collider> getColliders();
	void onCollision(RenderObject collider);
	void onChildCollision(RenderObject child, RenderObject collider);
	void setTag(String tag);
	boolean compareTag(String string);
	float getRotation();
	void addChild(RenderObject child);
	RenderObject getParent();
	
}
