package com.ltj.java.engine;


import static com.jogamp.opengl.GL.GL_ARRAY_BUFFER;
import static com.jogamp.opengl.GL.GL_FLOAT;
import static com.jogamp.opengl.GL.GL_TEXTURE0;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import static com.ltj.java.engine.StaticGL.*;

import java.io.IOException;

import com.ltj.java.utils.JoglBufferHelper;
import com.ltj.java.utils.JoglTextureHelper;
import com.ltj.shared.engine.AbstractSpriteRenderer;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.RenderObject;
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
	
	public JoglSpriteRenderer(String path,int cols, int rows){
		super(path,cols,rows);
		

		initRenderer();
		

		//load texture
		loadTexture(path);
		
	}

	public JoglSpriteRenderer(String path, int columns, int rows,RenderObject obj) {
		super(path,columns,rows);
		initRenderer();
		
		JoglTextureHelper.loadTextureAsync(path, obj);
	}

	public void loadTexture(String path) {
		try {
			mTextureDataHandle = JoglTextureHelper.loadTexture(path);
			texNumber = mTextureDataHandle[1];
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initRenderer() {
		//convert to buffers
		if (positionVBO == null){
			positionVBO = JoglBufferHelper.arrayToBufferId( vertices);
			//get locations for shaders
			aPositionLocation =  glGetAttribLocation(JoglRenderer.programId, A_POSITION);
		}

		textureVBO = JoglBufferHelper.arrayToBufferId( textureCoordinates);
		uTextureLocation =  glGetUniformLocation(JoglRenderer.programId, U_TEX);
		aTexCoordsLocation =  glGetAttribLocation(JoglRenderer.programId, A_TEX_COORDS);
	}
	
	public void setTexture(int column, int row){
		textureCol = column;
		textureRow = row;
		
		//set the row and column of the sprite
		row = (int) ((1.0f/rowSize)- row-1);
		
		textureCoordinates[0] = (column+1) * columnSize;
		textureCoordinates[3] = row * rowSize;
		textureCoordinates[2] = (column+1) * columnSize;
		textureCoordinates[1] = (row+1) * rowSize;
		textureCoordinates[4] = column * columnSize;
		textureCoordinates[7] = row * rowSize;
		textureCoordinates[6] = column * columnSize;
		textureCoordinates[5] = (row+1) * rowSize;
		//retransmit to GPU
		textureVBO = JoglBufferHelper.arrayToBufferId( textureCoordinates);
		
	}

	public void setRepeatTexture(float horizontal, float vertical){
		repeatX = horizontal;
		repeatY = vertical;
		
		textureCoordinates[0] = horizontal;
		textureCoordinates[1] = vertical;
		textureCoordinates[2] = horizontal;
		textureCoordinates[5] = vertical;
		
		//retransmit to GPU
		textureVBO = JoglBufferHelper.arrayToBufferId( textureCoordinates);
	}


	@Override
	public void clear() {
		 JoglTextureHelper.deleteTexture(path);
		 glDeleteBuffers(1, textureVBO, 0);
	}

	@Override
	public void draw() {

		 glEnableVertexAttribArray(aPositionLocation);
		 glEnableVertexAttribArray(aTexCoordsLocation);
		
		float[] mMVP = new float[16];
		
		//calculate MVP
		MatrixHelper.multiplyMM(mMVP,Camera.getProjectionViewMatrix(), getModelMatrix());
		
		//specify uniform matrix
		 glUniformMatrix4fv(JoglRenderer.uMatrixLocation, 1, false,mMVP, 0);
		
		//set active texture
		 glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit
	     glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	    
	    // Tell the texture uniform sampler to use this texture in the shader
	     glUniform1i(uTextureLocation, texNumber);

	    //bind VBOs
	     glBindBuffer(GL_ARRAY_BUFFER, positionVBO[0]);
		 glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0,0);
		
		 glBindBuffer(GL_ARRAY_BUFFER, textureVBO[0]);
		 glVertexAttribPointer(aTexCoordsLocation, 2, GL_FLOAT, false, 0,0);
		
		 glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		//draw
		 glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);  
	
		
	}

	@Override
	public void finishLoading(String path) {
		initRenderer();
		mTextureDataHandle = JoglTextureHelper.finishLoading(path);
		texNumber = mTextureDataHandle[1];
	}
}
