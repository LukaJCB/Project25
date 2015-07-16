package com.ltj.android.utils;

import static android.opengl.GLES20.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.ltj.android.engine.AndroidRenderer;
import com.ltj.shared.engine.RenderObject;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES11Ext;
import android.opengl.GLUtils;

public class AndroidTextureHelper {
	private static int texCount;
	private static HashMap<String, int[]> textureMap = new HashMap<String,int[]>();
	private static HashMap<String, Bitmap> dataMap = new HashMap<String, Bitmap>();
	private AndroidTextureHelper(){
		
	}
	
	public static int[] loadTexture(Context context,String path){
		if (textureMap.containsKey(path)){
			textureMap.get(path)[2]++;
			return textureMap.get(path);
		} 

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;   // No pre-scaling


		// Read in the resource
		Bitmap bitmap = getBitmapFromAsset(context, path);

		
	 
		/*
		 * This array will hold: 
		 * 1. the textureObjectHandle.
		 * 2. the unique value of the hashmap.
		 * 3. the number of times the texture has been loaded.
		 */
		int[] textureHandle = new int[3];
		textureHandle[1] = texCount;
		textureHandle[2] = 1;
		
		glGenTextures(1, textureHandle, 0);


		// Bind to the texture in OpenGL
		glBindTexture(GL_TEXTURE_2D, textureHandle[0]);

		// Load the bitmap into the bound texture.
		GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

		//Generate mipmaps
		glGenerateMipmap(GL_TEXTURE_2D);
		// Set filtering
		glTexParameteri(GL_TEXTURE_2D, GLES11Ext.GL_TEXTURE_MAX_ANISOTROPY_EXT, 16);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		// Recycle the bitmap, since its data has been loaded into OpenGL.
		bitmap.recycle();

		textureMap.put(path, textureHandle);

		//next texture
		texCount++;
		
	    
	    return textureHandle;
	}
	public static void loadTextureAsync(final String path, final RenderObject obj) {
		if (textureMap.containsKey(path)){
			textureMap.get(path)[2]++;
			obj.setLoaded(true);
			return;
		} 
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				final BitmapFactory.Options options = new BitmapFactory.Options();
				options.inScaled = false;   // No pre-scaling


				// Read in the resource
				Bitmap bitmap = getBitmapFromAsset(AndroidRenderer.context, path);
				dataMap.put(path,bitmap);
				obj.setLoaded(true);
			}
		}).start();
		
	}

	public static int[] finishLoading(String path) {
		if (textureMap.containsKey(path)){
			textureMap.get(path)[2]++;
			return textureMap.get(path);
		} 
		

		/*
		 * This array will hold: 
		 * 1. the textureObjectHandle.
		 * 2. the unique value of the hashmap.
		 * 3. the number of times the texture has been loaded.
		 */
		int[] textureHandle = new int[3];
		textureHandle[1] = texCount;
		textureHandle[2] = 1;
		
		glGenTextures(1, textureHandle, 0);


		// Bind to the texture in OpenGL
		glBindTexture(GL_TEXTURE_2D, textureHandle[0]);

		// Load the bitmap into the bound texture.
		GLUtils.texImage2D(GL_TEXTURE_2D, 0, dataMap.get(path), 0);

		//Generate mipmaps
		glGenerateMipmap(GL_TEXTURE_2D);
		// Set filtering
		glTexParameteri(GL_TEXTURE_2D, GLES11Ext.GL_TEXTURE_MAX_ANISOTROPY_EXT, 16);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		// Recycle the bitmap, since its data has been loaded into OpenGL.
		dataMap.remove(path).recycle();

		textureMap.put(path, textureHandle);

		//next texture
		texCount++;
		
	    
	    return textureHandle;
	}

	public static void deleteTexture(String path) {
		int[] arr = textureMap.get(path);
		arr[2]--;
		if (arr[2] == 0){
			glDeleteTextures(1, arr, 0);
			textureMap.remove(path);
		}
		
	}

	private static Bitmap getBitmapFromAsset(Context context, String filePath) {
	    AssetManager assetManager = context.getAssets();
	
	    InputStream istream;
	    Bitmap bitmap = null;
	    try {
	        istream = assetManager.open(filePath);
	        bitmap = BitmapFactory.decodeStream(istream);
	    } catch (IOException e) {
	        // handle exception
	    }
	
	    return bitmap;
	}
}
