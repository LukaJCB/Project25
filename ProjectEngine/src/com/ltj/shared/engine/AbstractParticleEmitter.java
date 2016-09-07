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
	private int runningTime;
	
	private float[] modelMatrix = new float[16];
	private float[] position = new float[3];
	private float[] colors;
	private int id;
	
	
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}


	public AbstractParticleEmitter(int maxParticleCount,int runningtime, float red, float green, float blue){
		particleDirections = new float[maxParticleCount * POSITION_COMPONENT_COUNT];
		particleStartTimes = new float[maxParticleCount];
		this.maxParticleCount = maxParticleCount;
		
		//set Matrix
		MatrixHelper.setIdentityM(modelMatrix);

		runningTime = runningtime;
		globalStartTime = System.currentTimeMillis();
		
		colors = new float[3];
		colors[0] = red;
		colors[1] = green;
		colors[2] = blue;
	}
	
	
	public int getMaxParticleCount() {
		return maxParticleCount;
	}


	@Override
	public void addParticleExplosion(int count, float speed){
		globalStartTime = System.currentTimeMillis();
		float rot = (float) (2 * Math.PI /count);
		for (int i = 0; i < count;i++){
			addParticle((float)Math.sin(i*rot)*speed, (float)Math.cos(i* rot)*speed, 0, System.currentTimeMillis() -globalStartTime);
		}
		recalculateVBOs();
	}
	
	public void addParticles(float dx, float dy, float dz, float particleStartTime, int count){
		for (int i = 0; i < count; i++) {
			addParticle(dx,dy,dz, particleStartTime -globalStartTime);
		}
		recalculateVBOs();
	}


	@Override
	public void setPosition(float x, float y, float z) {
		position[0] = x;
		position[1] = y;
		position[2] = z;
		calcMatrix();
	}
	
	@Override
	public void translate(float x, float y, float z){
		position[0] += x;
		position[1] += y;
		position[2] += z;
		calcMatrix();
	}
	
	public float getX() {
		return position[0];
	}


	public float getY() {
		return position[1];
	}


	public float getZ() {
		return position[2];
	}
	
	public float[] getPosition(){
		return position;
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
		MatrixHelper.translateM(modelMatrix, position[0],position[1],position[2]);
		
	}
	
	protected float[] getModelMatrix() {
		return modelMatrix;
	}


	public int getRunningTime() {
		return runningTime;
	}


	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	
	public String toJSON(){
		return "{\"type\":\""+ getClass().getName() + "\",\"x\":" + position[0] + ",\"y\":" + position[1]
				+ ",\"z\":" + position[2] + ",\"runningTime\":" + runningTime + ",\"maxParticles\":" + maxParticleCount
				+ ",\"red\":" + colors[0] + ",\"green\":" + colors[1] + ",\"blue\":" + colors[2] + "}";
	}
	
}
