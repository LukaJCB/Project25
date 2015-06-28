package com.ltj.shared.engine;

import com.jogamp.opengl.GL3;

public class HudElement extends OrthoRenderObject {

	public HudElement(GL3 gl, String path) {
		super(gl, path);
		
	}
	
	public HudElement(String path){
		super(path);
	}

	
}
