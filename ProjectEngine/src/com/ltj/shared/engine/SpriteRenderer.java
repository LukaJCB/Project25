package com.ltj.shared.engine;

public interface SpriteRenderer {
	void render();
	void translate(float dx, float dy);
	void rotate(float deg);
	void scale(float sx, float sy);
	float getX();
	float getY();
	float getHeight();
	float getWidth();
	float getRotation();
	void setModeSeven();
	void setNormalMode();
	float getZ();
	void setZ(float z);
	void setSheetDimensions(int cols, int rows);
	void setTexture(int col, int row);
	void setRepeatTexture(float xRepeat, float yRepeat);
	int getNumCols();
	int getNumRows();
	void clear();
	void setPosition(float x, float y);
	void setRotation(float deg);
	void setScale(float x, float y);
	void draw();
	void setDisabled(boolean disabled);
	boolean isDisabled();
	void animate();
	void startAnimation(String name);
	void addAnimation(String name, int animationTime, int texRow,boolean looping, int numCols);
	void stopAnimation();
	void setModeSevenEnabled(boolean modeSEnabled);
}
