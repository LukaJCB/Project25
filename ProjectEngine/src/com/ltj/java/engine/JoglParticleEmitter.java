package com.ltj.java.engine;

import static com.ltj.java.engine.StaticGL.*;
import static com.jogamp.opengl.GL3.*;


import com.ltj.java.utils.JoglBufferHelper;
import com.ltj.shared.engine.AbstractParticleEmitter;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

public class JoglParticleEmitter extends AbstractParticleEmitter {
	
	
	public JoglParticleEmitter(int maxParticleCount,int runningtime, float red, float green, float blue) {
		super(maxParticleCount,runningtime, red,green,blue);
		
		
		
		//uniforms
		uMatrixLocation = glGetUniformLocation(JoglRenderer.particleProgramId, U_MATRIX);
		uCurrentTimeLocation = glGetUniformLocation(JoglRenderer.particleProgramId, U_TIME);
		uColorLocation = glGetUniformLocation(JoglRenderer.particleProgramId, U_COLOR);
		uPositionLocation = glGetUniformLocation(JoglRenderer.particleProgramId, U_POSITION);
		
		//attributes
		aDirectionVectorLocation = glGetAttribLocation(JoglRenderer.particleProgramId, A_DIRECTION);
		aParticleStartTimeLocation = glGetAttribLocation(JoglRenderer.particleProgramId, A_START_TIME);

		emitterVBO = JoglBufferHelper.arrayToBufferId( particleDirections);
		timeVBO = JoglBufferHelper.arrayToBufferId( particleStartTimes);
		
		//set color uniform
		glUseProgram(JoglRenderer.particleProgramId);
		glUniform3f(uColorLocation, red, green, blue);
		glUseProgram(JoglRenderer.programId);
	}
	
	
	public void render(){
		long current = System.currentTimeMillis() - globalStartTime ;
		if (current  <  getRunningTime()){
			
			glEnableVertexAttribArray(aDirectionVectorLocation);
			glEnableVertexAttribArray(aParticleStartTimeLocation);
			
			glUniform1f(uCurrentTimeLocation,  current);
			//set position uniform
			
			glUniform3fv(uPositionLocation, 3, getPosition(), 0);
			
			
			float[] mMVPMatrix = new float[16]; 
			MatrixHelper.multiplyMM(mMVPMatrix, Camera.getProjectionViewMatrix(), getModelMatrix());
			glUniformMatrix4fv(uMatrixLocation, 1, false, mMVPMatrix, 0);
			
			glBindBuffer(GL_ARRAY_BUFFER, emitterVBO[0]);
			glVertexAttribPointer(aDirectionVectorLocation, 3, GL_FLOAT,false, 0, 0);
			
			glBindBuffer(GL_ARRAY_BUFFER, timeVBO[0]);
			glVertexAttribPointer(aParticleStartTimeLocation,1,GL_FLOAT,false, 0, 0);
			
			glBindBuffer(GL_ARRAY_BUFFER, 0);
			
			//draw particles
			glDrawArrays(GL_POINTS, 0, currentParticleCount);
		} 
	}

	@Override
	public void recalculateVBOs() {
		emitterVBO = JoglBufferHelper.arrayToBufferId( particleDirections);
		timeVBO = JoglBufferHelper.arrayToBufferId( particleStartTimes);
	}
}
