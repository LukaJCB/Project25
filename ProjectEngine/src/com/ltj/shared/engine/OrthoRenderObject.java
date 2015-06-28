package com.ltj.shared.engine;

import com.jogamp.opengl.GL3;
import com.ltj.android.engine.AndroidHudRenderer;
import com.ltj.java.engine.JoglHudRenderer;

public class OrthoRenderObject {

	private HudRenderer renderer;
	
	public OrthoRenderObject(GL3 gl, String path){
		this.renderer = new JoglHudRenderer(gl,path);
	}

	public OrthoRenderObject(String path) {
		this.renderer = new AndroidHudRenderer(path);
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
