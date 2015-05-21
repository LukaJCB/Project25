package com.ltj.shared.engine;

public interface GameObject {
	void destroy();
	void translate(float dx,float dy);
	void scale(float sx,float sy);
	void rotate(float deg);
	float getX();
	float getY();
	float getHeight();
	float getWidth();
	void setTag(String tag);
	String getTag();
	boolean compareTag(String string);
	float getRotation();
	boolean checkCollision(RenderObject collider);
	boolean isDestroyed();
	GameObject getParent();
	Behaviour<? extends GameObject> getBehaviour();
	GameObject cloneObject();
}
