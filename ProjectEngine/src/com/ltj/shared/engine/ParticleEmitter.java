package com.ltj.shared.engine;

public interface ParticleEmitter {
	void render();
	void recalculateVBOs();
	String toJSON();
	void setPosition(float x, float y, float z);
	void translate(float x, float y, float z);
	float getX();
	float getY();
	float getZ();
	void addParticleExplosion(int count, float speed);
	int getId();
	void setId(int id);
}
