package com.ltj.shared.engine;
import static android.opengl.GLES20.*;

public abstract class AbstractParticleEmitter {
	protected static final String A_POSITION = "aPosition";
	protected static final String A_DIRECTION = "aDirection";
	protected static final String A_START_TIME = "aParticleStartTime";
	
	protected static final String U_TIME = "uTime";
	protected static final String U_MATRIX = "uMatrix";
	protected static final String U_COLOR = "uColor";
	
	protected int uMatrixLocation;
	protected int uTimeLocation;
	protected int uColorLocation;
	
	protected int aPositionLocation;
	protected int aDirectionVectorLocation;
	protected int aParticleStartTimeLocation;
	
	
	public AbstractParticleEmitter(int program){
		//uniforms
		uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
		uTimeLocation = glGetUniformLocation(program, U_TIME);
		uColorLocation = glGetAttribLocation(program, U_COLOR);
		
		//attributes
		aPositionLocation = glGetAttribLocation(program, A_POSITION);
		aDirectionVectorLocation = glGetAttribLocation(program, A_DIRECTION);
		aParticleStartTimeLocation = glGetAttribLocation(program, A_START_TIME);
	}
}
