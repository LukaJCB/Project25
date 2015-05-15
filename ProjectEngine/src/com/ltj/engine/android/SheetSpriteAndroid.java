package com.ltj.engine.android;



public class SheetSpriteAndroid extends SimpleSpriteAndroid {

	

	public SheetSpriteAndroid(int resId, int numCols, int numRows) {
		super(resId);
		renderer.setSheetDimensions(numCols, numRows);
	}

	public void setTexture(int column, int row){
		renderer.setTexture(column, row);
	}


}
