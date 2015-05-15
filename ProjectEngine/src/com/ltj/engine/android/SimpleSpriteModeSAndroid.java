package com.ltj.engine.android;

import com.ltj.engine.primitives.ModeSevenObject;


public class SimpleSpriteModeSAndroid extends SimpleSpriteAndroid implements ModeSevenObject {

	
	
	public SimpleSpriteModeSAndroid(int resId) {
		super(resId);
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
