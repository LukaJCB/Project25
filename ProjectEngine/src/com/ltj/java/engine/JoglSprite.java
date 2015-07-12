package com.ltj.java.engine;


import com.ltj.shared.engine.AbstractSprite;
import com.ltj.shared.engine.RenderObject;

public class JoglSprite extends AbstractSprite {

	

	public JoglSprite( String path,int columns, int rows){
		this.path = path;
		renderer = new JoglSpriteRenderer( path,columns,rows);
	}
	
	
	@Override
	public RenderObject cloneObject() {
		JoglSprite o = new JoglSprite(path,getNumCols(),getNumRows());
		finishClone(o);
		return o;
	}

	public String toJSON(){
		return "{ \"type\":\"" + getClass().getName() + "\", "+ super.toJSON() + "}";
	}
	
	
}
