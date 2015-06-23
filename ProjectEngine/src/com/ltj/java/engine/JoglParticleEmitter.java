package com.ltj.java.engine;

import static com.jogamp.opengl.GL.*;

import com.jogamp.opengl.GL3;
import com.ltj.java.utils.JoglBufferHelper;
import com.ltj.shared.engine.AbstractParticleEmitter;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

public class JoglParticleEmitter extends AbstractParticleEmitter {
	
	private GL3 gl;
	public JoglParticleEmitter(GL3 gl, int maxParticleCount, float red, float green, float blue) {
		super(maxParticleCount, red,green,blue);
		
		this.gl = gl;
		
		//uniforms
		uMatrixLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_MATRIX);
		uCurrentTimeLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_TIME);
		uColorLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_COLOR);
		uPositionLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_POSITION);
		
		//attributes
		aDirectionVectorLocation = gl.glGetAttribLocation(JoglRenderer.particleProgramId, A_DIRECTION);
		aParticleStartTimeLocation = gl.glGetAttribLocation(JoglRenderer.particleProgramId, A_START_TIME);

		emitterVBO = JoglBufferHelper.arrayToBufferId(gl, particles);
		
		//set color uniform
		gl.glUseProgram(JoglRenderer.particleProgramId);
		gl.glUniform3f(uColorLocation, red, green, blue);
		gl.glUseProgram(JoglRenderer.programId);
	}
	
	
	public void render(){
		
		gl.glEnableVertexAttribArray(uPositionLocation);
		gl.glUniform1f(uCurrentTimeLocation,  System.currentTimeMillis());
		
		//set position uniform
		gl.glUseProgram(JoglRenderer.particleProgramId);
		gl.glUniform3f(uPositionLocation, getX(),getY(),getZ());
		
		float[] mMVPMatrix = new float[16]; 
		MatrixHelper.multiplyMM(mMVPMatrix, Camera.getProjectionViewMatrix(), getModelMatrix());
		gl.glUniformMatrix4fv(uMatrixLocation, 1, false, mMVPMatrix, 0);
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, emitterVBO[0]);
		
		gl.glVertexAttribPointer(aDirectionVectorLocation, POSITION_COMPONENT_COUNT, GL_FLOAT,false, TOTAL_COMPONENT_COUNT * 4, 0);
		gl.glVertexAttribPointer(aParticleStartTimeLocation,1,GL_FLOAT,false, TOTAL_COMPONENT_COUNT * 4, POSITION_COMPONENT_COUNT);
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		//draw particles
		gl.glDrawArrays(GL_POINTS, 0, currentParticleCount);
		
	}
}
