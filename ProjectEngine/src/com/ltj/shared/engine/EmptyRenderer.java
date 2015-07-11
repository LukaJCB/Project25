package com.ltj.shared.engine;

public class EmptyRenderer implements SpriteRenderer {

	private float x,y;
	private float rotation;
	private float height,width;
	
	public EmptyRenderer(){
		height = 1;
		width = 1;
	}
	
	public void translate(float dx, float dy){
		x += dx;
		y += dy;
	}

	
	
	public void rotate(float deg){
		rotation += deg;
	}

	public void scale(float sx,float sy){
		width *= sx;
		height *= sy;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public float getRotation() {
		return rotation;
	}

	@Override
	public void setModeSeven() {
		// do nothing

	}

	@Override
	public void setNormalMode() {
		// do nothing

	}

	@Override
	public float getZ() {
		// do nothing
		return 0;
	}

	@Override
	public void setZ(float z) {
		// do nothing

	}


	@Override
	public void setTexture(int col, int row) {
		// do nothing

	}

	@Override
	public void render() {
		// do nothing
		
	}

	@Override
	public int getNumCols() {
		// do nothing
		return 0;
	}

	@Override
	public int getNumRows() {
		// do nothing
		return 0;
	}

	@Override
	public void clear() {
		//do nothing at all
	}

	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	

	@Override
	public void setRotation(float deg) {
		this.rotation = deg;
	}

	@Override
	public void setRepeatTexture(float xRepeat, float yRepeat) {
		//do nothing
		
	}

	@Override
	public void draw() {
		
		
	}

	@Override
	public void setDisabled(boolean disabled) {
		//do nothing
		
	}

	@Override
	public boolean isDisabled() {
		return false;
	}

	@Override
	public void setScale(float x, float y) {
		width = x;
		height = y;
	}

	@Override
	public void animate() {
		// do nothing
		
	}

	@Override
	public void startAnimation(String name) {
		// do nothing
		
	}


	@Override
	public void addAnimation(String name, int animationTime, int texRow,
			boolean looping, int numCols) {
		// do nothing
	}

	@Override
	public void stopAnimation() {
		// do nothing
	}

	@Override
	public void setModeSevenEnabled(boolean modeSEnabled) {
		// do nothing
		
	}

	@Override
	public String toJSON() {
		
		return "\"x\":" + x + ",\"y\":" + y  + ",\"rotation\":" + rotation 
				+ ",\"width\":" + width + ",\"height\":" + height;
	}

}
