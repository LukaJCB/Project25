package com.ltj.shared.engine;

import java.util.HashMap;


import com.ltj.shared.engine.SpriteRenderer;
import com.ltj.shared.utils.MatrixHelper;

public abstract class AbstractSpriteRenderer implements SpriteRenderer{
	private static int texCount;
	private static HashMap<String, Integer> textureMap = new HashMap<String,Integer>();
	protected final static float[] vertices = {
			0.5f, 0.5f,
	        0.5f, -0.5f,
	        -0.5f, 0.5f,
	        -0.5f, -0.5f 
	};
	protected static final int COMPONENT_COUNT = 2;
	protected static final String A_POSITION = "aPosition";
	protected static final String A_TEX_COORDS = "aTexCoordinates";
	protected static final String U_TEX = "uTexture";
	protected static int aPositionLocation;
	protected static int[] positionVBO;
	protected int texNumber;
	private float[] modelMatrix = new float[16];

	
	private float x,y;
	private float rotation;
	private float height,width;
	private float rotationX;
	private float z;
	protected float rowSize, columnSize;
	public AbstractSpriteRenderer(String path){
		if (textureMap.containsKey(path)){
			texNumber = textureMap.get(path);
		} else {
			texNumber = texCount;
			textureMap.put(path, texNumber);
			texCount++;
		}
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

	public void setZ(float z) {
		this.z = z;
		calcMatrix();
	}

	public void setModeSeven() {
		z = getHeight()/2;
		rotationX = 90;
		calcMatrix();
	}
	
	public void setNormalMode(){
		z = 0;
		rotationX = 0;
		calcMatrix();
	}

	public float getZ() {
		return z;
	}
	
	public void setSheetDimensions(int cols, int rows){
		columnSize = 1.0f / cols;
		rowSize = 1.0f / rows;
	}
	


	private void calcMatrix(){
		MatrixHelper.setIdentityM(getModelMatrix());
		MatrixHelper.translateM(getModelMatrix(),  getX(), getY(), z);
		MatrixHelper.rotateM(getModelMatrix(), getRotation(), 0, 0, 1);
		MatrixHelper.rotateM(getModelMatrix(),  rotationX, 1, 0, 0);
		MatrixHelper.scaleM(getModelMatrix(),  getWidth(), getHeight(), 1);
	}

	protected float[] getModelMatrix() {
		return modelMatrix;
	}

	@Override
	public int getNumCols() {
		return (int) (1/columnSize);
	}

	@Override
	public int getNumRows() {
		return (int) (1/rowSize);
	}


	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		calcMatrix();
	}

	@Override
	public void setRotation(float deg) {
		this.rotation = deg;
		calcMatrix();
	}
}
