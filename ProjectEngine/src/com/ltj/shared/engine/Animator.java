package com.ltj.shared.engine;

import java.util.HashMap;
import java.util.Map.Entry;


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
	
	public String toJSON(){
		String animJSON = "[";
		for (Entry<String, Animation> a : animations.entrySet()){
			animJSON += "{\"name\":\""+ a.getKey()+ "\"," + a.getValue().toJSON() + "},";
		}
		animJSON = animJSON.substring(0,animJSON.length()-1);
		animJSON += "],";
		return animJSON;
		
	}

	public void setLooping(String name, boolean looping) {
		animations.get(name).setLooping(looping);
	}
}
