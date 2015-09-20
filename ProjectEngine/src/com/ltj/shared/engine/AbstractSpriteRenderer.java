package com.ltj.shared.engine;


import java.util.Map.Entry;
import java.util.Set;

import com.ltj.shared.utils.MatrixHelper;

public abstract class AbstractSpriteRenderer implements SpriteRenderer {
	
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
	private boolean disabled;
	private float[] modelMatrix = new float[16];

	
	private float x,y;
	private float rotation;
	private float height,width;
	private float rotationX;
	private float z;
	protected float repeatX, repeatY;
	protected int textureRow, textureCol;
	protected float rowSize, columnSize;
	private Animator animator;
	private boolean modeSEnabled;
	protected String path;
	
	public AbstractSpriteRenderer(String path,int columns, int rows){
		
		//set sheet dimensions
		setSheetDimensions(columns, rows);
		
		//set Matrix
		MatrixHelper.setIdentityM(modelMatrix);

		//set base values
		x = y = rotation = textureRow = textureCol = 0;
		height = width = repeatX = repeatY = 1;
		
		this.path = path;
		
	}

	@Override
	public void animate(){
		if (animator != null){
			animator.play(this);
		}
	}




	@Override
	public void addAnimation(String name, int animationTime, int texRow, boolean looping, int numCols){
		if (animator == null){
			animator = new Animator();
		}
		animator.addAnimation(name, new Animation(animationTime, texRow, looping, numCols));
	}



	@Override
	public Set<Entry<String, Animation>> getAllAnimations() {
		if (animator != null){
			return animator.getAllAnimations();
		}
		return null;
	}

	@Override
	public void startAnimation(String name) {
		animator.startAnimation(name);
	}

	@Override
	public void stopAnimation() {
		animator.stopAnimation();
	}
	
	@Override
	public Animation getAnimation(String name){
		return animator.getAnimation(name);
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

	@Override
	public void setScale(float x, float y) {
		width = x;
		height = y;
		calcMatrix();
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

	@Override
	public boolean isModeSevenEnabled() {
		return modeSEnabled;
	}

	@Override
	public void setModeSevenEnabled(boolean modeSEnabled) {

		this.modeSEnabled = modeSEnabled;
	}

	public void setModeSeven() {
		if (modeSEnabled){
			z = getHeight()/2;
			rotationX = 90;
			calcMatrix();
		}
	}
	
	public void setNormalMode(){
		if (modeSEnabled){
			z = 0;
			rotationX = 0;
			calcMatrix();
		}
	}

	public float getZ() {
		return z;
	}
	
	@Override
	public void setSheetDimensions(int cols, int rows){
		columnSize = 1.0f / cols;
		rowSize = 1.0f / rows;
	}



	private void calcMatrix(){
		//multiply all matrices together
		MatrixHelper.setIdentityM(getModelMatrix());
		MatrixHelper.translateM(getModelMatrix(),  getX(), getY(), z);
		MatrixHelper.rotateM(getModelMatrix(), getRotation(), 0, 0, 1);
		MatrixHelper.rotateM(getModelMatrix(),  rotationX, 1, 0, 0);
		MatrixHelper.scaleM(getModelMatrix(),  getWidth(), getHeight(), 1);

	}

	
	@Override
	public float getRepeatX() {
		return repeatX;
	}
	@Override
	public float getRepeatY() {
		return repeatY;
	}
	@Override
	public int getTextureRow() {
		return textureRow;
	}
	@Override
	public int getTextureCol() {
		return textureCol;
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
	public final void render() {
		if (!disabled){
			draw();
		}
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

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public String toJSON() {
		String basics = "\"x\":" + x + ",\"y\":" + y + ",\"z\":" + z + ",\"rotation\":" + rotation 
				+ ",\"width\":" + width + ",\"height\":" + height + ",\"repeatX\":" + repeatX + ",\"repeatY\":" + repeatY
				+",\"renderer_disabled\":" + disabled + ",\"textureRow\":" + textureRow + ",\"textureCol\":" + textureCol
				+ ",\"path\":\"" + "images/" + path.replace("\\", "/").split("images/")[1] + "\",\"columns\":" + getNumCols() + ",\"rows\":" + getNumRows() 
				+ ",\"modeSEnabled\":" + modeSEnabled + ",\"animator\":";
		if (animator != null){
			basics += animator.toJSON();
		} else {
			basics += "\"null\",";
		}
		return basics;
				
	}
	
	
	
}
