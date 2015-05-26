package com.ltj.shared.engine;

import com.ltj.shared.utils.MatrixHelper;

public abstract class AbstractHudRenderer implements HudRenderer {
	protected static final float[] vertices = {
		0f, 0f,
        0f, 1,
        1, 0f,
        1, 1 
	};
	protected static final float[] textureCoordinates =
	{
	    1.0f, 1.0f,
	    1.0f, 0.0f,
	    0.0f, 1.0f,
	    0.0f, 0.0f 
	};
	protected static final int COMPONENT_COUNT = 2;
	protected static final String A_POSITION = "aPosition";
	protected static final String A_TEX_COORDS = "aTexCoordinates";
	protected static final String U_TEX = "uTexture";
	protected static int aPositionLocation;
	protected static int[] positionVBO;
	protected static int aTexCoordsLocation;

	protected static int[] textureVBO;
	protected static int uTextureLocation;
	protected int texNumber;
	private float[] modelMatrix = new float[16];


	private float x,y;
	private float rotation;
	private float height,width;
	
	public AbstractHudRenderer(){

		//set Matrix
		MatrixHelper.setIdentityM(modelMatrix);

		x = y = rotation = 0;
		height = 1;
		width = 1;

	}


	public void translate(float dx, float dy){
		x += dx;
		y += dy;
		calcMatrix();
	}





	public void rotate(float deg){
		rotation += deg;
		calcMatrix();
	}

	public void scale(float sx,float sy){
		width *= sx;
		height *= sy;
		calcMatrix();
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




	private void calcMatrix(){
		MatrixHelper.setIdentityM(getModelMatrix());
		MatrixHelper.translateM(getModelMatrix(),  getX(), getY(), 0);
		MatrixHelper.rotateM(getModelMatrix(), getRotation(), 0, 0, 1);
		MatrixHelper.scaleM(getModelMatrix(),  getWidth(), getHeight(), 1);
	}

	protected float[] getModelMatrix() {
		return modelMatrix;
	}





}
