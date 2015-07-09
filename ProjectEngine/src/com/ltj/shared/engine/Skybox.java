package com.ltj.shared.engine;



public class Skybox {
	private BackgroundSprite[] skybox = new BackgroundSprite[6];
	public Skybox( String top, String bottom, String front, String back, String right, String left, int platform){
		addImages( top, bottom, front, back, right, left, platform);
	}
	
	
	
	public void addImages(String top, String bottom, String front, String back, String right, String left,int platform){
		skybox[0] = new BackgroundSprite(top,platform);
		skybox[1] = new BackgroundSprite(bottom,platform);
		skybox[2] = new BackgroundSprite(front,platform);
		skybox[3] = new BackgroundSprite(back,platform);
		skybox[4] = new BackgroundSprite(right,platform);
		skybox[5] = new BackgroundSprite(left,platform);
		
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
