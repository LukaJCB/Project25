package com.ltj.shared.engine;

public interface HudRenderer {
	void render();
	void translate(float dx, float dy);
	void setPosition(float x, float y);
	void setRotation(float deg);
	void setScaling(float sx,float sy);
	void scale(float sx, float sy);
	void clear();
	void setScreenDimensions(float width, float height);
}
