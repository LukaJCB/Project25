package com.ltj.editor;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.io.File;

import com.jogamp.opengl.awt.GLCanvas;

public class ImagePanel extends AbstractConsolePanel{

	

	public ImagePanel(String projectPath, GLCanvas canvas, EditorView editor) {
		
		super(projectPath);


		new CanvasDropTargetListener(canvas,editor);

		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY, this);

	}

	public void updateList(){
		model.clear();
		File imageFolder = new File(getProjectPath() + File.separatorChar + "images");
		File[] images = imageFolder.listFiles(pathname -> {
			if (pathname.getName().toLowerCase().endsWith("png")){
				return true;
			}
			return false;
		});
		for (File f : images){
			model.addElement(new ImageEntry(f.getName(), f.getPath()));
		}
		pane.repaint();
	}
	
	
	
	
	
	
	


}
