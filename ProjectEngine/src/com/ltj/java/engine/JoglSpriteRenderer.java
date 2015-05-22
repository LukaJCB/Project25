package com.ltj.java.engine;

import static com.jogamp.opengl.GL.GL_ARRAY_BUFFER;
import static com.jogamp.opengl.GL.GL_FLOAT;
import static com.jogamp.opengl.GL.GL_TEXTURE0;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;

import java.io.IOException;


import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.texture.Texture;
import com.ltj.java.utils.JoglBufferHelper;
import com.ltj.java.utils.JoglTextureHelper;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.SpriteRenderer;
import com.ltj.shared.utils.MatrixHelper;

public class JoglSpriteRenderer implements SpriteRenderer{
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
	        1.0f, 1.0f,
	        1.0f, 0.0f,
	        0.0f, 1.0f,
	        0.0f, 0.0f 
		};
	private float[] modelMatrix = new float[16];

	private static final int COMPONENT_COUNT = 2;
	private static final String A_POSITION = "aPosition";
	private static final String A_TEX_COORDS = "aTexCoordinates";
	private static final String U_TEX = "uTexture";
	
	private int aPositionLocation, aTexCoordsLocation;

	private int[] textureVBO,positionVBO;
	private Texture texture;
	private int uTextureLocation;
	private int mTextureDataHandle;
	
	private float x,y;
	private float rotation;
	private float height,width;
	private GL4 gl;
	private float rotationX;
	private float z;
	private float rowSize, columnSize;
	public JoglSpriteRenderer(GL4 gl, String path){
		this.gl = gl;
		this.texNumber = texCount;
		texCount++;
		
		//set Matrix
		MatrixHelper.setIdentityM(modelMatrix);

		x = y = rotation = 0;
		height = 1;
		width = 1;
		
		//convert to buffers
		positionVBO = JoglBufferHelper.arrayToBufferId(gl, vertices);
		textureVBO = JoglBufferHelper.arrayToBufferId(gl, textureCoordinates);
		
		//get locations for shaders
		uTextureLocation = gl.glGetUniformLocation(JoglRenderer.programId, U_TEX);
		aPositionLocation = gl.glGetAttribLocation(JoglRenderer.programId, A_POSITION);
		aTexCoordsLocation = gl.glGetAttribLocation(JoglRenderer.programId, A_TEX_COORDS);
		
	
		//load texture
		try {
			texture = JoglTextureHelper.loadTexture(gl, path);
			mTextureDataHandle = texture.getTextureObject();
		}  catch (IOException e) {
			e.printStackTrace();
		}
		

		//set active texturetype
		gl.glActiveTexture(GL_TEXTURE0 + texNumber);
		 
		
	    // Bind the texture to this unit.
	    gl.glBindTexture(GL_TEXTURE_2D, mTextureDataHandle);
	 
	    // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 
	    gl.glUniform1i(uTextureLocation, texNumber);
		
		//enable vertex data
		gl.glEnableVertexAttribArray(aPositionLocation);
		gl.glEnableVertexAttribArray(aTexCoordsLocation);
		gl.glVertexAttribPointer(aPositionLocation, COMPONENT_COUNT, GL_FLOAT, false, 0, 0);
		gl.glVertexAttribPointer(aTexCoordsLocation , COMPONENT_COUNT, GL_FLOAT, false, 0, 0);
	}
	
	public void render() {
		
		float[] mMVP = new float[16];
		
		//calculate MVP
		MatrixHelper.multiplyMM(mMVP,Camera.getProjectionViewMatrix(), modelMatrix);
		
		//specify uniform matrix
		gl.glUniformMatrix4fv(JoglRenderer.uMatrixLocation, 1, false,mMVP, 0);
		
		//set active texturetype
		gl.glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit.
	    gl.glBindTexture(GL_TEXTURE_2D, mTextureDataHandle);
	    
	    // Tell the texture uniform sampler to use this texture in the shader.
	    gl.glUniform1i(uTextureLocation, texNumber);

	    gl.glBindBuffer(GL_ARRAY_BUFFER, positionVBO[0]);
		gl.glEnableVertexAttribArray(aPositionLocation);
		gl.glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0,0);
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, textureVBO[0]);
		gl.glEnableVertexAttribArray(aTexCoordsLocation);
		gl.glVertexAttribPointer(aTexCoordsLocation, 2, GL_FLOAT, false, 0,0);
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		//draw
		gl.glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);  
	
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
	public void setTexture(int column, int row){
		
		row = (int) ((1.0f/rowSize)- row-1);
		
		textureCoordinates[0] = (column+1) * columnSize;
		textureCoordinates[3] = row * rowSize;
		textureCoordinates[2] = (column+1) * columnSize;
		textureCoordinates[1] = (row+1) * rowSize;
		textureCoordinates[4] = column * columnSize;
		textureCoordinates[7] = row * rowSize;
		textureCoordinates[6] = column * columnSize;
		textureCoordinates[5] = (row+1) * rowSize;
		
		textureVBO = JoglBufferHelper.arrayToBufferId(gl, textureCoordinates);;
		
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

	@Override
	public int getNumCols() {
		return (int) (1/columnSize);
	}

	@Override
	public int getNumRows() {
		return (int) (1/rowSize);
	}

	@Override
	public void clear() {
		texture.destroy(gl);
		gl.glDeleteBuffers(1, positionVBO, 0);
		gl.glDeleteBuffers(1, textureVBO, 0);
	}
}
