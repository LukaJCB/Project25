package com.ltj.shared.engine;

import com.ltj.shared.utils.MatrixHelper;

public abstract class AbstractHudRenderer implements HudRenderer {
	private static float screenHeight, screenWidth;
	protected static final float[] vertices = {
		0, 0,
        0, 1,
        1, 0,
        1, 1 
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
	private String path;



	public AbstractHudRenderer(String path){
		this.path = path;

		//set Matrix
		MatrixHelper.setIdentityM(modelMatrix);

		x = y = rotation = 0;
		height = 1;
		width = 1;

	}
	
	@Override
	public String getPath() {
		return path;
	}


	@Override
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		calcMatrix();
	}


	public void translate(float dx, float dy){
		x += dx;
		y += dy;
		calcMatrix();
	}





	@Override
	public void setScaling(float sx, float sy) {
		width = sx;
		height = sy;
		calcMatrix();
	}



	public void scale(float sx,float sy){
		width *= sx;
		height *= sy;
		calcMatrix();
	}


	@Override
	public void setRotation(float deg) {
		this.rotation = deg;
		calcMatrix();
	}

	@Override
	public void rotate(float deg){
		rotation += deg;
		calcMatrix();
	}
	@Override
	public float getX() {
		return x*screenWidth;
	}
	@Override
	public float getY() {
		return y*screenHeight;
	}
	@Override
	public float getHeight() {
		return height*screenWidth;
	}
	@Override
	public float getWidth() {
		return width*screenWidth;
	}
	@Override
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
	
	@Override
	public void setScreenDimensions(float width, float height){
		screenWidth = width;
		screenHeight = height;
		calcMatrix();
	}


	@Override
	public String toJSON() {
		
				
		return "\"x\":" + x + ",\"y\":" + y + ",\"rotation\":" + rotation 
				+ ",\"width\":" + width + ",\"height\":" + height 
				+ ",\"path\":\"" + "images/" + path.replace("\\", "/").split("images/")[1] + "\",";
				
	}


}
