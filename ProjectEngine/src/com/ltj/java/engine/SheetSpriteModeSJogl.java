package com.ltj.java.engine;

import com.jogamp.opengl.GL4;
import com.ltj.shared.engine.ModeSevenObject;


public class SheetSpriteModeSJogl extends SheetSpriteJogl implements ModeSevenObject {



	public SheetSpriteModeSJogl(GL4 gl, String path, int numCols,int numRows) {
		super(gl,path, numCols, numRows);
	}

	
	public void setModeSeven() {
		renderer.setModeSeven();
	}
	
	public void setNormalMode(){
		renderer.setNormalMode();
	}

	public void setZ(float z) {
		renderer.setZ(z);
	}

	public float getZ() {
		return renderer.getZ();
	}

	

}
