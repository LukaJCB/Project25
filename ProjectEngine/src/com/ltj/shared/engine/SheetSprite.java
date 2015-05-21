package com.ltj.shared.engine;

import com.jogamp.opengl.GL4;


public class SheetSprite extends SimpleSprite {


	public SheetSprite(GL4 gl,String path, int numCols, int numRows) {
		super(gl,path);
		renderer.setSheetDimensions(numCols, numRows);
	}
	public SheetSprite(String path, int numCols, int numRows){
		super(path);
		renderer.setSheetDimensions(numCols, numRows);
	}

	public void setTexture(int column, int row){
		renderer.setTexture(column, row);
	}

	@Override
	public GameObject cloneObject() {
		SheetSprite o;
		if (gl != null){
			o = new SheetSprite(gl,path,renderer.getNumCols(), renderer.getNumRows());
		} else {
			o = new SheetSprite(path,renderer.getNumCols(), renderer.getNumRows());
		}
		prepareClone(o);
		return o;
	}

}
