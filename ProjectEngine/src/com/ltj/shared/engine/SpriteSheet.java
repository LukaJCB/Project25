package com.ltj.shared.engine;


public interface SpriteSheet extends Sprite {
	
	void setTexture(int column, int row);
	void stopAnimation();
	void startAnimation(String name);
	void addAnimation(String name, int animationTime, int texRow,
			boolean looping, int numCols);
}
