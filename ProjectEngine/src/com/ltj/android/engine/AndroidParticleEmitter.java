package com.ltj.android.engine;

import static android.opengl.GLES20.*;

import com.ltj.android.utils.AndroidBufferHelper;
import com.ltj.shared.engine.AbstractParticleEmitter;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.utils.MatrixHelper;

public class AndroidParticleEmitter extends AbstractParticleEmitter {

	public AndroidParticleEmitter(int maxParticleCount, float red, float green, float blue) {
		super(maxParticleCount, red,green,blue);


		//uniforms
		uMatrixLocation = glGetUniformLocation(AndroidRenderer.particleProgramId, U_MATRIX);
		uCurrentTimeLocation = glGetUniformLocation(AndroidRenderer.particleProgramId, U_TIME);
		uColorLocation = glGetUniformLocation(AndroidRenderer.particleProgramId, U_COLOR);
		uPositionLocation = glGetUniformLocation(AndroidRenderer.particleProgramId, U_POSITION);

		//attributes
		aDirectionVectorLocation = glGetAttribLocation(AndroidRenderer.particleProgramId, A_DIRECTION);
		aParticleStartTimeLocation = glGetAttribLocation(AndroidRenderer.particleProgramId, A_START_TIME);

		recalculateVBOs();

		//set color uniform
		glUseProgram(AndroidRenderer.particleProgramId);
		glUniform3f(uColorLocation, red, green, blue);
		glUseProgram(AndroidRenderer.programId);
	}


	public void render(){

		glEnableVertexAttribArray(aDirectionVectorLocation);
		glEnableVertexAttribArray(aParticleStartTimeLocation);

		glUniform1f(uCurrentTimeLocation,  System.currentTimeMillis() - globalStartTime);
		//set position uniform
		glUniform3fv(uPositionLocation,3, getPosition(), 0);
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

	@Override
	public void recalculateVBOs() {
		emitterVBO = AndroidBufferHelper.arrayToBufferId( particleDirections);
		timeVBO = AndroidBufferHelper.arrayToBufferId(particleStartTimes);
	}
}
