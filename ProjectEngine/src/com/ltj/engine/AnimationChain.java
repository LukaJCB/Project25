package com.ltj.engine;

import java.util.ArrayList;

public class AnimationChain {
	private ArrayList<Animation> animations;
	private int index;
	private boolean finished;
	
	public AnimationChain(Animation anim){
		animations = new ArrayList<Animation>();
		animations.add(anim);
	}
	
	public void addAnimation(Animation anim) {
		animations.add(anim);
	}
	
	public void start(){
		animations.get(0).onStart();
	}
	
	public void play(){
		//play current animation
		animations.get(index).animate();
		
		//check for next animation
		if(animations.get(index).framesLeft() == 0){
			index++;
			if (index == animations.size()){
				//chain is finished
				finished =true;
			} else {
				//start next animation
				animations.get(index).onStart();
			}
		}
	}

	public boolean isFinished() {
		return finished;
	}
	public void restart(){
		finished = false;
		index = 0;
		start();
	}

}
