package com.ltj.shared.engine;

public interface ParticleEmitter {
	void render();
	void recalculateVBOs();
	String toJSON();
	void setPosition(float x, float y, float z);
}
