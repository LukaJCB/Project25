package com.ltj.java.engine;

import static com.jogamp.opengl.GL.GL_ARRAY_BUFFER;
import static com.jogamp.opengl.GL.GL_FLOAT;
import static com.jogamp.opengl.GL.GL_TEXTURE0;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;

import java.io.IOException;


import com.jogamp.opengl.GL3;
import com.ltj.java.utils.JoglBufferHelper;
import com.ltj.java.utils.JoglTextureHelper;
import com.ltj.shared.engine.AbstractSpriteRenderer;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

public class JoglSpriteRenderer extends AbstractSpriteRenderer{
	
	private float[] textureCoordinates =
		{
	        1.0f, 1.0f,
	        1.0f, 0.0f,
	        0.0f, 1.0f,
	        0.0f, 0.0f 
		};

	private int aTexCoordsLocation;

	private int[] textureVBO;
	private int uTextureLocation;
	private int[] mTextureDataHandle;
	
	private GL3 gl;
	public JoglSpriteRenderer(GL3 gl, String path){
		super(path);
		this.gl = gl;

		//convert to buffers
		positionVBO = JoglBufferHelper.arrayToBufferId(gl, vertices);
		textureVBO = JoglBufferHelper.arrayToBufferId(gl, textureCoordinates);
		
		//get locations for shaders
		uTextureLocation = gl.glGetUniformLocation(JoglRenderer.programId, U_TEX);
		aPositionLocation = gl.glGetAttribLocation(JoglRenderer.programId, A_POSITION);
		aTexCoordsLocation = gl.glGetAttribLocation(JoglRenderer.programId, A_TEX_COORDS);
		
	
		//load texture
		try {
			mTextureDataHandle = JoglTextureHelper.loadTexture(gl, path);
			texNumber = mTextureDataHandle[1];
		}  catch (IOException e) {
			e.printStackTrace();
		}
		

		//set active texturetype
		gl.glActiveTexture(GL_TEXTURE0 + texNumber);
		 
		
	    // Bind the texture to this unit.
	    gl.glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	 
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
		MatrixHelper.multiplyMM(mMVP,Camera.getProjectionViewMatrix(), getModelMatrix());
		
		//specify uniform matrix
		gl.glUniformMatrix4fv(JoglRenderer.uMatrixLocation, 1, false,mMVP, 0);
		
		//set active texturetype
		gl.glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit.
	    gl.glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	    
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
		
		textureVBO = JoglBufferHelper.arrayToBufferId(gl, textureCoordinates);
		
	}



	@Override
	public void clear() {
		gl.glDeleteTextures(1, mTextureDataHandle, 0);
		gl.glDeleteBuffers(1, positionVBO, 0);
		gl.glDeleteBuffers(1, textureVBO, 0);
	}
}
