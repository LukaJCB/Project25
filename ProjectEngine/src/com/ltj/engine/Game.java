package com.ltj.engine;

import android.util.SparseArray;

public class Game {
	private Scene startingScene;
	private SparseArray<Scene> scenes;
	
	
	public Game() {
		scenes = new SparseArray<Scene>();
	}
	


	public void addScene(int key, Scene value) {
		scenes.put(key, value);
	}



	public void setStartingScene(int sceneId) {
		startingScene = scenes.get(sceneId);
	}
	
	public void Start(){
		startingScene.start();
	}
	
}
