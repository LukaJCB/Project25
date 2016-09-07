package com.ltj.android.utils;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;
import android.util.Log;

public class AndroidShaderHelper {
	
	
	private static final String TAG = "ShaderHelper";
	
	private AndroidShaderHelper(){}
	
	private static int compileShader(int type, String shaderCode){
		
		//create shader
		final int shaderObjectId = glCreateShader(type);
		
		if (shaderObjectId != 0){
			//fill shader with the src code
			glShaderSource(shaderObjectId, shaderCode);
			
			//compile
			glCompileShader(shaderObjectId);
			
			//check for success
			final int[] status = new int[1];
			glGetShaderiv(shaderObjectId,GL_COMPILE_STATUS,status,0);
			
			
			
			
			if (status[0] != 0){
				//compilation succesfull
				return shaderObjectId;
			} else {
				//compilation failed
				glDeleteShader(shaderObjectId);
				Log.w(TAG, "compilation failed" + type);
				
				return 0;
			}
			
		
			
		} else {
			//creation failed
			Log.w(TAG, "Couldn't create shader.");
			
			return 0;
		}
		
	}
	
	public static int compileVertexShader(String shaderCode){
		return compileShader(GL_VERTEX_SHADER, shaderCode);
	}
	
	public static int compileFragmentShader(String shaderCode){
		return compileShader(GL_FRAGMENT_SHADER, shaderCode);
	}
	
	public static int linkProgram(int vertexId, int fragmentId){
		int programObjectId = glCreateProgram();
		
		if (programObjectId != 0){
			//creation succesfull
			glAttachShader(programObjectId, vertexId);
			glAttachShader(programObjectId,fragmentId);
			
			//link
			glLinkProgram(programObjectId);
			
			//retrieve status
			final int[] status = new int[1];
			glGetProgramiv(programObjectId, GL_LINK_STATUS, status, 0);
			
			
			
			if (status[0] != 0){
				
				return programObjectId;
			} else {
				//linking failed
				glDeleteProgram(programObjectId);
				Log.w(TAG, "linking failed");
				
				return 0;
			}
		} else {
			Log.w(TAG, "couldn't create program");
			return 0;
		}
	}
	
	public static boolean programValidated(int programId){
		glValidateProgram(programId);
		
		final int[] status = new int[1];
		glGetProgramiv(programId, GL_VALIDATE_STATUS, status,0);
		

		Log.v(TAG, "Validated: " + glGetProgramInfoLog(programId)+ "end");
		
		
		return status[0] != 0;
		
	}
	
}
