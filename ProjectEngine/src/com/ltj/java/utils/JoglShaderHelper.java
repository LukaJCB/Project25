package com.ltj.java.utils;

import static com.jogamp.opengl.GL3.*;


import com.jogamp.opengl.GL3;


public class JoglShaderHelper {
	
	
	
	private JoglShaderHelper(){}
	
	private static int compileShader(GL3 gl,int type, String shaderCode){
		
		//create shader
		final int shaderObjectId = gl.glCreateShader(type);
		
		if (shaderObjectId != 0){
			String[] code = new String[1];
			code[0] = shaderCode;
			int[] length = new int[1];
			length[0] = shaderCode.length();
			//fill shader with the src code
			gl.glShaderSource(shaderObjectId,1, code,length, 0);
			
			//compile
			gl.glCompileShader(shaderObjectId);
			
			//check for success
			final int[] status = new int[1];
			gl.glGetShaderiv(shaderObjectId,GL_COMPILE_STATUS,status,0);
			
			
			
			
			if (status[0] != 0){
				//compilation succesfull
				return shaderObjectId;
			} else {
				//compilation failed
				System.err.println("Compilation failed. Type: " + type);
				gl.glDeleteShader(shaderObjectId);
				
				return 0;
			}
			
		
			
		} else {
			//creation failed
			return 0;
		}
		
	}
	
	public static int compileVertexShader(GL3 gl,String shaderCode){
		return compileShader(gl,GL_VERTEX_SHADER, shaderCode);
	}
	
	public static int compileFragmentShader(GL3 gl, String shaderCode){
		return compileShader(gl,GL_FRAGMENT_SHADER, shaderCode);
	}
	
	public static int linkProgram(GL3 gl,int vertexId, int fragmentId){
		int programObjectId = gl.glCreateProgram();
		
		if (programObjectId != 0){
			//creation succesfull
			gl.glAttachShader(programObjectId, vertexId);
			gl.glAttachShader(programObjectId,fragmentId);
			
			//link
			gl.glLinkProgram(programObjectId);
			
			//retrieve status
			final int[] status = new int[1];
			gl.glGetProgramiv(programObjectId, GL_LINK_STATUS, status, 0);
			
			
			
			if (status[0] != 0){
				
				return programObjectId;
			} else {
				//linking failed
				gl.glDeleteProgram(programObjectId);
				System.err.println("linking failed");
				
				return 0;
			}
		} else {
			System.err.println("couldn't create program");
			return 0;
		}
	}
	
	public static boolean programValidated(GL3 gl, int programId){
		gl.glValidateProgram(programId);
		
		final int[] status = new int[1];
		gl.glGetProgramiv(programId, GL_VALIDATE_STATUS, status,0);
		

		
		
		return status[0] != 0;
		
	}
	
}
