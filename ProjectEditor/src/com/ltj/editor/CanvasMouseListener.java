package com.ltj.editor;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.EnumSet;

import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputListener;

import com.jogamp.opengl.awt.GLCanvas;
import com.ltj.shared.engine.Area;
import com.ltj.shared.engine.Camera;
import com.ltj.shared.engine.Engine;
import com.ltj.shared.engine.Line;
import com.ltj.shared.engine.RenderObject;
import com.ltj.shared.utils.MatrixHelper;

public class CanvasMouseListener implements MouseInputListener, MouseWheelListener, KeyListener {

	private static final float BORDER_LENGTH = 0.4f;
	private GLCanvas canvas;
	private JList<RenderObject> list;
	private float[] lastCoordsWorld;
	private EnumSet<Border> scalingMode = EnumSet.noneOf(Border.class);
	private ListSelectionListener selectionListener;
	private AreaChangedListener areaChangedListener;
	private boolean translateMode;
	private boolean changeAreaMode;

	

	public CanvasMouseListener(GLCanvas canvas, JList<RenderObject> list,ListSelectionListener selectionListener) {
		this.canvas = canvas;
		this.list = list;
		this.selectionListener = selectionListener;
	}
	
	public void setList(JList<RenderObject> list){
		this.list = list;
		selectionListener = list.getListSelectionListeners()[0];
	}

	public void setAreaChangedListener(AreaChangedListener areaChangedListener) {
		this.areaChangedListener = areaChangedListener;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
			
		Line mouseClick = getLineFromMouse(e);
		mouseClick.intersectXYPlane();	
		
		if (changeAreaMode){
			
			Area selectedArea = Engine.getArea((int)Math.round((mouseClick.getIntersection()[0] / Engine.getAreaWidth())), 
					(int)Math.round(mouseClick.getIntersection()[1] / Engine.getAreaHeight()));
			
			areaChangedListener.onAreaChange(selectedArea);
			
		} else if (!SwingUtilities.isRightMouseButton(e)) {
			leftMousePressed(mouseClick);
		}
		
		lastCoordsWorld = mouseClick.getIntersection();

	}

