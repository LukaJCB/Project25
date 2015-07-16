package com.ltj.java.engine;

import com.ltj.shared.engine.AbstractSprite;
import com.ltj.shared.engine.RenderObject;

public class JoglSprite extends AbstractSprite {

	

	public JoglSprite( String path,int columns, int rows){
		super(path);
		renderer = new JoglSpriteRenderer( path,columns,rows);
	}
	
	public JoglSprite(String path, int columns, int rows,boolean dynamic){
		super(path);
		setLoaded(false);
		renderer  = new JoglSpriteRenderer(path,columns,rows,this);
		
	}
	
	
	@Override
	public RenderObject cloneObject() {
		JoglSprite o = new JoglSprite(path,getNumCols(),getNumRows());
		finishClone(o);
		return o;
	}




	@Override
	public void finishLoading() {
		renderer.finishLoading(path);
		
	}

	
	
}
