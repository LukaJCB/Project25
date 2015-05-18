package com.ltj.shared.engine;

import com.jogamp.opengl.GL4;


public class SimpleSpriteModeS extends SimpleSprite implements ModeSevenObject {

	
	
	public SimpleSpriteModeS(GL4 gl, String path) {
		super(gl,path);
	}

	public SimpleSpriteModeS(String path){
		super(path);
	}
	
	public void setZ(float z) {
		renderer.setZ(z);
	}

	public void setModeSeven() {
		renderer.setModeSeven();
	}
	
	public void setNormalMode(){
		renderer.setNormalMode();
	}

	public float getZ() {
		return renderer.getZ();
	}

}
