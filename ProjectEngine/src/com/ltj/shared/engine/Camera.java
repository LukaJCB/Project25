package com.ltj.shared.engine;


import com.ltj.shared.utils.MatrixHelper;

public abstract class Camera {

	private Camera() {}
	private static float[] viewMatrix = new float[16];
	private static float[] projectionMatrix = new float[16];
	private static float[] projectionViewMatrix = new float[16];
	private static float sevenY,sevenZ;
	private static float[] eyePos = new float[4];
	private static Skybox skybox;
	private static boolean activeSkybox;
	
	
	public static void addSkyBox(Skybox box){
		skybox = box;
		activeSkybox = true;
	}
	
	public static boolean activeSkybox(){
		return activeSkybox;
	}
	
	public static float[] getEyePos() {
		return eyePos;
	}
	public static void createPerspective(int height, int width){
		eyePos[2] = 2;
		MatrixHelper.perspectiveM(projectionMatrix, 60, (float) width / (float) height, 0.1f, 100f);
	}
	public static void createOrthographic(int height, int width){
		eyePos[2] = 2;
		MatrixHelper.orthoM(projectionMatrix, 0, width, 0,height, 0.1f, 100f);
	}
	public static float[] getProjectionViewMatrix() {
		return projectionViewMatrix;
	}
	public static void calcPVMatrix(){
		// Our ViewProjection : multiplication of our 2 matrices
		MatrixHelper.multiplyMM(projectionViewMatrix,projectionMatrix,viewMatrix);
	}
	
	public static void setLookAt(float x, float y){
		eyePos[0] = x;
		eyePos[1] = y-sevenY;
		
		// Camera matrix
		MatrixHelper.setLookAtM(viewMatrix, 
				eyePos[0],eyePos[1],eyePos[2]-sevenZ, 
				x,y,0, 
				0,1,0); 
	}
	
	public static void setRotateAround(float x, float y,float rotation){
		float[] camPos = new float[4];
		camPos[3] = 1;
		float[] rotationMatrix = new float[16];
		//Rotate around origin.
		MatrixHelper.setRotateM(rotationMatrix,rotation, 0,0,1);
		MatrixHelper.translateM(rotationMatrix,  0, -sevenY,0);
		//multiply with the matrix; 
		MatrixHelper.multiplyMV(eyePos, rotationMatrix, camPos);
		
		//translate to position of lookat-target
		eyePos[0] += x;
		eyePos[1] += y;
		//set camera height
		eyePos[2] = 2-sevenZ;
		
		
		MatrixHelper.setLookAtM(viewMatrix, 
				eyePos[0],eyePos[1],eyePos[2], 
				x,y,0, 
				0,0,1); 
	}
	
	public static void setModeSeven() {
		sevenY = 1.5f;
		sevenZ = 1.5f;
	}
	
	public static void setNormalMode(){
		sevenY = 0;
		sevenZ = 0;
	}

	public static void renderSkybox() {
		skybox.render(eyePos[0], eyePos[1]);
		
	}

	public static void flush() {
		if (activeSkybox){
			skybox.clear();
			skybox = null;
			activeSkybox = false;
		}
		
		
	}
	
	
}
