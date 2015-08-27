package com.ltj.android.engine;

import com.ltj.shared.engine.AbstractSprite;
import com.ltj.shared.engine.RenderObject;

public class AndroidSprite extends AbstractSprite {

	protected String path;


	public AndroidSprite(String path,int columns, int rows){
		super(path);
		this.path = path;
		renderer = new AndroidSpriteRenderer(path,columns, rows);
	}
	
	public AndroidSprite(String path, int columns, int rows,boolean dynamic){
		super(path);
		setLoaded(false);
		renderer  = new AndroidSpriteRenderer(path,columns,rows,this);
		
	}
	
	
	@Override
	public RenderObject cloneObject() {
		AndroidSprite o = new AndroidSprite(path,getNumCols(),getNumRows());
		finishClone(o);
		return o;
	}


	@Override
	public void finishLoading() {
		// TODO Auto-generated method stub
		
	}

	
}
