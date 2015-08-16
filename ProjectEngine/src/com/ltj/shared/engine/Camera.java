package com.ltj.shared.engine;


import com.ltj.shared.utils.MatrixHelper;

public abstract class Camera {

	private Camera() {}
	private static float[] viewMatrix = new float[16];
	private static float[] perspectiveProjectionMatrix = new float[16];
	private static float[] orthoProjectionMatrix = new float[16];
	private static float[] projectionViewMatrix = new float[16];
	private static float sevenY,sevenZ;
	private static float[] eyePos = new float[4];
	private static float[] lookAt = new float[2];
	private static Skybox skybox;
	
	
	public static void addSkyBox(Skybox box){
		skybox = box;
	}
	
	public static boolean activeSkybox(){
		return skybox != null;
	}
	
	public static float[] getEyePos() {
		return eyePos;
	}
	public static void createPerspective(int height, int width){
		MatrixHelper.perspectiveM(perspectiveProjectionMatrix, 60, (float) width / (float) height, 0.1f, 100f);
	}
	
	public static void setDistance(float distance){
		eyePos[2] = distance;
	}
	
	public static void zoom(float zoomFactor){
		eyePos[2] /= zoomFactor;
	}
	
	public static void createOrthographic(int height, int width){
		MatrixHelper.orthoM(orthoProjectionMatrix, 0, width, height,0, -1f, 100f);
	}
	
	public static float[] getOrthoProjectionMatrix(){
		return orthoProjectionMatrix;
	}
	public static float[] getProjectionViewMatrix() {
		return projectionViewMatrix;
	}
	public static void calcPVMatrix(){
		// Our ViewProjection : multiplication of our 2 matrices
		MatrixHelper.multiplyMM(projectionViewMatrix,perspectiveProjectionMatrix,viewMatrix);
	}
	
	public static void setLookAt(float x, float y){
		lookAt[0] = x;
		lookAt[1] = y;
		
		eyePos[0] = x;
		eyePos[1] = y;
		
		calcMatrix();
	}
	
	public static void setRotateAround(float x, float y,float rotation){
		lookAt[0] = x;
		lookAt[1] = y;
		
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
	
	public static float[] getLookAt() {
		return lookAt;
	}

	public static void setModeSeven() {
		sevenY = 1.5f;
		sevenZ = 1.5f;
		calcMatrix();
	}
	
	private static void calcMatrix(){
		MatrixHelper.setLookAtM(viewMatrix, 
				eyePos[0],eyePos[1]-sevenY,eyePos[2]-sevenZ, 
				lookAt[0],lookAt[1],0, 
				0,1,0); 
	}
	
	public static void setNormalMode(){
		sevenY = 0;
		sevenZ = 0;
		calcMatrix();
	}

	public static void renderSkybox() {
		skybox.render(eyePos[0], eyePos[1]);
		
	}

	public static void flush() {
		if (skybox != null){
			skybox.clear();
			skybox = null;
		}
		
		
	}

	public static float[] getPerspectiveProjectionMatrix() {
		
		return perspectiveProjectionMatrix;
	}
	
	public static String toJSON(){
		String json = "{ \"distance\":" + eyePos[2]+ ",\"x\":" + lookAt[0] + ",\"y\":" + lookAt[1] + ",\"skybox\":";
		if (skybox != null){
			json += skybox.toJSON();
		} else {
			json += "\"null\"";
		}
		json += "}";
		return json;
		
	}
	
	
}
