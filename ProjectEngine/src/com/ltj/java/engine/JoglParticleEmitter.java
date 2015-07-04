package com.ltj.java.engine;

import static com.jogamp.opengl.GL.*;

import com.jogamp.opengl.GL3;
import com.ltj.java.utils.JoglBufferHelper;
import com.ltj.shared.engine.AbstractParticleEmitter;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

public class JoglParticleEmitter extends AbstractParticleEmitter {
	
	private GL3 gl;
	public JoglParticleEmitter(GL3 gl, int maxParticleCount,int runningtime, float red, float green, float blue) {
		super(maxParticleCount,runningtime, red,green,blue);
		
		this.gl = gl;
		
		//uniforms
		uMatrixLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_MATRIX);
		uCurrentTimeLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_TIME);
		uColorLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_COLOR);
		uPositionLocation = gl.glGetUniformLocation(JoglRenderer.particleProgramId, U_POSITION);
		
		//attributes
		aDirectionVectorLocation = gl.glGetAttribLocation(JoglRenderer.particleProgramId, A_DIRECTION);
		aParticleStartTimeLocation = gl.glGetAttribLocation(JoglRenderer.particleProgramId, A_START_TIME);

		emitterVBO = JoglBufferHelper.arrayToBufferId(gl, particleDirections);
		timeVBO = JoglBufferHelper.arrayToBufferId(gl, particleStartTimes);
		
		//set color uniform
		gl.glUseProgram(JoglRenderer.particleProgramId);
		gl.glUniform3f(uColorLocation, red, green, blue);
		gl.glUseProgram(JoglRenderer.programId);
	}
	
	
	public void render(){
		long current = System.currentTimeMillis() - globalStartTime ;
		if (current  <  getRunningTime()){
			
			gl.glEnableVertexAttribArray(aDirectionVectorLocation);
			gl.glEnableVertexAttribArray(aParticleStartTimeLocation);
			
			gl.glUniform1f(uCurrentTimeLocation,  current);
			//set position uniform
			
			gl.glUniform3fv(uPositionLocation, 3, getPosition(), 0);
			
			
			float[] mMVPMatrix = new float[16]; 
			MatrixHelper.multiplyMM(mMVPMatrix, Camera.getProjectionViewMatrix(), getModelMatrix());
			gl.glUniformMatrix4fv(uMatrixLocation, 1, false, mMVPMatrix, 0);
			
			gl.glBindBuffer(GL_ARRAY_BUFFER, emitterVBO[0]);
			gl.glVertexAttribPointer(aDirectionVectorLocation, 3, GL_FLOAT,false, 0, 0);
			
			gl.glBindBuffer(GL_ARRAY_BUFFER, timeVBO[0]);
			gl.glVertexAttribPointer(aParticleStartTimeLocation,1,GL_FLOAT,false, 0, 0);
			
			gl.glBindBuffer(GL_ARRAY_BUFFER, 0);
			
			//draw particles
			gl.glDrawArrays(GL_POINTS, 0, currentParticleCount);
		} 
	}

	@Override
	public void recalculateVBOs() {
		emitterVBO = JoglBufferHelper.arrayToBufferId(gl, particleDirections);
		timeVBO = JoglBufferHelper.arrayToBufferId(gl, particleStartTimes);
	}
}
