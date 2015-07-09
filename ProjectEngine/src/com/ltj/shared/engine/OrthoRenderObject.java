package com.ltj.shared.engine;


import com.ltj.android.engine.AndroidHudRenderer;
import com.ltj.java.engine.JoglHudRenderer;

public class OrthoRenderObject {

	private HudRenderer renderer;
	
	

	public OrthoRenderObject(String path, boolean isJogl) {
		if (isJogl){
			renderer = new JoglHudRenderer( path);
		} else {
			this.renderer = new AndroidHudRenderer(path);
		}
	}

	public void render() {
		this.renderer.render();
		
	}

	public void translate(float dx, float dy) {
		renderer.translate(dx, dy);
	}

	public void setPosition(float x, float y) {
		renderer.setPosition(x, y);
	}

	public void setRotation(float deg) {
		renderer.setRotation(deg);
	}

	public void setScaling(float sx, float sy) {
		renderer.setScaling(sx, sy);
	}

	public void setScreenDimensions(float width, float height) {
		renderer.setScreenDimensions(width, height);
	}

	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
	}

	public void clear() {
		renderer.clear();
	}
	
	
	
	

}
