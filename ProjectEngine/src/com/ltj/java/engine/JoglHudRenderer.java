package com.ltj.java.engine;

import static com.jogamp.opengl.GL.*;

import java.io.IOException;

import com.jogamp.opengl.GL3;
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
	
	private GL3 gl;
	public JoglHudRenderer(GL3 gl, String path){
		super();
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


	@Override
	public void clear() {
		gl.glDeleteTextures(1, mTextureDataHandle, 0);
		gl.glDeleteBuffers(1, positionVBO, 0);
		gl.glDeleteBuffers(1, textureVBO, 0);
	}

	
	@Override
	public void render() {
		
		
		float[] projectionModelMatrix = new float[16];
		
		//calculate MP
		MatrixHelper.multiplyMM(projectionModelMatrix,Camera.getOrthoProjectionMatrix(), getModelMatrix());
		
		//specify uniform matrix
		gl.glUniformMatrix4fv(JoglRenderer.uMatrixLocation, 1, false,projectionModelMatrix, 0);
		
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

}
