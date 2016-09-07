package com.ltj.android.utils;


import static android.opengl.GLES20.GL_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_STATIC_DRAW;
import static android.opengl.GLES20.glBindBuffer;
import static android.opengl.GLES20.glBufferData;
import static android.opengl.GLES20.glGenBuffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class AndroidBufferHelper {
	private AndroidBufferHelper(){}
	
	public static FloatBuffer arrayToBuffer(float[] arr){
		FloatBuffer buffer = ByteBuffer.allocateDirect(arr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
		buffer.put(arr).position(0);
		return buffer;
	}
	
	public static int[] arrayToBufferId(float[] arr){

		int[] bufferId = new int[1];
		// let's generate
		glGenBuffers(1, bufferId, 0);


		FloatBuffer fb = arrayToBuffer(arr);
		// bind the two buffer
		glBindBuffer(GL_ARRAY_BUFFER, bufferId[0]);
		glBufferData(GL_ARRAY_BUFFER, fb.capacity()* 4,fb, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		return bufferId;
	}
}
