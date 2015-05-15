package com.ltj.engine.primitives;

public interface Collider {

	float getLeft(float x,float width);
	
	float getRight(float x,float width);
	
	float getTop(float y, float height);
	
	float getBottom(float y, float height);
	
	void setScaling(float width, float height);
	
	void setOffet(float x, float y);
}
