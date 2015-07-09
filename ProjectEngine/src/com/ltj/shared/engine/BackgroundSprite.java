package com.ltj.shared.engine;

import com.ltj.android.engine.AndroidSpriteRenderer;
import com.ltj.java.engine.JoglSpriteRenderer;

public class BackgroundSprite {
	private SpriteRenderer renderer;
	
	public BackgroundSprite( String path, boolean isJogl){
		renderer = new JoglSpriteRenderer( path,1,1);
	}
	
	public BackgroundSprite(String path){
		renderer = new AndroidSpriteRenderer(path,1,1);
	}

	public void render() {
		renderer.render();
	}

	public void setPosition(float x, float y){
		renderer.setPosition(x,y);
	}
	
	public void translate(float dx, float dy) {
		renderer.translate(dx, dy);
	}

	public void rotate(float deg) {
		renderer.rotate(deg);
	}
	public void setRotation(float deg){
		renderer.setRotation(deg);
	}

	public void scale(float sx, float sy) {
		renderer.scale(sx, sy);
	}

	public void setModeSeven() {
		renderer.setModeSeven();
	}

	public void setZ(float z) {
		renderer.setZ(z);
	}
	
	public float[] getPosition(){
		float[] f = new float[3];
		f[0] = renderer.getX();
		f[1] = renderer.getY();
		f[2] = renderer.getZ();
		return f;
	}

	public void clear() {
		renderer.clear();
	}

	
	
}
