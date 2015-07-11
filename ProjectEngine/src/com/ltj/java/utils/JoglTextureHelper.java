package com.ltj.java.utils;

import static com.jogamp.opengl.GL.GL_LINEAR;
import static com.jogamp.opengl.GL.GL_LINEAR_MIPMAP_LINEAR;
import static com.jogamp.opengl.GL.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT;
import static com.jogamp.opengl.GL.GL_REPEAT;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAX_ANISOTROPY_EXT;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_WRAP_S;
import static com.jogamp.opengl.GL.GL_TEXTURE_WRAP_T;
import static com.ltj.java.engine.StaticGL.glBindTexture;
import static com.ltj.java.engine.StaticGL.glDeleteTextures;
import static com.ltj.java.engine.StaticGL.glGenerateMipmap;
import static com.ltj.java.engine.StaticGL.glGetFloatv;
import static com.ltj.java.engine.StaticGL.glTexParameterf;
import static com.ltj.java.engine.StaticGL.glTexParameteri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;


public class JoglTextureHelper {
	
	private static int texCount;
	private static HashMap<String, int[]> textureMap = new HashMap<String,int[]>();
	private JoglTextureHelper(){
		
	}
	
	public static int[] loadTexture(String file) throws IOException {
		
		if (textureMap.containsKey(file)){
			textureMap.get(file)[2]++;
			return textureMap.get(file);
		} 
		
	   
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(ImageIO.read(new File(file)), "png", os);
	    InputStream fis = new ByteArrayInputStream(os.toByteArray());
	    
	    Texture tex = TextureIO.newTexture(fis, false, TextureIO.PNG);
	    
	    glBindTexture(GL_TEXTURE_2D, tex.getTextureObject());
	    glGenerateMipmap(GL_TEXTURE_2D);
	    
	    float[] aniso = new float[1];
	    glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, aniso, 0);
	    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, aniso[0]);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	    
	    /*
		 * This array will hold: 
		 * 1. the textureObjectHandle.
		 * 2. the unique value of the hashmap.
		 * 3. the number of times the texture has been loaded.
		 */
		int[] textureHandle = new int[3];
		textureHandle[0] = tex.getTextureObject();
		textureHandle[1] = texCount;
		textureHandle[2] = 1;
		
		textureMap.put(file, textureHandle);
		
		//next texture
		texCount++;
		
	    return textureHandle;
	}
	
	public static int[] loadTextureAsync(final String path){
		if (textureMap.containsKey(path)){
			textureMap.get(path)[2]++;
			return textureMap.get(path);
		} 
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
			    try {
					ImageIO.write(ImageIO.read(new File(path)), "png", os);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    InputStream fis = new ByteArrayInputStream(os.toByteArray());
			    
			    fis.toString();
				
			}
		}).start();
		return null;
	}
	
	public static void deleteTexture(String path){
		int[] arr = textureMap.get(path);
		arr[2]--;
		if (arr[2] == 0){
			glDeleteTextures(1, arr, 0);
			textureMap.remove(path);
		}
	}
	
	
}
