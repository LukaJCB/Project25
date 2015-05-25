package com.ltj.shared.engine;

import com.jogamp.opengl.GL3;


public class SheetSpriteModeS extends SheetSprite implements ModeSevenObject {



	public SheetSpriteModeS(GL3 gl, String path, int numCols,int numRows) {
		super(gl,path, numCols, numRows);
	}
	public SheetSpriteModeS( String path, int numCols,int numRows) {
		super(path, numCols, numRows);
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
