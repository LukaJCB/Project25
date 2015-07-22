package com.ltj.shared.engine;

import java.util.ArrayList;
import java.util.List;

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
	int getId();
	void setModeSevenEnabled(boolean modeSEnabled);
	void addChild(Sprite child);
	void setScale(float width, float height);
	void mirrorX();
	void mirrorY();
	void setMirroring(boolean x, boolean y);
	List<ParticleEmitter> getParticleEmitterList();
	void addParticleEmitter(ParticleEmitter pe);
	void animationSetLooping(String name, boolean looping);
}
