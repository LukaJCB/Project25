package com.ltj.java.engine;



import com.jogamp.opengl.GL4;
import com.ltj.shared.engine.AbstractSprite;


public class SimpleSpriteJogl extends AbstractSprite {

	
	
	public SimpleSpriteJogl (GL4 gl,String path){
		renderer = new JoglSpriteRenderer(gl, path);
	}
	


}
