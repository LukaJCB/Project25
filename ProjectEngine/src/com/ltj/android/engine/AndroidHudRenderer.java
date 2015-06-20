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
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glVertexAttribPointer;

import com.ltj.android.utils.AndroidBufferHelper;
import com.ltj.android.utils.AndroidTextureHelper;
import com.ltj.shared.engine.AbstractHudRenderer;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

public class AndroidHudRenderer extends AbstractHudRenderer {

	protected static final float[] textureCoordinates = {
	    0, 0,
	    0, 1,
	    1, 0,
	    1, 1 
	};
	
	private int[] mTextureDataHandle;
	
	public AndroidHudRenderer(String path){
		super();
		
		if (positionVBO == null){
			//convert to buffers
			positionVBO = AndroidBufferHelper.arrayToBufferId(vertices);
			textureVBO = AndroidBufferHelper.arrayToBufferId(textureCoordinates);

			//get locations for shaders
			uTextureLocation = glGetUniformLocation(AndroidRenderer.programId, U_TEX);
			aPositionLocation = glGetAttribLocation(AndroidRenderer.programId, A_POSITION);
			aTexCoordsLocation = glGetAttribLocation(AndroidRenderer.programId, A_TEX_COORDS);
			

			glEnableVertexAttribArray(aPositionLocation);
			glEnableVertexAttribArray(aTexCoordsLocation);
		}
	
		//load texture
		mTextureDataHandle = AndroidTextureHelper.loadTexture(AndroidRenderer.context, path);
		texNumber = mTextureDataHandle[1];
		
	}
	
	public void render() {
		
		float[] modelProjectionMatrix = new float[16];
		
		//calculate MVP
		MatrixHelper.multiplyMM(modelProjectionMatrix,Camera.getOrthoProjectionMatrix(), getModelMatrix());
		
		//specify uniform matrix
		glUniformMatrix4fv(AndroidRenderer.uMatrixLocation, 1, false,modelProjectionMatrix, 0);
		
		//set active texturetype
		glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit.
	    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	    
	    // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
	    glUniform1i(uTextureLocation, texNumber);

	    glBindBuffer(GL_ARRAY_BUFFER, positionVBO[0]);
		glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, textureVBO[0]);
		glVertexAttribPointer(aTexCoordsLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		//draw
		glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);  
	
	}
	


	@Override
	public void clear() {
		glDeleteTextures(1, mTextureDataHandle, 0);
		glDeleteBuffers(1, textureVBO, 0);
		glDeleteBuffers(1, positionVBO, 0);
	}


}
