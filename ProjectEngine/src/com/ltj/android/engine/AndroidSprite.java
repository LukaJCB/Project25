package com.ltj.android.engine;

import com.ltj.shared.engine.AbstractSprite;
import com.ltj.shared.engine.RenderObject;

public class AndroidSprite extends AbstractSprite {

	protected String path;


	public AndroidSprite(String path){
		this.path = path;
		renderer = new AndroidSpriteRenderer(path);
	}
	
	
	@Override
	public RenderObject cloneObject() {
		AndroidSprite o = new AndroidSprite(path);
		finishClone(o);
		return o;
	}

}
