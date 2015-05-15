package com.ltj.engine.android;



import com.ltj.engine.SpriteRenderer;
import com.ltj.engine.primitives.Camera;
import com.ltj.engine.utils.BufferHelper;
import com.ltj.engine.utils.MatrixHelper;
import com.ltj.engine.utils.TextureHelper;
import static android.opengl.GLES20.*;


public class AndroidSpriteRenderer implements SpriteRenderer{
	private static int texCount;
	private int texNumber;
	private float[] vertices = {
			0.5f, 0.5f,
	        0.5f, -0.5f,
	        -0.5f, 0.5f,
	        -0.5f, -0.5f 
	};
	private float[] textureCoordinates =
		{
	        0.0f, 0.0f,
	        0.0f, 1.0f,
	        1.0f, 0.0f,
	        1.0f, 1.0f 
		};
	private float[] modelMatrix = new float[16];

	private static final int COMPONENT_COUNT = 2;
	private static final String A_POSITION = "aPosition";
	private static final String A_TEX_COORDS = "aTexCoordinates";
	private static final String U_TEX = "uTexture";
	
	private int aPositionLocation, aTexCoordsLocation;

	private int positionVBO, textureVBO;
	private int uTextureLocation;
	private int mTextureDataHandle;
	
	private float x,y;
	private float rotation;
	private float height,width;
	private float rotationX;
	private float z;
	private float rowSize, columnSize;
	public AndroidSpriteRenderer(int resId){
		this.texNumber = texCount;
		texCount++;
		
		//set Matrix
		MatrixHelper.setIdentityM(modelMatrix);

		x = y = rotation = 0;
		height = 1;
		width = 1;
		
		//convert to buffers
		positionVBO = BufferHelper.arrayToBufferId(vertices);
		textureVBO = BufferHelper.arrayToBufferId(textureCoordinates);
		
		//get locations for shaders
		uTextureLocation = glGetUniformLocation(AndroidRenderer.programId, U_TEX);
		aPositionLocation = glGetAttribLocation(AndroidRenderer.programId, A_POSITION);
		aTexCoordsLocation = glGetAttribLocation(AndroidRenderer.programId, A_TEX_COORDS);
		
	
		//load texture
		mTextureDataHandle = TextureHelper.loadTexture(AndroidRenderer.context, resId);
		
		

		//set active texturetype
		glActiveTexture(GL_TEXTURE0 + texNumber);
		 
		
	    // Bind the texture to this unit.
	    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle);
	 
	    // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 
	    glUniform1i(uTextureLocation, texNumber);
		
		//enable vertex data
		glEnableVertexAttribArray(aPositionLocation);
		glEnableVertexAttribArray(aTexCoordsLocation);
		glVertexAttribPointer(aPositionLocation, COMPONENT_COUNT, GL_FLOAT, false, 0, 0);
		glVertexAttribPointer(aTexCoordsLocation , COMPONENT_COUNT, GL_FLOAT, false, 0, 0);
	}
	
	public void render() {
		
		float[] mMVP = new float[16];
		
		//calculate MVP
		MatrixHelper.multiplyMM(mMVP,Camera.getProjectionViewMatrix(), modelMatrix);
		
		//specify uniform matrix
		glUniformMatrix4fv(AndroidRenderer.uMatrixLocation, 1, false,mMVP, 0);
		
		//set active texturetype
		glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit.
	    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle);
	    
	    // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
	    glUniform1i(uTextureLocation, texNumber);

	    glBindBuffer(GL_ARRAY_BUFFER, positionVBO);
		glEnableVertexAttribArray(aPositionLocation);
		glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, textureVBO);
		glEnableVertexAttribArray(aTexCoordsLocation);
		glVertexAttribPointer(aTexCoordsLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		//draw
		glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);  
	
	}
	
	public void translate(float dx, float dy){
		x += dx*width;
		y += dy*height;
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
		System.out.println(columnSize + " " + rowSize);
	}
	public void setTexture(int column, int row){
		textureCoordinates[0] = column * columnSize;
		textureCoordinates[1] = row * rowSize;
		textureCoordinates[2] = column * columnSize;
		textureCoordinates[3] = (row+1) * rowSize;
		textureCoordinates[4] = (column+1) * columnSize;
		textureCoordinates[5] = row * rowSize;
		textureCoordinates[6] = (column+1) * columnSize;
		textureCoordinates[7] = (row+1) * rowSize;
		
		textureVBO = BufferHelper.arrayToBufferId(textureCoordinates);;
		
	}

	private void calcMatrix(){
		MatrixHelper.setIdentityM(getModelMatrix());
		MatrixHelper.translateM(getModelMatrix(),  getX(), getY(), z);
		MatrixHelper.rotateM(getModelMatrix(), getRotation(), 0, 0, 1);
		MatrixHelper.rotateM(getModelMatrix(),  rotationX, 1, 0, 0);
		MatrixHelper.scaleM(getModelMatrix(),  getWidth(), getHeight(), 1);
	}

	private float[] getModelMatrix() {
		return modelMatrix;
	}
}
