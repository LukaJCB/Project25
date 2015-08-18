package com.ltj.shared.engine;


import com.ltj.shared.utils.MatrixHelper;


public abstract class Camera {

	private Camera() {}
	private static float[] viewMatrix = new float[16];
	private static float[] perspectiveProjectionMatrix = new float[16];
	private static float[] orthoProjectionMatrix = new float[16];
	private static float[] projectionViewMatrix = new float[16];
	private static float[] invertedProjectionViewMatrix = new float[16];
	private static float sevenY,sevenZ;
	private static float[] eyePos = new float[4];
	private static float[] lookAt = new float[2];
	private static Skybox skybox;
	private static boolean modeSeven;
	
	
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
		calcDistanceParams();
		calcViewMatrix();
	}
	
	public static void zoom(float zoomFactor){
		eyePos[2] /= zoomFactor;
		calcDistanceParams();
		calcViewMatrix();
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
		
		calcViewMatrix();
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
		modeSeven = true;
		calcDistanceParams();
		calcViewMatrix();
	}
	
	public static void setNormalMode(){
		modeSeven = false;
		calcDistanceParams();
		calcViewMatrix();
	}

	private static void calcViewMatrix(){
		
		MatrixHelper.setLookAtM(viewMatrix, 
				eyePos[0],eyePos[1]-sevenY,eyePos[2]-sevenZ, 
				lookAt[0],lookAt[1],0, 
				0,1,0); 
	}
	
	private static void calcDistanceParams(){
		if (modeSeven){
			sevenY = 0.75f*eyePos[2];
			sevenZ = 0.75f*eyePos[2];
		} else {
			sevenY = 0;
			sevenZ = 0;
		}
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
	
	public static void calcInvertedMatrix(){
		MatrixHelper.invertM(invertedProjectionViewMatrix,projectionViewMatrix);
	}
	
	public static float[] getInvertedProjectionViewMatrix(){
		return invertedProjectionViewMatrix;
	}
	
	public static Line normalized2DCoordsToLine(float x, float y){
		
		final float[] nearPoint = {x, y, -1, 1};
		final float[] farPoint = {x, y, 1, 1};
		
		final float[] nearPointWorld = new float[4];
		final float[] farPointWorld = new float[4];
		
		MatrixHelper.multiplyMV(nearPointWorld, invertedProjectionViewMatrix, nearPoint);
		MatrixHelper.multiplyMV(farPointWorld, invertedProjectionViewMatrix, farPoint);
		
		perspectiveDevide(nearPointWorld);
		perspectiveDevide(farPointWorld);
		
		return new Line(nearPointWorld,farPointWorld);
	}
	
	private static void perspectiveDevide(float[] vector) {
		vector[0] /= vector[3];
		vector[1] /= vector[3];
		vector[2] /= vector[3];
		
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
