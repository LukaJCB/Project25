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
}
