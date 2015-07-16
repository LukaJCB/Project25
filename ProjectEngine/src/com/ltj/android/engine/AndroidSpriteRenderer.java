package com.ltj.android.engine;



import static android.opengl.GLES20.GL_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindBuffer;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDeleteBuffers;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glVertexAttribPointer;

import com.ltj.android.utils.AndroidBufferHelper;
import com.ltj.android.utils.AndroidTextureHelper;
import com.ltj.shared.engine.AbstractSpriteRenderer;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;


public class AndroidSpriteRenderer extends AbstractSpriteRenderer{
	
	private float[] textureCoordinates =
		{
	        0.0f, 0.0f,
	        0.0f, 1.0f,
	        1.0f, 0.0f,
	        1.0f, 1.0f 
		};
	private int aTexCoordsLocation;

	private int[] textureVBO;
	private int uTextureLocation;
	private int[] mTextureDataHandle;
	
	public AndroidSpriteRenderer(String path, int cols, int rows){
		super(path,cols,rows);
		
		initRenderer();
		
	
		//load texture
		mTextureDataHandle = AndroidTextureHelper.loadTexture(AndroidRenderer.context, path);
		texNumber = mTextureDataHandle[1];
		
	}

	public AndroidSpriteRenderer(String path, int columns, int rows,AndroidSprite obj) {
		super(path,columns,rows);
		initRenderer();
		AndroidTextureHelper.loadTextureAsync(path, obj);
	}

	public void setTexture(int column, int row){
		textureCol = column;
		textureRow = row;
		
		
		//set column and row of the sprite
		textureCoordinates[0] = column * columnSize;
		textureCoordinates[1] = row * rowSize;
		textureCoordinates[2] = column * columnSize;
		textureCoordinates[3] = (row+1) * rowSize;
		textureCoordinates[4] = (column+1) * columnSize;
		textureCoordinates[5] = row * rowSize;
		textureCoordinates[6] = (column+1) * columnSize;
		textureCoordinates[7] = (row+1) * rowSize;
		
		//retransmit vertexBuffer to GPU
		textureVBO = AndroidBufferHelper.arrayToBufferId(textureCoordinates);;
		
	}

	private void initRenderer() {
		//convert to buffers
		if (positionVBO == null){
			positionVBO = AndroidBufferHelper.arrayToBufferId(vertices);
			aPositionLocation = glGetAttribLocation(AndroidRenderer.programId, A_POSITION);
		}
		textureVBO = AndroidBufferHelper.arrayToBufferId(textureCoordinates);
		
		//get locations for shaders
		uTextureLocation = glGetUniformLocation(AndroidRenderer.programId, U_TEX);
		aTexCoordsLocation = glGetAttribLocation(AndroidRenderer.programId, A_TEX_COORDS);
	}

	@Override
	public void clear() {
		AndroidTextureHelper.deleteTexture(path);
		glDeleteBuffers(1, textureVBO, 0);
	}

	@Override
	public void setRepeatTexture(float horizontal, float vertical) {
		repeatX = horizontal;
		repeatY = vertical;
		
		textureCoordinates[3] = vertical;
		textureCoordinates[4] = horizontal;
		textureCoordinates[6] = horizontal;
		textureCoordinates[7] = vertical;
		
		//retransmit vertexBuffer to GPU
		textureVBO = AndroidBufferHelper.arrayToBufferId(textureCoordinates);
		
	}

	@Override
	public void draw() {
float[] mMVP = new float[16];
		
		//calculate MVP
		MatrixHelper.multiplyMM(mMVP,Camera.getProjectionViewMatrix(), getModelMatrix());
		
		//specify uniform matrix
		glUniformMatrix4fv(AndroidRenderer.uMatrixLocation, 1, false,mMVP, 0);
		
		//set active texture
		glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit
	    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	    
	    // Tell the texture uniform sampler to use this texture
	    glUniform1i(uTextureLocation, texNumber);

	    //Bind VBOs
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
		mTextureDataHandle = AndroidTextureHelper.finishLoading(path);
		texNumber = mTextureDataHandle[1];
	}

}
