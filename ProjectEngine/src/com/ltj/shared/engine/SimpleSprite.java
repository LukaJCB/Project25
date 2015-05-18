package com.ltj.shared.engine;



import com.jogamp.opengl.GL4;
import com.ltj.android.engine.AndroidSpriteRenderer;
import com.ltj.java.engine.JoglSpriteRenderer;


public class SimpleSprite extends AbstractSprite {

	
	
	public SimpleSprite (GL4 gl,String path){
		renderer = new JoglSpriteRenderer(gl, path);
	}
	
	public SimpleSprite(String path){
		renderer = new AndroidSpriteRenderer(path);
	}


}
