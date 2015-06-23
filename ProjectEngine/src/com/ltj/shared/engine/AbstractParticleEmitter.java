package com.ltj.shared.engine;

import com.ltj.shared.utils.MatrixHelper;


public abstract class AbstractParticleEmitter implements ParticleEmitter {
	protected static final String A_DIRECTION = "aDirection";
	protected static final String A_START_TIME = "aParticleStartTime";
	
	protected static final String U_POSITION = "uPosition";
	protected static final String U_TIME = "uCurrentTime";
	protected static final String U_MATRIX = "uMatrix";
	protected static final String U_COLOR = "uColor";
	
	protected int uMatrixLocation;
	protected int uCurrentTimeLocation;
	protected int uPositionLocation;
	protected int uColorLocation;
	
	protected int aDirectionVectorLocation;
	protected int aParticleStartTimeLocation;
	

	protected static final int POSITION_COMPONENT_COUNT = 3;
	protected final float[] particleDirections, particleStartTimes;
	protected int currentParticleCount;
	protected long globalStartTime;
	protected int[] emitterVBO, timeVBO;
	
	private final int maxParticleCount;
	private int nextParticle;
	
	private float[] modelMatrix = new float[16];
	private float x,y,z;
	
	public AbstractParticleEmitter(int maxParticleCount, float red, float green, float blue){
		particleDirections = new float[maxParticleCount * POSITION_COMPONENT_COUNT];
		particleStartTimes = new float[maxParticleCount];
		this.maxParticleCount = maxParticleCount;
		
		//set Matrix
		MatrixHelper.setIdentityM(modelMatrix);

		x = y = z = 0;
		
		globalStartTime = System.currentTimeMillis();
	}
	
	
	public int getMaxParticleCount() {
		return maxParticleCount;
	}


	public void addParticles(float dx, float dy, float dz, float particleStartTime, int count){
		for (int i = 0; i < count; i++) {
			addParticle(dx,dy*(i%55)*0.3f,dz, particleStartTime);
		}
	}


	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		calcMatrix();
	}
	
	public void translate(float x, float y, float z){
		this.x += x;
		this.y += y;
		this.z += z;
		calcMatrix();
	}
	
	public float getX() {
		return x;
	}


	public float getY() {
		return y;
	}


	public float getZ() {
		return z;
	}


	private void addParticle(float dx, float dy, float dz, float particleStartTime) {
		final int particleOffset = nextParticle * POSITION_COMPONENT_COUNT;
		int currentOffset = particleOffset;
		if (currentParticleCount < maxParticleCount) {
			currentParticleCount++;
		}
		particleDirections[currentOffset++] = dx;
		particleDirections[currentOffset++] = dy;
		particleDirections[currentOffset++] = dz;
		particleStartTimes[nextParticle++] = particleStartTime;
		
		if (nextParticle == maxParticleCount) {
			//Start over
			nextParticle = 0;
		}
	}


	private void calcMatrix(){
		MatrixHelper.setIdentityM(getModelMatrix());
		MatrixHelper.translateM(getModelMatrix(), x,y,z);
	}
	
	protected float[] getModelMatrix() {
		return modelMatrix;
	}
	
}
