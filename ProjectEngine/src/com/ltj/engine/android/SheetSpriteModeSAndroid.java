package com.ltj.engine.android;

import com.ltj.engine.primitives.ModeSevenObject;

public class SheetSpriteModeSAndroid extends SheetSpriteAndroid implements ModeSevenObject {

	public SheetSpriteModeSAndroid(int resId, int numCols, int numRows) {
		super(resId, numCols, numRows);
	}

	public void setTexture(int column, int row) {
		renderer.setTexture(column, row);
	}

	public void setZ(float z) {
		renderer.setZ(z);
	}

	public void setModeSeven() {
		renderer.setModeSeven();
	}

	public void setNormalMode() {
		renderer.setNormalMode();
	}

	public float getZ() {
		return renderer.getZ();
	}

}
