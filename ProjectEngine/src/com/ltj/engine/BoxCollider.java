package com.ltj.engine;

import com.ltj.engine.primitives.Collider;

public class BoxCollider implements Collider {

	private float xScaling = 1, yScaling = 1;
	private float xOffset, yOffset;
	
	@Override
	public void setScaling(float x, float y){
		xScaling = x;
		yScaling = y;
	}
	
	@Override
	public void setOffet(float x, float y) {
		xOffset = x;
		yOffset = y;
	}

	@Override
	public float getLeft(float x, float width) {
		return x+xOffset - xScaling*width/2;
	}

	@Override
	public float getRight(float x, float width) {
		return x+xOffset + xScaling*width/2;
	}

	@Override
	public float getTop(float y, float height) {
		return y+yOffset  + yScaling*height/2;
	}

	@Override
	public float getBottom(float y, float height) {
		return  y+yOffset - yScaling*height/2;
	}


}
