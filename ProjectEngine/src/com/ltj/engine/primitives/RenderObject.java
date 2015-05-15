package com.ltj.engine.primitives;


import java.util.ArrayList;

import com.ltj.engine.Behaviour;

public interface RenderObject {
	void start();
	void update();
	void render();
	void destroy();
	void translate(float dx,float dy);
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
	void onCollision(RenderObject renderObject);
	void setTag(String tag);
	boolean compareTag(String string);
	float getRotation();
	
}
