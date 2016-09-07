package com.ltj.java.utils;

import static com.jogamp.opengl.GL.*;
import static com.ltj.java.engine.StaticGL.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;


import com.jogamp.opengl.GL;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import com.ltj.shared.engine.RenderObject;


public class JoglTextureHelper {
	
	private static int texCount;
	private static HashMap<String, int[]> textureMap = new HashMap<String,int[]>();
	private static HashMap<String, TextureData> dataMap = new HashMap<String,TextureData>();
	private JoglTextureHelper(){
		
	}
	
	static {
		 glAlphaColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
				 new int[] {8,8,8,8},
				 true,
				 false,
				 ComponentColorModel.TRANSLUCENT,
				 DataBuffer.TYPE_BYTE);

		 glColorModel = new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB),
				 new int[] {8,8,8,0},
				 false,
				 false,
				 ComponentColorModel.OPAQUE,
				 DataBuffer.TYPE_BYTE);
	}
	
	private static ColorModel glAlphaColorModel,glColorModel;
	 
	
	public static int[] loadTexture(String file) throws IOException {
		
		if (textureMap.containsKey(file)){
			textureMap.get(file)[2]++;
			return textureMap.get(file);
		} 
		
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(ImageIO.read(new File(file)), "png", os);
	    InputStream fis = new ByteArrayInputStream(os.toByteArray());
	    
	   
	    TextureData td = TextureIO.newTextureData(getGLProfile(), fis, false, TextureIO.PNG);
	   
	    Texture tex = TextureIO.newTexture(td);
	   
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
	    
	    
	    glBindTexture(GL_TEXTURE_2D, tex.getTextureObject());
	    glGenerateMipmap(GL_TEXTURE_2D);
	 
	    
	    float[] aniso = new float[1];
	    glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, aniso, 0);
	    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, aniso[0]);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	 
		
		textureMap.put(file, textureHandle);
		
		//next texture
		texCount++;
		
	    return textureHandle;
	}

	public static void deleteTexture(String path){
		int[] arr = textureMap.get(path);
		arr[2]--;
		if (arr[2] == 0){
			glDeleteTextures(1, arr, 0);
			textureMap.remove(path);
		}
	}

	public static void loadTextureAsync(final String path, final RenderObject obj){
		if (textureMap.containsKey(path)){
			textureMap.get(path)[2]++;
			obj.setLoaded(true);
			return;
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
			    
			    try {
					TextureData td = TextureIO.newTextureData(getGLProfile(), fis, false, TextureIO.PNG);
					dataMap.put(path, td);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    obj.setLoaded(true);
			}
		}).start();
	}
	
	public static int[] finishLoading(String path) {
		if (textureMap.containsKey(path)){
			textureMap.get(path)[2]++;
			return textureMap.get(path);
		} 
		
	    Texture tex = TextureIO.newTexture(dataMap.remove(path));
	
	    
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
		
		textureMap.put(path, textureHandle);
		
		//next texture
		texCount++;
	    return textureHandle;
		
	}

	public static int[] loadTextureBytes(String file) throws IOException {
		
		if (textureMap.containsKey(file)){
			textureMap.get(file)[2]++;
			return textureMap.get(file);
		} 
		
		BufferedImage srcImage = ImageIO.read(new File(file));
	
		int srcFormat;
		if (srcImage.getColorModel().hasAlpha()){
			srcFormat = GL.GL_BGRA;
		} else {
			srcFormat = GL.GL_BGR;
		}
		ByteBuffer buf = convertImageData(srcImage);
		
	    System.out.println("breh");
	    /*
		 * This array will hold: 
		 * 1. the textureObjectHandle.
		 * 2. the unique value of the hashmap.
		 * 3. the number of times the texture has been loaded.
		 */
		int[] textureHandle = new int[3];
	    glGenTextures(1, textureHandle, 0);
	    glBindTexture(GL_TEXTURE_2D, textureHandle[0]);
	    glTexImage2D(GL_TEXTURE_2D, 0, GL.GL_RGBA, srcImage.getWidth(), srcImage.getHeight(), 0, srcFormat, GL.GL_BYTE, buf);
	    
	    
	
	    glGenerateMipmap(GL_TEXTURE_2D);
	    
	    float[] aniso = new float[1];
	    glGetFloatv(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT, aniso, 0);
	    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, aniso[0]);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
	    
	    textureHandle[1] = texCount;
		textureHandle[2] = 1;
		
		textureMap.put(file, textureHandle);
		
		//next texture
		texCount++;
		
	    return textureHandle;
	}

	private static ByteBuffer convertImageData(BufferedImage bufferedImage) {
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;


		// for a texture
		if (bufferedImage.getColorModel().hasAlpha()) {
			raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,bufferedImage.getWidth(),bufferedImage.getHeight(),4,null);
			texImage = new BufferedImage(glAlphaColorModel,raster,false,new Hashtable<Object, Object>());
		} else {
            raster = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE,bufferedImage.getWidth(),bufferedImage.getHeight(),3,null);
            texImage = new BufferedImage(glColorModel,raster,false,new Hashtable<Object, Object>());
        }
 
        // copy the source image into the produced image
        Graphics g = texImage.getGraphics();
        g.setColor(new Color(0f,0f,0f,0f));
        g.fillRect(0,0,bufferedImage.getWidth(),bufferedImage.getHeight());
        g.drawImage(bufferedImage,0,0,null);
 
        
 
        // build a byte buffer from the temporary image
        // that be used by OpenGL to produce a texture.
        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
 
        imageBuffer = ByteBuffer.allocateDirect(data.length);
        imageBuffer.order(ByteOrder.nativeOrder());
        imageBuffer.put(data, 0, data.length);
        imageBuffer.flip();
 
        return imageBuffer;
    }
	
	
}
