package com.ltj.java.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import static com.jogamp.opengl.GL3.*;


public class JoglTextureHelper {
	
	private static int texCount;
	private static HashMap<String, int[]> textureMap = new HashMap<String,int[]>();
	private JoglTextureHelper(){
		
	}
	
	public static int[] loadTexture(GL3 gl,String file) throws IOException {
		
		if (textureMap.containsKey(file)){
			return textureMap.get(file);
		} 
		int[] textureHandle = new int[2];
		textureHandle[1] = texCount;
		texCount++;
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(ImageIO.read(new File(file)), "png", os);
	    InputStream fis = new ByteArrayInputStream(os.toByteArray());
	    Texture tex = TextureIO.newTexture(fis, false, TextureIO.PNG);
	    tex.bind(gl);
	    gl.glGenerateMipmap(GL_TEXTURE_2D);
	    
	    float[] aniso = new float[1];
	    gl.glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, aniso, 0);
	    gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, aniso[0]);
	    tex.setTexParameteri(gl, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
	    tex.setTexParameteri(gl, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    textureHandle[0] = tex.getTextureObject();
	    

		textureMap.put(file, textureHandle);
	    
	    return textureHandle;
	}
}
