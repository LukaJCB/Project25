package com.ltj.shared.engine;

import com.jogamp.opengl.GL3;
import com.ltj.java.engine.JoglHudRenderer;

public class HudElement {

	
	private HudRenderer renderer;
	
	public HudElement(GL3 gl, String path){
		this.renderer = new JoglHudRenderer(gl,path);
	}

	public void render(float width, float height) {
		this.renderer.render(width,height);
		
	}

	public void translate(float dx, float dy) {
		renderer.translate(dx, dy);
	}

	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
	}

	public void clear() {
		renderer.clear();
	}

}
