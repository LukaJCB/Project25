package com.ltj.editor;

import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.ltj.java.engine.JoglRenderer;

public class ImageEntry  {
	private String title, path;
	private ImageIcon icon;
	
	public ImageEntry(String title, String path){
		this.title = title;
		this.path = path;
		
		BufferedImage image;
		try {
			if (!path.endsWith("null")){
				image = ImageIO.read(new File(path));
			} else {
				image = ImageIO.read(new File(JoglRenderer.getSelectionSprite().getPath()));
			}
			BufferedImage ret = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
			ret.getGraphics().drawImage(image,0,0,64,64,null);
			this.icon = new ImageIcon(ret);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String toString() {
		return title;
	}


	
	public StringSelection getPath() {
		return new StringSelection(path);
	}
	
	public ImageIcon getThumbnail(){
		return icon;
	}

	
}