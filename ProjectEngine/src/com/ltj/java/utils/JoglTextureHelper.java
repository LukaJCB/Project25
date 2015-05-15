package com.ltj.java.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import static com.jogamp.opengl.GL4.*;


public class JoglTextureHelper {
	private JoglTextureHelper(){
		
	}
	
	public static Texture loadTexture(GL4 gl,String file) throws IOException {
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	    ImageIO.write(ImageIO.read(new File(file)), "png", os);
	    InputStream fis = new ByteArrayInputStream(os.toByteArray());
	    Texture tex = TextureIO.newTexture(fis, true, TextureIO.PNG);
	    tex.bind(gl);
	    tex.setTexParameteri(gl, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	    tex.setTexParameteri(gl, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	    return tex;
	}
}
