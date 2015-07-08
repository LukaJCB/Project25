package com.ltj.shared.engine;

import java.util.HashMap;

public class Animator {
	private HashMap<String,	Animation> animations = new HashMap<String, Animation>();
	private Animation currentAnimation;
	
	public void addAnimation(String name, Animation animation){
		animations.put(name, animation);
	}
	
	public void startAnimation(String name){
		if (currentAnimation != animations.get(name)){
			currentAnimation = animations.get(name);
			currentAnimation.start();
		}
	}
	
	public void stopAnimation(){
		currentAnimation = null;
	}
	
	public void play(SpriteRenderer renderer){
		if (currentAnimation != null){
			if (currentAnimation.isPlaying()){
				currentAnimation.play(renderer);
			} else {
				currentAnimation = null;
			}
		}
	}
}
