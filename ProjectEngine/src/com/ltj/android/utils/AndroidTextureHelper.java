package com.ltj.android.utils;

import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_LINEAR_MIPMAP_LINEAR;
import static android.opengl.GLES20.GL_NEAREST;
import static android.opengl.GLES20.GL_REPEAT;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_S;
import static android.opengl.GLES20.GL_TEXTURE_WRAP_T;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glGenerateMipmap;
import static android.opengl.GLES20.glTexParameteri;
import static com.ltj.java.engine.StaticGL.glDeleteTextures;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES11Ext;
import android.opengl.GLUtils;

public class AndroidTextureHelper {
	private static int texCount;
	private static HashMap<String, int[]> textureMap = new HashMap<String,int[]>();
	private AndroidTextureHelper(){
		
	}
	
	public static int loadTexture(Context context, int resId){
		
		
		
		final int[] textureHandle = new int[1];
		 
	    glGenTextures(1, textureHandle, 0);
	 
	    if (textureHandle[0] != 0)
	    {
	        final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inScaled = false;   // No pre-scaling
	 
	        // Read in the resource
	        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId, options);
	        
	        // Bind to the texture in OpenGL
	        glBindTexture(GL_TEXTURE_2D, textureHandle[0]);
	 
	        // Set filtering
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	 
	        // Load the bitmap into the bound texture.
	        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
	 
	        // Recycle the bitmap, since its data has been loaded into OpenGL.
	        bitmap.recycle();
	    }
	 
	    if (textureHandle[0] == 0)
	    {
	        throw new RuntimeException("Error loading texture.");
	    }
	 
	    return textureHandle[0];
		
		
		
	}
	
	public static int[] loadTexture(Context context,String path){
		if (textureMap.containsKey(path)){
			return textureMap.get(path);
		}

		final int[] textureHandle = new int[2];

		textureHandle[1] = texCount;

		texCount++;

		glGenTextures(1, textureHandle, 0);

		if (textureHandle[0] != 0)
		{
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inScaled = false;   // No pre-scaling


			// Read in the resource
			Bitmap bitmap = getBitmapFromAsset(context, path);

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
	    }
	 
	    if (textureHandle[0] == 0)
	    {
	        throw new RuntimeException("Error loading texture.");
	    }
	 
	    textureMap.put(path, textureHandle);
	    
	    return textureHandle;
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

	public static void deleteTexture(String path) {
		int[] arr = textureMap.get(path);
		arr[2]--;
		if (arr[2] == 0){
			glDeleteTextures(1, arr, 0);
			textureMap.remove(path);
		}
		
	}
}
