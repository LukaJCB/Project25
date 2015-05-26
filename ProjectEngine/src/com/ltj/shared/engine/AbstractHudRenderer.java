package com.ltj.shared.engine;

public abstract class AbstractHudRenderer implements HudRenderer {

	private float x,y;
	private float height = 1,width = 1;
	protected static final int COMPONENT_COUNT = 2;
	protected static final String A_POSITION = "aPosition";
	protected static final String A_TEX_COORDS = "aTexCoordinates";
	protected static final String U_TEX = "uTexture";
	protected static int aPositionLocation;
	protected static int[] positionVBO;
	protected int texNumber;
	protected static float[] textureCoordinates =
		{
	        1.0f, 1.0f,
	        1.0f, 0.0f,
	        0.0f, 1.0f,
	        0.0f, 0.0f 
		};

	protected static int aTexCoordsLocation;

	protected static int[] textureVBO;
	protected static int uTextureLocation;
	
	protected float[] vertices = {
		0,0,
		0,1,
		1,0,
		1,1
	};
	
	
	
	@Override
	public void translate(float dx, float dy) {
		vertices[0] += dx;
		vertices[2] += dx;
		
		vertices[1] += dy;
		vertices[5] += dy;
		x += dx;
		y += dy;
	}


	@Override
	public void scale(float sx, float sy) {
		vertices[4] *= sx;
		vertices[6] *= sx;
		
		vertices[3] *= sy;
		vertices[7] *= sy;

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

}
