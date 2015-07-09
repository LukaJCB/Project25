package com.ltj.java.engine;

import static com.jogamp.opengl.GL3.*;
import static com.ltj.java.engine.StaticGL.*;
import java.io.IOException;



import com.ltj.java.utils.JoglBufferHelper;
import com.ltj.java.utils.JoglTextureHelper;
import com.ltj.shared.engine.AbstractHudRenderer;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

public class JoglHudRenderer extends AbstractHudRenderer {

	protected static final float[] textureCoordinates = {
	    1.0f, 1.0f,
	    1.0f, 0.0f,
	    0.0f, 1.0f,
	    0.0f, 0.0f 
	};
	private int[] mTextureDataHandle;
	

	public JoglHudRenderer(String path){
		super();
		

		if (positionVBO == null){
			//convert to buffers
			positionVBO = JoglBufferHelper.arrayToBufferId( vertices);
			textureVBO = JoglBufferHelper.arrayToBufferId( textureCoordinates);

			//get locations for shaders
			uTextureLocation = glGetUniformLocation(JoglRenderer.programId, U_TEX);
			aPositionLocation = glGetAttribLocation(JoglRenderer.programId, A_POSITION);
			aTexCoordsLocation = glGetAttribLocation(JoglRenderer.programId, A_TEX_COORDS);

		}
	
		//load texture
		try {
			mTextureDataHandle = JoglTextureHelper.loadTexture( path);
			texNumber = mTextureDataHandle[1];
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void clear() {
		glDeleteTextures(1, mTextureDataHandle, 0);
		glDeleteBuffers(1, positionVBO, 0);
		glDeleteBuffers(1, textureVBO, 0);
	}

	
	@Override
	public void render() {
		
		glEnableVertexAttribArray(aPositionLocation);
		glEnableVertexAttribArray(aTexCoordsLocation);
		
		float[] projectionModelMatrix = new float[16];
		
		//calculate MP
		MatrixHelper.multiplyMM(projectionModelMatrix,Camera.getOrthoProjectionMatrix(), getModelMatrix());
		
		//specify uniform matrix
		glUniformMatrix4fv(JoglRenderer.uMatrixLocation, 1, false,projectionModelMatrix, 0);
		
		//set active texturetype
		glActiveTexture(GL_TEXTURE0 + texNumber);
		
		 // Bind the texture to this unit.
	    glBindTexture(GL_TEXTURE_2D, mTextureDataHandle[0]);
	    
	    // Tell the texture uniform sampler to use this texture in the shader.
	    glUniform1i(uTextureLocation, texNumber);

	    glBindBuffer(GL_ARRAY_BUFFER, positionVBO[0]);
		glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, textureVBO[0]);
		glVertexAttribPointer(aTexCoordsLocation, 2, GL_FLOAT, false, 0,0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		//draw
		glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);  
		
	}

}
