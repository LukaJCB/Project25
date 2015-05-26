package com.ltj.shared.engine;

public interface HudRenderer {
	void render(float width, float height);
	void translate(float dx, float dy);
	void scale(float sx, float sy);
	void clear();
}
