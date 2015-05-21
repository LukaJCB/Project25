package com.ltj.shared.engine;



import com.jogamp.opengl.GL4;
import com.ltj.android.engine.AndroidSpriteRenderer;
import com.ltj.java.engine.JoglSpriteRenderer;


public class SimpleSprite extends AbstractSprite {

	
	
	protected GL4 gl;
	protected String path;

	public SimpleSprite (GL4 gl,String path){
		this.gl = gl;
		this.path = path;
		renderer = new JoglSpriteRenderer(gl, path);
	}
	
	public SimpleSprite(String path){
		this.path = path;
		renderer = new AndroidSpriteRenderer(path);
	}

	@Override
	public GameObject cloneObject() {
		SimpleSprite o;
		if (gl != null){
			o = new SimpleSprite(gl,path);
		} else {
			o = new SimpleSprite(path);
		}
		prepareClone(o);
		return o;
	}


}
