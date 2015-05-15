package com.ltj.shared.engine;

public class EmptyRenderer implements SpriteRenderer {

	private float x,y;
	private float rotation;
	private float height,width;
	
	public void translate(float dx, float dy){
		x += dx*width;
		y += dy*height;
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
	public void setSheetDimensions(int cols, int rows) {
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

}
