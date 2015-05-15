package com.ltj.java.engine;

import com.jogamp.opengl.GL4;
import com.ltj.shared.engine.ModeSevenObject;


public class SimpleSpriteModeSJogl extends SimpleSpriteJogl implements ModeSevenObject {

	
	
	public SimpleSpriteModeSJogl(GL4 gl, String path) {
		super(gl,path);
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
