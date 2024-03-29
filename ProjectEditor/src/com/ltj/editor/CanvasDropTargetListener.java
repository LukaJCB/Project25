package com.ltj.editor;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.java.engine.JoglSprite;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Line;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.utils.BasicIO;
import com.ltj.shared.utils.MatrixHelper;

public class CanvasDropTargetListener extends DropTargetAdapter {

	private GLCanvas canvas;
	private EditorView editor;

	public CanvasDropTargetListener(GLCanvas canvas,EditorView editor) {
		this.canvas = canvas;
		this.editor = editor;
		new DropTarget(canvas, DnDConstants.ACTION_COPY, this, true);
	}
	
	@Override
	public void drop(DropTargetDropEvent event) {
		try {
			if (event.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				event.acceptDrop(DnDConstants.ACTION_COPY);
				String path = (String) event.getTransferable().getTransferData(DataFlavor.stringFlavor);
				
				Point pos = event.getLocation();
				float normalX = MatrixHelper.normalizeCoordiantes((float)pos.getX(),canvas.getWidth());
				float normalY = -MatrixHelper.normalizeCoordiantes((float)pos.getY(), canvas.getHeight());
				Line mouseDrop = Camera.normalized2DCoordsToLine(normalX,normalY);
				mouseDrop.intersectXYPlane();
				float[] worldPos = mouseDrop.getIntersection();
				
				if (isDMO(path)){
					RenderObject o = BasicIO.loadFromDMO(editor.getProjectPath(), path);
					editor.addObject(o, path.replace(".dmo", ""),worldPos[0],worldPos[1]);
				} else {
			
					RenderObject o = new JoglSprite(path, 1, 1);
					editor.addObject(o, "SingleSprite",worldPos[0],worldPos[1]);
				}
				event.dropComplete(true);
				return;
				
			}
			event.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			event.rejectDrop();
		}
	}
	
	private boolean isDMO(String transferable){
		return transferable.endsWith(".dmo");
	}
	
}