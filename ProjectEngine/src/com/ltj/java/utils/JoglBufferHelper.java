package com.ltj.java.utils;

import static com.jogamp.opengl.GL.GL_ARRAY_BUFFER;
import static com.jogamp.opengl.GL.GL_STATIC_DRAW;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.jogamp.opengl.GL3;

public class JoglBufferHelper {
	private JoglBufferHelper(){}
	
	public static FloatBuffer arrayToBuffer(float[] arr){
		FloatBuffer buffer = ByteBuffer.allocateDirect(arr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(arr).position(0);
		return buffer;
	}
	
	public static int[] arrayToBufferId(GL3 gl,float[] arr){
		// allocate an array of one element
		int[] idArray = new int[1];
		// let's generate
		gl.glGenVertexArrays(1, idArray, 0);
		int triangleVAO = idArray[0];
		// create the buffer and link the data with the location inside the
		gl.glBindVertexArray(triangleVAO);
		
		
		
		int[] bufferId = new int[1];
		// let's generate
		gl.glGenBuffers(1, bufferId, 0);


		// bind the buffer
		gl.glBindBuffer(GL_ARRAY_BUFFER,bufferId[0]);
		gl.glBufferData(GL_ARRAY_BUFFER, arr.length * 4,arrayToBuffer(arr), GL_STATIC_DRAW);
		gl.glBindBuffer(GL_ARRAY_BUFFER, 0);
		return bufferId;
	}
}