	private void leftMousePressed(Line mouseClick) {
		
		RenderObject selectedObject = null;
		for (RenderObject o : Engine.getAllObjects().values()){
			if (mouseClick.intersectsWith(o)){
				selectedObject = o;
			}
		}
		
		if (selectedObject != null){
			//check if selection has changed
			if (list.getSelectedValue() == selectedObject){
				//only allow translation and scaling if selection hasn't changed
				scalingMode = pointNearEdgeOf(mouseClick.getIntersection(), selectedObject);
				if (scalingMode.isEmpty()){
					translateMode = true;
				}
			} else {
				list.setSelectedValue(selectedObject, true);
			}
			
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		scalingMode.clear();
		translateMode = false;
		canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (list.getSelectedValue() == null){
			return;
		}
		Line mouseHover = getLineFromMouse(e);
		int cursorSymbol = Cursor.DEFAULT_CURSOR;

		if (mouseHover.intersectsWith(list.getSelectedValue())){
			
			EnumSet<Border> border = pointNearEdgeOf(mouseHover.getIntersection(), list.getSelectedValue());
			if (!border.isEmpty()){
				cursorSymbol = extractCursorSymbol(border);
			} else {
				cursorSymbol = Cursor.MOVE_CURSOR;
			}
		}
		
		canvas.setCursor(new Cursor(cursorSymbol));
		
	}

	private int extractCursorSymbol(EnumSet<Border> border) {
		int cursorSymbol;
		if (border.contains(Border.NORTH)){
			if (border.contains(Border.EAST)){
				cursorSymbol = Cursor.NE_RESIZE_CURSOR;
			} else if (border.contains(Border.WEST)){
				cursorSymbol = Cursor.NW_RESIZE_CURSOR;
			} else {
				cursorSymbol = Cursor.N_RESIZE_CURSOR;
			}
		} else if (border.contains(Border.SOUTH)){
			if (border.contains(Border.EAST)){
				cursorSymbol = Cursor.SE_RESIZE_CURSOR;
			} else if (border.contains(Border.WEST)){
				cursorSymbol = Cursor.SW_RESIZE_CURSOR;
			} else {
				cursorSymbol = Cursor.S_RESIZE_CURSOR;
			}
		} else {
			cursorSymbol = Cursor.E_RESIZE_CURSOR;
		}
		return cursorSymbol;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		Line mouseDrag = getLineFromMouse(e);
		
		if (SwingUtilities.isRightMouseButton(e)){
			mouseDrag.intersectXYPlane();
			Camera.setLookAt(Camera.getLookAt()[0] - (mouseDrag.getIntersection()[0] - lastCoordsWorld[0]),
					Camera.getLookAt()[1] - (mouseDrag.getIntersection()[1] - lastCoordsWorld[1]));
		} else {
			RenderObject o = list.getSelectedValue();

			if (o != null){

				mouseDrag.intersectXYPlane();

				if (!scalingMode.isEmpty()){
					scaleObjectInDirection(scalingMode,o,mouseDrag.getIntersection());
				} else if(translateMode) {
					o.translate(mouseDrag.getIntersection()[0] - lastCoordsWorld[0]
							,mouseDrag.getIntersection()[1] - lastCoordsWorld[1]);
				}

				selectionListener.valueChanged(null);
				lastCoordsWorld = mouseDrag.getIntersection();

			} 
		}
		canvas.display();
	}

	private void scaleObjectInDirection(EnumSet<Border> scalingMode, RenderObject o, float[] intersection) {

		float deltaX = 2*(intersection[0] - lastCoordsWorld[0]);
		float deltaY = 2*(intersection[1] - lastCoordsWorld[1]);
		
		float scalingX = o.getWidth();
		float scalingY = o.getHeight();
		
		if (scalingMode.contains(Border.NORTH)){
			scalingY += deltaY;
		} else if (scalingMode.contains(Border.SOUTH)){
			scalingY -= deltaY;
		}

		if (scalingMode.contains(Border.EAST)){
			scalingX += deltaX;
		} else if (scalingMode.contains(Border.WEST)){
			scalingX -= deltaX;
		}
		
		o.setScale(scalingX, scalingY);
		
	}

	private Line getLineFromMouse(MouseEvent e) {
		float normalX = MatrixHelper.normalizeCoordiantes(e.getX(),canvas.getWidth());
		float normalY = -MatrixHelper.normalizeCoordiantes(e.getY(), canvas.getHeight());
		Line mouseClick = Camera.normalized2DCoordsToLine(normalX,normalY);
		return mouseClick;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (Camera.getEyePos()[2] > 1 || e.getWheelRotation() > 0){
			Camera.setDistance(Camera.getEyePos()[2] + e.getWheelRotation());
			canvas.display();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public void registerListener() {
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addMouseWheelListener(this);
		canvas.addKeyListener(this);
	}

	private EnumSet<Border> pointNearEdgeOf(float[] point, RenderObject object) {
		EnumSet<Border> border = EnumSet.noneOf(Border.class);
		if (point[0] > object.getX() + object.getWidth()*BORDER_LENGTH){
			border.add(Border.EAST);
		} else if (point[0] < object.getX() - object.getWidth() * BORDER_LENGTH){
			border.add(Border.WEST);
		}
		
		if (point[1] > object.getY() + object.getHeight()*BORDER_LENGTH){
			border.add(Border.NORTH);
		} else if (point[1] < object.getY() - object.getHeight() * BORDER_LENGTH){
			border.add(Border.SOUTH);
		}
		
		return border;
		
	}
	
	private static enum Border{
		NORTH, EAST, SOUTH, WEST;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL){
			changeAreaMode = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_CONTROL){
			changeAreaMode = false;
		}
	}


}
