package com.ltj.shared.engine;

public class Animation {

	private int counter;
	private int animationTime;
	private int texCol, texRow;
	private boolean playing,looping;
	private int numCols;
	
	

	public boolean isPlaying() {
		return playing;
	}

	public void setAnimationTime(int animationTime) {
		this.animationTime = animationTime;
	}

	public void setLooping(boolean looping) {
		this.looping = looping;
	}

	

	public void play(SpriteRenderer renderer){
		counter++;
		if (counter > animationTime){
			counter = 0;
			texCol++;
			
			if(texCol == numCols){
				texCol = 0;
				if (!looping){ 
					playing = false;
				}
			}
			renderer.setTexture(texCol, texRow);
		}
	}

	public Animation(int animationTime, int texRow, boolean looping, int numCols) {
		this.animationTime = animationTime;
		this.texRow = texRow;
		this.looping = looping;
		this.numCols = numCols;
	}

	public void reset() {
		counter = 0;
		texCol = 0;
	}

	public int getTexRow() {
		return texRow;
	}

	public void setTexRow(int texRow) {
		this.texRow = texRow;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	public int getAnimationTime() {
		return animationTime;
	}

	public boolean isLooping() {
		return looping;
	}

	public void start() {
		playing = true;
		reset();
	}

	public String toJSON() {
		return "\"animationTime\":" + animationTime + ",\"texRow\":" + texRow 
				+ ",\"looping\":" + looping + ",\"numCols\":" + numCols;
		
	}
	
	public String toString(){
		return "Row #" + texRow;
	}
}
