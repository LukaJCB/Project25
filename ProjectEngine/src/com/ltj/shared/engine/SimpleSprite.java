package com.ltj.shared.engine;



import com.jogamp.opengl.GL3;
import com.ltj.android.engine.AndroidSpriteRenderer;
import com.ltj.java.engine.JoglSpriteRenderer;


public class SimpleSprite extends AbstractSprite {

	
	
	protected GL3 gl;
	protected String path;

	public SimpleSprite (GL3 gl,String path){
		this.gl = gl;
		this.path = path;
		renderer = new JoglSpriteRenderer(gl, path);
	}
	
	public SimpleSprite(String path){
		this.path = path;
		renderer = new AndroidSpriteRenderer(path);
	}
	
	public void setRepeat(float horizontal, float vertical){
		renderer.setRepeatTexture(horizontal, vertical);
	}

	@Override
	public RenderObject cloneObject() {
		SimpleSprite o;
		if (gl != null){
			o = new SimpleSprite(gl,path);
		} else {
			o = new SimpleSprite(path);
		}
		finishClone(o);
		return o;
	}


}
