package com.ltj.shared.engine;

import com.jogamp.opengl.GL4;

public class Skybox {
	private BackgroundSprite[] skybox = new BackgroundSprite[6];
	public Skybox(GL4 gl, String top, String bottom, String front, String back, String right, String left){
		addImages(gl, top, bottom, front, back, right, left);
	}
	public Skybox(String top, String bottom, String front, String back, String right, String left){
		addImages(top, bottom, front, back, right, left);
	}
	public void addImages(GL4 gl, String top, String bottom, String front, String back, String right, String left){
		skybox[0] = new BackgroundSprite(gl, top);
		skybox[1] = new BackgroundSprite(gl, bottom);
		skybox[2] = new BackgroundSprite(gl, front);
		skybox[3] = new BackgroundSprite(gl, back);
		skybox[4] = new BackgroundSprite(gl, right);
		skybox[5] = new BackgroundSprite(gl, left);
		
		setupSkybox();
	}
	public void addImages(String top, String bottom, String front, String back, String right, String left){
		skybox[0] = new BackgroundSprite(top);
		skybox[1] = new BackgroundSprite(bottom);
		skybox[2] = new BackgroundSprite(front);
		skybox[3] = new BackgroundSprite(back);
		skybox[4] = new BackgroundSprite(right);
		skybox[5] = new BackgroundSprite(left);
		
		setupSkybox();
	}
	
	public void render(float x, float y){
		for (BackgroundSprite bs : skybox){
			bs.setPosition(x, y);
		}
		setupSkybox();
		for (BackgroundSprite bs : skybox){
			bs.render();
		}
	}
	private void setupSkybox() {
		skybox[0].setZ(1);
		
		skybox[2].setModeSeven();
		skybox[2].translate(0,0.5f);
		
		skybox[3].setModeSeven();
		skybox[3].translate(0,-0.5f);
		
		skybox[4].setModeSeven();
		skybox[4].setRotation(90);
		skybox[4].translate(0.5f,0);
		
		skybox[5].setModeSeven();
		skybox[5].setRotation(270);
		skybox[5].translate(-0.5f,0);
	}
	public void clear() {
		for (BackgroundSprite bs : skybox){
			bs.clear();
		}
		
	}
	
}
