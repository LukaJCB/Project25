package com.ltj.android.engine;

import com.ltj.shared.engine.AbstractSprite;
import com.ltj.shared.engine.RenderObject;

public class AndroidSprite extends AbstractSprite {

	protected String path;


	public AndroidSprite(String path,int columns, int rows){
		this.path = path;
		renderer = new AndroidSpriteRenderer(path,columns, rows);
	}
	
	
	@Override
	public RenderObject cloneObject() {
		AndroidSprite o = new AndroidSprite(path,getNumCols(),getNumRows());
		finishClone(o);
		return o;
	}

	@Override
	public String toJSON(){
		return "{ \"type\":\"" + getClass().getName() + "\", "+ super.toJSON() + "}";
	}
}
