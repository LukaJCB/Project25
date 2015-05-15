package com.ltj.shared.engine;


import com.ltj.shared.utils.MatrixHelper;

public abstract class Camera {

	private Camera() {}
	private static float[] viewMatrix = new float[16];
	private static float[] projectionMatrix = new float[16];
	private static float[] projectionViewMatrix = new float[16];
	private static float sevenY,sevenZ;
	
	
	
	public static void surfaceChanged(int height, int width){
		MatrixHelper.perspectiveM(projectionMatrix, 60, (float) width / (float) height, 0.1f, 100f);
	}
	public static float[] getProjectionViewMatrix() {
		return projectionViewMatrix;
	}
	public static void calcPVMatrix(){
		// Our MViewProjection : multiplication of our 2 matrices
		MatrixHelper.multiplyMM(projectionViewMatrix,projectionMatrix,viewMatrix);
	}
	
	public static void setLookAt(float x, float y){
		// Camera matrix
		MatrixHelper.setLookAtM(viewMatrix, 
				x,y-sevenY,2-sevenZ, 
				x,y,0, 
				0,1,0); 
	}
	
	public static void setRotateAround(float x, float y,float rotation){
		float[] campos = new float[4];
		campos[3] = 1;
		float[] rotationMatrix = new float[16];
		//Rotate around origin.
		MatrixHelper.setRotateM(rotationMatrix,rotation, 0,0,1);
		MatrixHelper.translateM(rotationMatrix,  0, -sevenY,0);
		float[] newCampos = new float[4];
		//multiply with the matrix; 
		MatrixHelper.multiplyMV(newCampos, rotationMatrix, campos);
		
		//translate to position of lookat-target
		newCampos[0] += x;
		newCampos[1] += y;
		//set camera height
		newCampos[2] = 2-sevenZ;
		
		
		MatrixHelper.setLookAtM(viewMatrix, 
				newCampos[0],newCampos[1],newCampos[2], 
				x,y,0, 
				0,0,1); 
	}
	
	public static void setModeSeven() {
		sevenY = 1.5f;
		sevenZ = 1;
	}
	
	public static void setNormalMode(){
		sevenY = 0;
		sevenZ = 0;
	}
	
	
}
