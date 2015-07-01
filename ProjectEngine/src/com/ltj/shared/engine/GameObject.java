package com.ltj.shared.engine;

public interface GameObject {
	void destroy();
	void translate(float dx,float dy);
	void scale(float sx,float sy);
	void rotate(float deg);
	void setRotation(float deg);
	void setPosition(float dx, float dy);
	float getX();
	float getY();
	float getHeight();
	float getWidth();
	void setTag(String tag);
	String getTag();
	boolean compareTag(String string);
	float getRotation();
	void checkCollision(RenderObject collider);
	boolean isDestroyed();
	GameObject getParent();
	Behaviour<? extends GameObject> getBehaviour();
	RenderObject cloneObject();
	void setRendererDisabled(boolean disabled);
	boolean isRendererDisabled();
	void setControllerDisabled(boolean disabled);
	boolean isControllerDisabled();
	boolean isInactive();
	void setInactive(boolean inactive);
}
