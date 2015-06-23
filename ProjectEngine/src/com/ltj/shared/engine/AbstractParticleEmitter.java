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
	protected static final int TOTAL_COMPONENT_COUNT = POSITION_COMPONENT_COUNT + 1;
	protected final float[] particles;
	protected int currentParticleCount;
	protected long globalStartTime;
	protected int[] emitterVBO;
	private final int maxParticleCount;
	private int nextParticle;
	
	private float[] modelMatrix = new float[16];
	private float x,y,z;
	
	public AbstractParticleEmitter(int maxParticleCount, float red, float green, float blue){
		particles = new float[maxParticleCount * TOTAL_COMPONENT_COUNT];
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
			addParticle(dx,dy*(i%15)*0.3f,dz, particleStartTime);
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
		final int particleOffset = nextParticle * TOTAL_COMPONENT_COUNT;
		int currentOffset = particleOffset;
		nextParticle++;
		if (currentParticleCount < maxParticleCount) {
			currentParticleCount++;
		}
		if (nextParticle == maxParticleCount) {
			//Start over
			nextParticle = 0;
		}
		particles[currentOffset++] = dx;
		particles[currentOffset++] = dy;
		particles[currentOffset++] = dz;
		particles[currentOffset++] = particleStartTime;
	}


	private void calcMatrix(){
		MatrixHelper.setIdentityM(getModelMatrix());
		MatrixHelper.translateM(getModelMatrix(), x,y,z);
	}
	
	protected float[] getModelMatrix() {
		return modelMatrix;
	}
	
}
