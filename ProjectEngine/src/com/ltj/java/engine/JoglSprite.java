package com.ltj.java.engine;


import com.jogamp.opengl.GL3;
import com.ltj.shared.engine.AbstractSprite;
import com.ltj.shared.engine.RenderObject;

public class JoglSprite extends AbstractSprite {

	
	private GL3 gl;


	public JoglSprite(GL3 gl, String path,int columns, int rows){
		this.path = path;
		this.gl = gl;
		renderer = new JoglSpriteRenderer(gl, path,columns,rows);
	}
	
	
	@Override
	public RenderObject cloneObject() {
		JoglSprite o = new JoglSprite(gl,path,getNumCols(),getNumRows());
		finishClone(o);
		return o;
	}

}
