package com.ltj.java.engine;

import com.jogamp.opengl.GL4;


public class SheetSpriteJogl extends SimpleSpriteJogl {


	public SheetSpriteJogl(GL4 gl,String path, int numCols, int numRows) {
		super(gl,path);
		renderer.setSheetDimensions(numCols, numRows);
	}

	public void setTexture(int column, int row){
		renderer.setTexture(column, row);
	}

}
