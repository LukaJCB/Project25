package com.ltj.shared.engine;

public interface ParticleEmitter {
	void render();
	void recalculateVBOs();
	String toJSON();
}
