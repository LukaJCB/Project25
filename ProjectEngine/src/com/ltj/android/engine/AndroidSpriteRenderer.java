package com.ltj.android.engine;



import com.ltj.android.utils.AndroidBufferHelper;
import com.ltj.android.utils.AndroidTextureHelper;
import com.ltj.shared.engine.AbstractSpriteRenderer;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

import static android.opengl.GLES20.*;


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
	
	public AndroidSpriteRenderer(String path){
		super();
		
		
		//convert to buffers
		if (positionVBO == null){
			positionVBO = AndroidBufferHelper.arrayToBufferId(vertices);
		}
		textureVBO = AndroidBufferHelper.arrayToBufferId(textureCoordinates);
		
		//get locations for shaders
		uTextureLocation = glGetUniformLocation(AndroidRenderer.programId, U_TEX);
		aPositionLocation = glGetAttribLocation(AndroidRenderer.programId, A_POSITION);
		aTexCoordsLocation = glGetAttribLocation(AndroidRenderer.programId, A_TEX_COORDS);
		
	
		//load texture
		mTextureDataHandle = AndroidTextureHelper.loadTexture(AndroidRenderer.context, path);
		texNumber = mTextureDataHandle[1];
		
		

		//set active texturetype
		glActiveTexture(GL_TEXTURE0 + texNumber);
		 
		
	    // Bind the texture to this unit.
	    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	 
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
		MatrixHelper.multiplyMM(mMVP,Camera.getProjectionViewMatrix(), getModelMatrix());
		
		//specify uniform matrix
		glUniformMatrix4fv(AndroidRenderer.uMatrixLocation, 1, false,mMVP, 0);
		
		//set active texturetype
		glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit.
	    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	    
	    // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
	    glUniform1i(uTextureLocation, texNumber);

	    glBindBuffer(GL_ARRAY_BUFFER, positionVBO[0]);
		glEnableVertexAttribArray(aPositionLocation);
		glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, textureVBO[0]);
		glEnableVertexAttribArray(aTexCoordsLocation);
		glVertexAttribPointer(aTexCoordsLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		//draw
		glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);  
	
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
		
		textureVBO = AndroidBufferHelper.arrayToBufferId(textureCoordinates);;
		
	}

	@Override
	public void clear() {
		glDeleteTextures(1, mTextureDataHandle, 0);
		glDeleteBuffers(1, textureVBO, 0);
		glDeleteBuffers(1, positionVBO, 0);
	}

	@Override
	public void setRepeatTexture(float horizontal, float vertical) {

		textureCoordinates[3] = vertical;
		textureCoordinates[4] = horizontal;
		textureCoordinates[6] = horizontal;
		textureCoordinates[7] = vertical;
		
		textureVBO = AndroidBufferHelper.arrayToBufferId(textureCoordinates);
		
	}

}
