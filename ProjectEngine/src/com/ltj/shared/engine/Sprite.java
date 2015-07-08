package com.ltj.shared.engine;

import java.util.ArrayList;

public interface Sprite {
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
	boolean compareTag(String string);
	float getRotation();
	Sprite getParent();
	void setRendererDisabled(boolean disabled);
	void setControllerDisabled(boolean disabled);
	void setInactive(boolean inactive);
	ArrayList<Sprite> getChildList();
	Collider getCollider(int index);
	void setZ(float z);
	float getZ();
	void setModeSevenEnabled(boolean modeSEnabled);
	void addChild(Sprite child);
}